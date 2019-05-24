package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.models.game.GameEngine;
import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.soviet.Conscript;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {
    @Test
    void testCheckBoardExistsOnGameEngine() {
        // Act
        Board board = new GameBoard(2, 2);
        Engine engine = new GameEngine(board, new ArrayList<>());

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
        Engine engine = new GameEngine(board, new ArrayList<>());

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
        Engine engine = new GameEngine(board, new ArrayList<>());
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
        Engine engine = new GameEngine(board, players);

        // Act
        Player player = engine.getTurn();

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
        Engine engine = new GameEngine(board, players);

        // Act
        engine.defendUnit(board.getPiece(0, 0));

        // Assert
        assertTrue(unit.getDefendStatus());
        assertEquals(playerTwo, engine.getTurn());
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
        Engine engine = new GameEngine(board, players);

        // Act
        engine.attackUnit(board.getPiece(0, 0), board.getPiece(1, 0));

        // Assert
        assertNull(engine.getSelectedPiece());
        assertNull(board.getPiece(0, 0).getUnit());
        assertTrue(targetUnit.isCaptured());
        assertEquals(1, playerTwo.getAllUnits().size());
        assertEquals(0, playerTwo.getAliveUnits().size());
        assertEquals(board.getPiece(1, 0).getUnit(), attackingUnit);
        assertEquals(playerTwo, engine.getTurn());
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
        Engine engine = new GameEngine(board, players);

        // Act
        engine.selectUnit(board.getPiece(0, 0));

        // Assert
        assertNotNull(engine.getSelectedPiece());
        assertNotNull(board.getPiece(0, 0).getUnit());
        assertEquals(playerOne, engine.getTurn());
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
        Engine engine = new GameEngine(board, players);
        engine.moveUnit(playerOnePiece, board.getPiece(1, 0));
        engine.moveUnit(playerTwoPiece, board.getPiece(0, 1));

        // Act
        engine.undoTurn();

        // Assert
        assertNotNull(board.getPiece(0, 0).getUnit());
        assertNotNull(board.getPiece(1, 1).getUnit());
        assertNull(board.getPiece(1, 0).getUnit());
        assertNull(board.getPiece(0, 1).getUnit());
        assertEquals(playerOne, engine.getTurn());
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
        Engine engine = new GameEngine(board, players);
        engine.defendUnit(playerOnePiece);
        engine.defendUnit(playerTwoPiece);

        // Act
        engine.undoTurn();

        // Assert
        assertFalse(board.getPiece(0, 0).getUnit().getDefendStatus());
        assertFalse(board.getPiece(1, 1).getUnit().getDefendStatus());
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
        Engine engine = new GameEngine(board, players);
        engine.defendUnit(board.getPiece(0, 0));
        engine.defendUnit(board.getPiece(1, 1));
        engine.moveUnit(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.moveUnit(board.getPiece(1, 1), board.getPiece(0, 1));
        engine.defendUnit(board.getPiece(1, 0));
        engine.defendUnit(board.getPiece(0, 1));
        boolean initialUndoStatus = playerOne.getUndoStatus();

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
        assertFalse(afterUndoPlayerOne.getUndoStatus());
        assertTrue(afterUndoPlayerTwo.getUndoStatus());
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
        Engine engine = new GameEngine(board, players);
        engine.moveUnit(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.moveUnit(board.getPiece(1, 1), board.getPiece(0, 1));

        // Act
        boolean firstUndo = engine.undoTurn();
        engine.moveUnit(board.getPiece(1, 0), board.getPiece(0, 0));
        engine.moveUnit(board.getPiece(0, 1), board.getPiece(1, 1));
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
        board.getPiece(0, 0).setUnit(unit);
        board.getPiece(0, 0).getUnit().startDefending();
        Engine engine = new GameEngine(board, players);

        // Act
        engine.moveUnit(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.moveUnit(board.getPiece(1, 0), board.getPiece(0, 0));

        // Assert
        assertFalse(unit.getDefendStatus());
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
        Engine engine = new GameEngine(board, players);

        // Act
        engine.selectUnit(board.getPiece(0, 0));
        engine.defendUnit(board.getPiece(0, 0));
        engine.moveUnit(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.attackUnit(board.getPiece(1, 0), board.getPiece(1, 1));
        int turns = engine.getTurns();

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
        Engine engine = new GameEngine(board, players, 2);

        // Act
        engine.selectUnit(board.getPiece(0, 0));
        engine.defendUnit(board.getPiece(0, 0));
        engine.moveUnit(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.attackUnit(board.getPiece(1, 0), board.getPiece(1, 1));
        int actualTurns = engine.getRemainingTurns();
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
        Engine engine = new GameEngine(board, players);

        // Act
        engine.selectUnit(board.getPiece(0, 0));
        engine.defendUnit(board.getPiece(0, 0));
        engine.moveUnit(board.getPiece(0, 0), board.getPiece(1, 0));
        engine.attackUnit(board.getPiece(1, 0), board.getPiece(1, 1));
        int actualTurns = engine.getRemainingTurns();
        int expectedTurns = engine.getTurnLimit() - engine.getTurns();

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
        Engine engine = new GameEngine(board, players);

        // Act
        engine.selectUnit(board.getPiece(0, 0));
        engine.defendUnit(board.getPiece(0, 0));
        engine.attackUnit(board.getPiece(0, 0), board.getPiece(1, 1));
        engine.moveUnit(board.getPiece(1, 1), board.getPiece(1, 0));
        engine.forfeitGame();

        // Assert
        assertEquals(1, playerOne.getWins());
        assertEquals(0, playerTwo.getWins());
        assertEquals(0, playerOne.getLosses());
        assertEquals(1, playerTwo.getLosses());
        assertEquals(unit, board.getPiece(0, 0).getUnit());
        assertEquals(unitTwo, board.getPiece(1, 1).getUnit());
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
        Engine engine = new GameEngine(board, players, 2);

        // Act
        engine.endGame();

        // Assert
        assertEquals(1, playerOne.getWins());
        assertEquals(0, playerTwo.getWins());
        assertEquals(0, playerOne.getLosses());
        assertEquals(1, playerTwo.getLosses());
        assertEquals(unitOne, board.getPiece(0, 0).getUnit());
        assertEquals(unitTwo, board.getPiece(1, 0).getUnit());
        assertEquals(unitThree, board.getPiece(1, 1).getUnit());
    }

    @Test
    void testEndGameFromTurns() {
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
        Engine engine = new GameEngine(board, players, 2);

        // Act
        engine.selectUnit(board.getPiece(0, 0));
        engine.moveUnit(board.getPiece(0, 0), board.getPiece(0, 1));
        engine.defendUnit(board.getPiece(1, 1));

        // Assert
        assertEquals(0, playerOne.getWins());
        assertEquals(1, playerTwo.getWins());
        assertEquals(1, playerOne.getLosses());
        assertEquals(0, playerTwo.getLosses());
        assertEquals(unitOne, board.getPiece(0, 0).getUnit());
        assertEquals(unitTwo, board.getPiece(1, 1).getUnit());
    }
}
