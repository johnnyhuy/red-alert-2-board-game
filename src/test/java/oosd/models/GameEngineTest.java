package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.board.Piece;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
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
        boolean isValidMove = unitPiece.getUnit().getUnitBehaviour().isValidMove(gameEngine, unitPiece);

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
}
