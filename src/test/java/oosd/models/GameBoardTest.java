package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.board.Piece;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameBoardTest {
    @Test
    void testShouldGetBoardColumns() {
        // Arrange
        final int expectedColumns = 6;
        Board board = new GameBoard(expectedColumns, 6);

        // Act
        int columns = board.getColumns();

        // Assert
        assertEquals(expectedColumns, columns);
    }

    @Test
    void testShouldGetBoardRow() {
        // Arrange
        final int expectedRows = 6;
        Board board = new GameBoard(expectedRows, 6);

        // Act
        int rows = board.getRows();

        // Assert
        assertEquals(expectedRows, rows);
    }

    @Test
    void testShouldGetPieceWithObject() {
        // Arrange
        Board board = new GameBoard(6, 6);
        Piece piece = new Piece(1, 1);

        // Act
        Piece selectedPiece = board.getPiece(piece);

        // Assert
        assertEquals(selectedPiece.getColumn(), 1);
        assertEquals(selectedPiece.getRow(), 1);
    }

    @Test
    void testShouldGetPieceWithInt() {
        // Arrange
        Board board = new GameBoard(6, 6);

        // Act
        Piece selectedPiece = board.getPiece(1, 1);

        // Assert
        assertEquals(selectedPiece.getColumn(), 1);
        assertEquals(selectedPiece.getRow(), 1);
    }

    @Test
    void testShouldGetPieceAndSetUnit() {
        // Arrange
        Player player = new Player("John Tester", new Team("Red"));
        Unit unit = new GISoldier(player);
        Board board = new GameBoard(6, 6);
        Piece piece = new Piece(1, 1);

        // Act
        board.getPiece(piece).setUnit(unit);
        Piece selectedPiece = board.getPiece(piece);

        // Assert
        assertEquals(selectedPiece.getColumn(), 1);
        assertEquals(selectedPiece.getRow(), 1);
        assertEquals(selectedPiece.getUnit(), unit);
    }

    @Test
    void testShouldNotBeNegativeCreateBoardRowsAndColumns() {
        // Act
        Executable run = () -> new GameBoard(-209, -209);

        // Assert
        assertThrows(AssertionError.class, run);
    }

    @Test
    void testShouldNotGetPieceWithObjectGreaterThanBoardSize() {
        // Arrange
        Board board = new GameBoard(42, 42);

        // Act
        Executable run = () -> board.getPiece(new Piece(100, 100));

        // Assert
        assertThrows(AssertionError.class, run);
    }

    @Test
    void testShouldNotGetPieceWithIntegersGreaterThanBoardSize() {
        // Arrange
        Board board = new GameBoard(42, 42);

        // Act
        Executable run = () -> board.getPiece(100, 100);

        // Assert
        assertThrows(AssertionError.class, run);
    }
}
