package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.board.Piece;
import oosd.models.game.*;
import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.soviet.Conscript;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {
    @Test
    void testCheckBoardExistsOnGameEngine() {
        // Act
        Board board = new GameBoard(2, 2);
        PlayerService playerService = new GamePlayerService(new ArrayList<>());
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Assert
        assertNotNull(engine);
        assertNotNull(engine.getBoard());
    }

    @Test
    void testSetBoardSize() {
        // Arrange
        final int rows = 100;
        final int columns = 100;

        // Act
        Board board = new GameBoard(columns, rows);
        PlayerService playerService = new GamePlayerService(new ArrayList<>());
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Assert
        assertEquals(engine.getBoard().getRows(), rows);
        assertEquals(engine.getBoard().getColumns(), columns);
        assertNotNull(engine.getBoard().getPiece(columns - 1, rows - 1));
    }

    @Test
    void testGetValidMoves() {
        // Arrange
        Player player = new Player("John Tester");
        Unit unit = new GISoldier(player);
        Board board = new GameBoard(2, 2);
        PlayerService playerService = new GamePlayerService(new ArrayList<>());
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);
        Piece piece = engine.getBoard().getPiece(0, 1);
        piece.setUnit(unit);

        // Act
        List<Piece> validMove = piece.getUnit().getUnitBehaviour().getValidMoves(engine, piece);

        // Assert
        assertTrue(validMove.size() > 0);
    }

    @Test
    void testGetFirstTurnOnFirstPlayer() {
        // Arrange
        Board board = new GameBoard(2, 2);
        List<Player> players = new ArrayList<>();
        players.add(new Player("John Tester"));
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        Player player = engine.getTurnService().getTurn();

        // Assert
        assertNotNull(player);
    }

    @Test
    void testDefendUnit() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit unit = new GISoldier(playerOne);
        board.getPiece(0, 0).setUnit(unit);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.defend(board.getPiece(0, 0));

        // Assert
        assertTrue(unit.canDefend(board.getPiece(0, 0)));
        assertEquals(playerTwo, engine.getTurnService().getTurn());
    }

    @Test
    void testAttackUnit() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit attackingUnit = new GISoldier(playerOne);
        Unit targetUnit = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(attackingUnit);
        board.getPiece(1, 0).setUnit(targetUnit);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.attack(board.getPiece(0, 0), board.getPiece(1, 0));

        // Assert
        assertNull(engine.getSelected());
        assertNull(board.getPiece(0, 0).getUnit());
        assertTrue(targetUnit.isCaptured());
        assertEquals(1, playerTwo.getAllUnits().size());
        assertEquals(0, playerTwo.getAliveUnits().size());
        assertEquals(board.getPiece(1, 0).getUnit(), attackingUnit);
        assertEquals(playerTwo, engine.getTurnService().getTurn());
    }

    @Test
    void testSelectUnit() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit unit = new GISoldier(playerOne);
        board.getPiece(0, 0).setUnit(unit);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.select(board.getPiece(0, 0));

        // Assert
        assertNotNull(engine.getSelected());
        assertNotNull(board.getPiece(0, 0).getUnit());
        assertEquals(playerOne, engine.getTurnService().getTurn());
    }

    @Test
    void testUndoTurnFromAllPlayers() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit playerOneUnit = new GISoldier(playerOne);
        Unit playerTwoUnit = new Conscript(playerTwo);
        Piece playerOnePiece = board.getPiece(0, 0);
        Piece playerTwoPiece = board.getPiece(1, 1);
        playerOnePiece.setUnit(playerOneUnit);
        playerTwoPiece.setUnit(playerTwoUnit);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);
        engine.move(playerOnePiece, board.getPiece(1, 0));
        engine.move(playerTwoPiece, board.getPiece(0, 1));

        // Act
        engine.undoTurn();
        Board afterBoard = engine.getBoard();

        // Assert
        assertNotNull(afterBoard.getPiece(0, 0).getUnit());
        assertNotNull(afterBoard.getPiece(1, 1).getUnit());
        assertNull(afterBoard.getPiece(1, 0).getUnit());
        assertNull(afterBoard.getPiece(0, 1).getUnit());
        assertEquals(playerOne, engine.getTurnService().getTurn());
    }

    @Test
    void testUndoTurnWhenPlayersDefend() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit playerOneUnit = new GISoldier(playerOne);
        Unit playerTwoUnit = new Conscript(playerTwo);
        Piece playerOnePiece = board.getPiece(0, 0);
        Piece playerTwoPiece = board.getPiece(1, 1);
        playerOnePiece.setUnit(playerOneUnit);
        playerTwoPiece.setUnit(playerTwoUnit);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);
        engine.defend(playerOnePiece);
        engine.defend(playerTwoPiece);

        // Act
        engine.undoTurn();
        Board afterBoard = engine.getBoard();

        // Assert
        assertFalse(afterBoard.getPiece(0, 0).getUnit().canDefend(afterBoard.getPiece(0, 0)));
        assertFalse(afterBoard.getPiece(1, 1).getUnit().canDefend(afterBoard.getPiece(1, 1)));
    }

    @Test
    void testUndoMoreThanThreeTimesInARow() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit playerOneUnit = new GISoldier(playerOne);
        Unit playerTwoUnit = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(playerOneUnit);
        board.getPiece(1, 1).setUnit(playerTwoUnit);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);
        engine.defend(board.getPiece(0, 0));
        engine.defend(board.getPiece(1, 1));
        engine.move(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.move(board.getPiece(1, 1), board.getPiece(0, 1));
        engine.defend(board.getPiece(1, 0));
        engine.defend(board.getPiece(0, 1));
        boolean initialUndoStatus = playerOne.canUndo();

        // Act
        engine.undoTurn();
        engine.undoTurn();
        engine.undoTurn();
        boolean lastUndo = engine.undoTurn();

        // Assert
        Player afterUndoPlayerOne = engine.getBoard().getPiece(0, 0).getUnit().getPlayer();
        Player afterUndoPlayerTwo = engine.getBoard().getPiece(1, 1).getUnit().getPlayer();

        assertEquals(3, afterUndoPlayerOne.getUndoMoves());
        assertEquals(0, afterUndoPlayerTwo.getUndoMoves());
        assertFalse(lastUndo);
        assertTrue(initialUndoStatus);
        assertFalse(afterUndoPlayerOne.canUndo());
        assertTrue(afterUndoPlayerTwo.canUndo());
    }

    @Test
    void testUndoStatusCannotBeTrueAfterSecondUndo() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit playerOneUnit = new GISoldier(playerOne);
        Unit playerTwoUnit = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(playerOneUnit);
        board.getPiece(1, 1).setUnit(playerTwoUnit);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);
        engine.move(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.move(board.getPiece(1, 1), board.getPiece(0, 1));

        // Act
        boolean firstUndo = engine.undoTurn();
        engine.move(board.getPiece(1, 0), board.getPiece(0, 0));
        engine.move(board.getPiece(0, 1), board.getPiece(1, 1));
        boolean lastUndo = engine.undoTurn();

        // Assert
        assertTrue(firstUndo);
        assertFalse(lastUndo);
    }

    @Test
    void testMoveUnitDefendStatusShouldGoAway() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit unit = new GISoldier(playerOne);
        Unit unitTwo = new GISoldier(playerTwo);
        board.getPiece(0, 0).setUnit(unit);
        board.getPiece(1, 1).setUnit(unitTwo);
        board.getPiece(0, 0).getUnit().startDefending(board.getPiece(0, 0));
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.move(board.getPiece(1, 1), board.getPiece(0, 1));
        engine.move(board.getPiece(0, 1), board.getPiece(1, 1));

        // Assert
        assertFalse(unit.canDefend(board.getPiece(0, 0)));
    }

    @Test
    void testGetTotalAmountOfTurns() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit unit = new GISoldier(playerOne);
        Unit unitTwo = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(unit);
        board.getPiece(1, 1).setUnit(unitTwo);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.select(board.getPiece(0, 0));
        engine.defend(board.getPiece(0, 0));
        engine.move(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.attack(board.getPiece(1, 0), board.getPiece(1, 1));
        int turns = engine.getTurnService().getTurns();

        // Assert
        assertEquals(turns, 3);
    }

    @Test
    void testRemainingTurnsCantBeNegative() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit unit = new GISoldier(playerOne);
        Unit unitTwo = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(unit);
        board.getPiece(1, 1).setUnit(unitTwo);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 2);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.select(board.getPiece(0, 0));
        engine.defend(board.getPiece(0, 0));
        engine.move(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.attack(board.getPiece(1, 0), board.getPiece(1, 1));
        int actualTurns = engine.getTurnService().getRemainingTurns();
        int expectedTurns = 0;

        // Assert
        assertEquals(expectedTurns, actualTurns);
    }

    @Test
    void testGetRemainingTurns() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit unit = new GISoldier(playerOne);
        Unit unitTwo = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(unit);
        board.getPiece(1, 1).setUnit(unitTwo);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.select(board.getPiece(0, 0));
        engine.defend(board.getPiece(0, 0));
        engine.move(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.attack(board.getPiece(1, 0), board.getPiece(1, 1));
        int actualTurns = engine.getTurnService().getRemainingTurns();
        int expectedTurns = engine.getTurnService().getTurnLimit() - engine.getTurnService().getTurns();

        // Assert
        assertEquals(expectedTurns, actualTurns);
    }

    @Test
    void testForfeitGame() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit unit = new GISoldier(playerOne);
        Unit unitTwo = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(unit);
        board.getPiece(1, 1).setUnit(unitTwo);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.select(board.getPiece(0, 0));
        engine.defend(board.getPiece(0, 0));
        engine.attack(board.getPiece(0, 0), board.getPiece(1, 1));
        engine.move(board.getPiece(1, 1), board.getPiece(1, 0));
        engine.forfeitGame();
        Board afterBoard = engine.getBoard();

        // Assert
        assertEquals(1, playerOne.getWins());
        assertEquals(0, playerTwo.getWins());
        assertEquals(0, playerOne.getLosses());
        assertEquals(1, playerTwo.getLosses());
        assertEquals(false, afterBoard.getPiece(0, 0).getUnit().canDefend(afterBoard.getPiece(0, 0)));
        assertEquals(false, afterBoard.getPiece(0, 0).getUnit().isCaptured());
        assertEquals(false, afterBoard.getPiece(1, 1).getUnit().canDefend(afterBoard.getPiece(1, 1)));
        assertEquals(false, afterBoard.getPiece(1, 1).getUnit().isCaptured());
    }

    @Test
    void testEndGame() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit unitOne = new GISoldier(playerOne);
        Unit unitTwo = new Conscript(playerTwo);
        Unit unitThree = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(unitOne);
        board.getPiece(1, 0).setUnit(unitThree);
        board.getPiece(1, 1).setUnit(unitTwo);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 2);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.endGame();

        // Assert
        assertEquals(0, playerOne.getWins());
        assertEquals(1, playerTwo.getWins());
        assertEquals(1, playerOne.getLosses());
        assertEquals(0, playerTwo.getLosses());
        assertEquals(unitOne, board.getPiece(0, 0).getUnit());
        assertEquals(unitTwo, board.getPiece(1, 0).getUnit());
        assertEquals(unitThree, board.getPiece(1, 1).getUnit());
    }

    @Test
    void testCanDefendUnit() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(2, 2);
        Unit unitOne = new GISoldier(playerOne);
        Unit unitTwo = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(unitOne);
        board.getPiece(1, 1).setUnit(unitTwo);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 2);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        boolean cannotDefendNoSelection = engine.canDefend(board.getPiece(0, 0));
        engine.select(board.getPiece(0, 0));
        boolean canDefend = engine.canDefend(board.getPiece(0, 0));
        boolean cannotDefendNotSelected = engine.canDefend(board.getPiece(1, 1));

        // Assert
        assertTrue(canDefend);
        assertFalse(cannotDefendNoSelection);
        assertFalse(cannotDefendNotSelected);
    }

    @Test
    void testCanAttackUnit() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(4, 4);
        Unit unitOne = new GISoldier(playerOne);
        Unit unitTwo = new Conscript(playerTwo);
        Unit unitThree = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(unitOne);
        board.getPiece(0, 1).setUnit(unitTwo);
        board.getPiece(3, 1).setUnit(unitThree);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 2);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.select(board.getPiece(0, 0));
        boolean canAttack = engine.canAttack(board.getPiece(0, 1));
        boolean cannotAttackInvalidMove = engine.canAttack(board.getPiece(3, 1));

        // Assert
        assertTrue(canAttack);
        assertFalse(cannotAttackInvalidMove);
    }

    @Test
    void testCanSelectUnit() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(4, 4);
        Unit unitOne = new GISoldier(playerOne);
        Unit unitTwo = new Conscript(playerTwo);
        Unit unitThree = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(unitOne);
        board.getPiece(0, 1).setUnit(unitTwo);
        board.getPiece(3, 1).setUnit(unitThree);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 2);
        Engine engine = new GameEngine(board, playerService, turnService);
        engine.defend(board.getPiece(3, 1));

        // Act
        boolean canSelect = engine.canSelect(board.getPiece(0, 1));
        boolean cannotSelectEnemy = engine.canSelect(board.getPiece(0, 0));
        boolean cannotSelectOnDefense = engine.canSelect(board.getPiece(3, 1));

        // Assert
        assertTrue(canSelect);
        assertFalse(cannotSelectEnemy);
        assertFalse(cannotSelectOnDefense);
    }

    @Test
    void testResetGameOnNoHistoryShouldNotFail() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(4, 4);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 2);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        Executable reset = engine::resetGame;

        // Assert
        assertDoesNotThrow(reset);
    }

    @Test
    void testResetGameEmptiesHistory() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(4, 4);
        Unit unitOne = new GISoldier(playerOne);
        Unit unitTwo = new Conscript(playerTwo);
        Unit unitThree = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(unitOne);
        board.getPiece(0, 1).setUnit(unitTwo);
        board.getPiece(3, 1).setUnit(unitThree);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 2);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.move(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.move(board.getPiece(3, 1), board.getPiece(3, 0));
        engine.move(board.getPiece(1, 0), board.getPiece(1, 1));
        engine.move(board.getPiece(3, 0), board.getPiece(3, 2));
        engine.resetGame();
        engine.undoTurn();
        Board afterBoard = engine.getBoard();

        // Assert
        assertNull(afterBoard.getPiece(3, 0).getUnit());
        assertNull(afterBoard.getPiece(1, 0).getUnit());
        assertNotNull(afterBoard.getPiece(0, 0).getUnit());
        assertNotNull(afterBoard.getPiece(3, 1).getUnit());
    }

    @Test
    void testResetGameCanUndo() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(4, 4);
        Unit unitOne = new GISoldier(playerOne);
        Unit unitTwo = new Conscript(playerTwo);
        Unit unitThree = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(unitOne);
        board.getPiece(0, 1).setUnit(unitTwo);
        board.getPiece(3, 1).setUnit(unitThree);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 2);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.move(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.move(board.getPiece(3, 1), board.getPiece(3, 0));
        engine.undoTurn();
        engine.move(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.move(board.getPiece(3, 1), board.getPiece(3, 0));
        engine.resetGame();
        engine.move(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.move(board.getPiece(3, 1), board.getPiece(3, 0));
        boolean undoTurn = engine.undoTurn();
        boolean canUndo = engine.getTurnService().getTurn().canUndo();

        // Assert
        assertTrue(undoTurn);
        assertTrue(canUndo);
    }

    @Test
    void testSaveGameAndRestoreAfter() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(4, 4);
        Unit unitOne = new GISoldier(playerOne);
        Unit unitTwo = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(unitOne);
        board.getPiece(0, 1).setUnit(unitTwo);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 2);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.move(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.move(board.getPiece(0, 1), board.getPiece(3, 0));
        boolean saveExistsBefore = engine.saveGameExists();
        engine.saveGame();
        engine.move(board.getPiece(1, 0), board.getPiece(2, 0));
        engine.move(board.getPiece(3, 0), board.getPiece(3, 3));
        boolean saveExistsAfterSave = engine.saveGameExists();
        engine.restoreGame();
        boolean saveExistsAfterRestore = engine.saveGameExists();
        Board afterBoard = engine.getBoard();

        // Assert
        assertFalse(saveExistsBefore);
        assertTrue(saveExistsAfterSave);
        assertFalse(saveExistsAfterRestore);
        assertNotNull(afterBoard.getPiece(3, 0).getUnit());
        assertNotNull(afterBoard.getPiece(1, 0).getUnit());
    }

    @Test
    void testSaveGameAndRestoreOnDefend() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(4, 4);
        Unit unit = new GISoldier(playerOne);
        board.getPiece(0, 0).setUnit(unit);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 2);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.defend(board.getPiece(0, 0));
        engine.saveGame();
        engine.restoreGame();
        Board afterBoard = engine.getBoard();

        // Assert
        assertTrue(afterBoard.getPiece(0, 0).getUnit().canDefend(afterBoard.getPiece(0, 0)));
    }

    @Test
    void testResetGameSetsPlayerTurnBack() {
        // Arrange
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        Board board = new GameBoard(4, 4);
        Unit unitOne = new GISoldier(playerOne);
        Unit unitTwo = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(unitOne);
        board.getPiece(0, 1).setUnit(unitTwo);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 2);
        Engine engine = new GameEngine(board, playerService, turnService);

        // Act
        engine.move(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.resetGame();

        // Assert
        assertEquals(engine.getTurnService().getTurn(), playerOne);
        assertEquals(2, engine.getTurnService().getRemainingTurns());
    }
}
