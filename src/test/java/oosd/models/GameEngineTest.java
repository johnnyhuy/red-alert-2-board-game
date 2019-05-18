package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.board.Piece;
import oosd.models.player.Player;
import oosd.models.player.Team;
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
        GameEngine gameEngine = new GameEngine(board, new ArrayList<>());

        // Assert
        assertNotNull(gameEngine);
        assertNotNull(gameEngine.getBoard());
    }

    @Test
    void testSetBoardSize() {
        // Arrange
        final int rows = 100;
        final int columns = 100;

        // Act
        Board board = new GameBoard(columns, rows);
        GameEngine gameEngine = new GameEngine(board, new ArrayList<>());

        // Assert
        assertEquals(gameEngine.getBoard().getRows(), rows);
        assertEquals(gameEngine.getBoard().getColumns(), columns);
        assertNotNull(gameEngine.getBoard().getPiece(columns - 1, rows - 1));
    }

    @Test
    void testSetAndGetSelectedPiece() {
        // Arrange
        Piece expectedPiece = new Piece(1, 1);
        Board board = new GameBoard(2, 2);
        GameEngine gameEngine = new GameEngine(board, new ArrayList<>());

        // Act
        gameEngine.setSelectedPiece(expectedPiece);
        Piece selectedPiece = gameEngine.getSelectedPiece();

        // Assert
        assertEquals(expectedPiece, selectedPiece);
    }

    @Test
    void testIsInvalidMoveWhenUnitExists() {
        // Arrange
        Player player = new Player("John Tester", new Team("Red"));
        Unit unit = new GISoldier(player);
        Board board = new GameBoard(2, 2);
        GameEngine gameEngine = new GameEngine(board, new ArrayList<>());
        Piece unitPiece = gameEngine.getBoard().getPiece(0, 0);
        Piece selectedPiece = gameEngine.getBoard().getPiece(0, 1);
        unitPiece.setUnit(unit);
        selectedPiece.setUnit(unit);
        gameEngine.setSelectedPiece(selectedPiece);

        // Act
        boolean isValidMove = unitPiece.getUnit().getUnitBehaviour().isValidMove(gameEngine, new Piece(100, 100));

        // Assert
        assertFalse(isValidMove);
    }

    @Test
    void testGetValidMoves() {
        // Arrange
        Player player = new Player("John Tester", new Team("Red"));
        Unit unit = new GISoldier(player);
        Board board = new GameBoard(2, 2);
        GameEngine gameEngine = new GameEngine(board, new ArrayList<>());
        Piece piece = gameEngine.getBoard().getPiece(0, 1);
        piece.setUnit(unit);

        // Act
        List<Piece> validMove = piece.getUnit().getUnitBehaviour().getValidMoves(gameEngine, piece);

        // Assert
        assertTrue(validMove.size() > 0);
    }

    @Test
    void testGetFirstTurnOnFirstPlayer() {
        // Arrange
        Board board = new GameBoard(2, 2);
        List<Player> players = new ArrayList<>();
        players.add(new Player("John Tester", new Team("Red")));
        GameEngine gameEngine = new GameEngine(board, players);

        // Act
        Player player = gameEngine.getTurn();

        // Assert
        assertNotNull(player);
    }

    @Test
    void testGetNextTurn() {
        // Arrange
        Player playerOne = new Player("Johnny Dave", new Team("Red"));
        Player playerTwo = new Player("Jane Doe", new Team("Blue"));
        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        Board board = new GameBoard(2, 2);
        GameEngine gameEngine = new GameEngine(board, players);

        // Act
        Player firstTurn = gameEngine.getTurn();
        Player getFirstTurn = gameEngine.getTurn();
        Player secondTurn = gameEngine.getNextTurn();
        Player getSecondTurn = gameEngine.getTurn();
        Player thirdTurn = gameEngine.getNextTurn();
        Player getThirdTurn = gameEngine.getTurn();
        Player forthTurn = gameEngine.getNextTurn();
        Player getForthTurn = gameEngine.getTurn();

        // Assert
        assertEquals(playerOne, firstTurn);
        assertEquals(playerOne, getFirstTurn);
        assertEquals(playerTwo, getSecondTurn);
        assertEquals(playerTwo, secondTurn);
        assertEquals(playerOne, thirdTurn);
        assertEquals(playerOne, getThirdTurn);
        assertEquals(playerTwo, forthTurn);
        assertEquals(playerTwo, getForthTurn);
    }

    @Test
    void testGetPlayers() {
        // Arrange
        Player playerOne = new Player("Johnny Dave", new Team("Red"));
        Player playerTwo = new Player("Jane Doe", new Team("Blue"));
        List<Player> expectedPlayers = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        Board board = new GameBoard(2, 2);
        GameEngine gameEngine = new GameEngine(board, expectedPlayers);

        // Act
        List<Player> actualPlayers = gameEngine.getPlayers();

        // Assert
        assertEquals(expectedPlayers, actualPlayers);
        assertEquals(2, actualPlayers.size());
    }

    @Test
    void testDefendUnit() {
        // Arrange
        Player playerOne = new Player("Johnny Dave", new Team("Red"));
        Player playerTwo = new Player("Jane Doe", new Team("Blue"));
        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        Board board = new GameBoard(2, 2);
        Unit unit = new GISoldier(playerOne);
        board.getPiece(0, 0).setUnit(unit);
        GameEngine gameEngine = new GameEngine(board, players);

        // Act
        gameEngine.defendUnit(board.getPiece(0, 0));

        // Assert
        assertTrue(unit.getDefendStatus());
        assertEquals(playerTwo, gameEngine.getTurn());
    }

    @Test
    void testAttackUnit() {
        // Arrange
        Player playerOne = new Player("Johnny Dave", new Team("Red"));
        Player playerTwo = new Player("Jane Doe", new Team("Blue"));
        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        Board board = new GameBoard(2, 2);
        Unit attackingUnit = new GISoldier(playerOne);
        Unit targetUnit = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(attackingUnit);
        board.getPiece(1, 0).setUnit(targetUnit);
        GameEngine gameEngine = new GameEngine(board, players);

        // Act
        gameEngine.attackUnit(board.getPiece(0, 0), board.getPiece(1, 0));

        // Assert
        assertNull(gameEngine.getSelectedPiece());
        assertNull(board.getPiece(0, 0).getUnit());
        assertEquals(board.getPiece(1, 0).getUnit(), attackingUnit);
        assertEquals(playerTwo, gameEngine.getTurn());
    }

    @Test
    void testSelectUnit() {
        // Arrange
        Player playerOne = new Player("Johnny Dave", new Team("Red"));
        Player playerTwo = new Player("Jane Doe", new Team("Blue"));
        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        Board board = new GameBoard(2, 2);
        Unit unit = new GISoldier(playerOne);
        board.getPiece(0, 0).setUnit(unit);
        GameEngine gameEngine = new GameEngine(board, players);

        // Act
        gameEngine.selectUnit(board.getPiece(0, 0));

        // Assert
        assertNotNull(gameEngine.getSelectedPiece());
        assertNotNull(board.getPiece(0, 0).getUnit());
        assertEquals(playerOne, gameEngine.getTurn());
    }

    @Test
    void testUndoTurnFromAllPlayers() {
        // Arrange
        Player playerOne = new Player("Johnny Dave", new Team("Red"));
        Player playerTwo = new Player("Jane Doe", new Team("Blue"));
        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        Board board = new GameBoard(2, 2);
        Unit playerOneUnit = new GISoldier(playerOne);
        Unit playerTwoUnit = new Conscript(playerTwo);
        Piece playerOnePiece = board.getPiece(0, 0);
        Piece playerTwoPiece = board.getPiece(1, 1);
        playerOnePiece.setUnit(playerOneUnit);
        playerTwoPiece.setUnit(playerTwoUnit);
        GameEngine gameEngine = new GameEngine(board, players);
        gameEngine.moveUnit(playerOnePiece, board.getPiece(1, 0));
        gameEngine.moveUnit(playerTwoPiece, board.getPiece(0, 1));

        // Act
        gameEngine.undoTurn();

        // Assert
        assertNotNull(board.getPiece(0, 0).getUnit());
        assertNotNull(board.getPiece(1, 1).getUnit());
        assertNull(board.getPiece(1, 0).getUnit());
        assertNull(board.getPiece(0, 1).getUnit());
        assertEquals(playerOne, gameEngine.getTurn());
    }

    @Test
    void testUndoTurnWhenPlayersDefend() {
        // Arrange
        Player playerOne = new Player("Johnny Dave", new Team("Red"));
        Player playerTwo = new Player("Jane Doe", new Team("Blue"));
        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        Board board = new GameBoard(2, 2);
        Unit playerOneUnit = new GISoldier(playerOne);
        Unit playerTwoUnit = new Conscript(playerTwo);
        Piece playerOnePiece = board.getPiece(0, 0);
        Piece playerTwoPiece = board.getPiece(1, 1);
        playerOnePiece.setUnit(playerOneUnit);
        playerTwoPiece.setUnit(playerTwoUnit);
        GameEngine gameEngine = new GameEngine(board, players);
        gameEngine.defendUnit(playerOnePiece);
        gameEngine.defendUnit(playerTwoPiece);

        // Act
        gameEngine.undoTurn();

        // Assert
        assertFalse(board.getPiece(0, 0).getUnit().getDefendStatus());
        assertFalse(board.getPiece(1, 1).getUnit().getDefendStatus());
    }

    @Test
    void testUndoMoreThanThreeTimesInARow() {
        // Arrange
        Player playerOne = new Player("Johnny Dave", new Team("Red"));
        Player playerTwo = new Player("Jane Doe", new Team("Blue"));
        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        Board board = new GameBoard(2, 2);
        Unit playerOneUnit = new GISoldier(playerOne);
        Unit playerTwoUnit = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(playerOneUnit);
        board.getPiece(1, 1).setUnit(playerTwoUnit);
        GameEngine gameEngine = new GameEngine(board, players);
        gameEngine.defendUnit(board.getPiece(0, 0));
        gameEngine.defendUnit(board.getPiece(1, 1));
        gameEngine.moveUnit(board.getPiece(0, 0), board.getPiece(1, 0));
        gameEngine.moveUnit(board.getPiece(1, 1), board.getPiece(0, 1));
        gameEngine.defendUnit(board.getPiece(1, 0));
        gameEngine.defendUnit(board.getPiece(0, 1));
        boolean initialUndoStatus = playerOne.getUndoStatus();

        // Act
        gameEngine.undoTurn();
        gameEngine.undoTurn();
        gameEngine.undoTurn();
        boolean lastUndo = gameEngine.undoTurn();

        // Assert
        Player afterUndoPlayerOne = board.getPiece(0, 0).getUnit().getPlayer();
        Player afterUndoPlayerTwo = board.getPiece(1, 1).getUnit().getPlayer();

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
        Player playerOne = new Player("Johnny Dave", new Team("Red"));
        Player playerTwo = new Player("Jane Doe", new Team("Blue"));
        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        Board board = new GameBoard(2, 2);
        Unit playerOneUnit = new GISoldier(playerOne);
        Unit playerTwoUnit = new Conscript(playerTwo);
        board.getPiece(0, 0).setUnit(playerOneUnit);
        board.getPiece(1, 1).setUnit(playerTwoUnit);
        GameEngine gameEngine = new GameEngine(board, players);
        gameEngine.moveUnit(board.getPiece(0, 0), board.getPiece(1, 0));
        gameEngine.moveUnit(board.getPiece(1, 1), board.getPiece(0, 1));

        // Act
        boolean firstUndo = gameEngine.undoTurn();
        gameEngine.moveUnit(board.getPiece(1, 0), board.getPiece(0, 0));
        gameEngine.moveUnit(board.getPiece(0, 1), board.getPiece(1, 1));
        boolean lastUndo = gameEngine.undoTurn();

        // Assert
        assertTrue(firstUndo);
        assertFalse(lastUndo);
    }

    @Test
    void testMoveUnitDefendStatusShouldGoAway() {
        // Arrange
        Player playerOne = new Player("Johnny Dave", new Team("Red"));
        Player playerTwo = new Player("Jane Doe", new Team("Blue"));
        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        Board board = new GameBoard(2, 2);
        Unit unit = new GISoldier(playerOne);
        board.getPiece(0, 0).setUnit(unit);
        board.getPiece(0, 0).getUnit().startDefending();
        GameEngine gameEngine = new GameEngine(board, players);

        // Act
        gameEngine.moveUnit(board.getPiece(0, 0), board.getPiece(1, 0));
        gameEngine.moveUnit(board.getPiece(1, 0), board.getPiece(0, 0));

        // Assert
        assertFalse(unit.getDefendStatus());
    }
}
