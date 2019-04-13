package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {
    @Test
    void testShouldGetBoardColumns() {
        // Arrange
        final int expectedColumns = 6;
        Board board = new Board(expectedColumns, 6);

        // Act
        int columns = board.getColumns();

        // Assert
        assertEquals(expectedColumns, columns);
    }

    @Test
    void testShouldGetBoardRow() {
        // Arrange
        final int expectedRows = 6;
        Board board = new Board(expectedRows, 6);

        // Act
        int rows = board.getRows();

        // Assert
        assertEquals(expectedRows, rows);
    }

    @Test
    void testShouldGetHexagonWithObject() {
        // Arrange
        Board board = new Board(6, 6);
        Hexagon hexagon = new Hexagon(1, 1);

        // Act
        Hexagon selectedHexagon = board.getHexagon(hexagon);

        // Assert
        assertEquals(selectedHexagon.getColumn(), 1);
        assertEquals(selectedHexagon.getRow(), 1);
    }

    @Test
    void testShouldGetHexagonWithInt() {
        // Arrange
        Board board = new Board(6, 6);

        // Act
        Hexagon selectedHexagon = board.getHexagon(1, 1);

        // Assert
        assertEquals(selectedHexagon.getColumn(), 1);
        assertEquals(selectedHexagon.getRow(), 1);
    }

    @Test
    void testShouldGetHexagonAndSetUnit() {
        // Arrange
        Player player = new Player("John Tester", new Team("Red"));
        Unit unit = new GISoldier(player);
        Board board = new Board(6, 6);
        Hexagon hexagon = new Hexagon(1, 1);

        // Act
        board.getHexagon(hexagon).setUnit(unit);
        Hexagon selectedHexagon = board.getHexagon(hexagon);

        // Assert
        assertEquals(selectedHexagon.getColumn(), 1);
        assertEquals(selectedHexagon.getRow(), 1);
        assertEquals(selectedHexagon.getUnit(), unit);
    }

    @Test
    void testShouldNotBeNegativeCreateBoardRowsAndColumns() {
        // Act
        Executable run = () -> new Board(-209, -209);

        // Assert
        assertThrows(AssertionError.class, run);
    }

    @Test
    void testShouldNotGetHexagonWithObjectGreaterThanBoardSize() {
        // Arrange
        Board board = new Board(42, 42);

        // Act
        Executable run = () -> board.getHexagon(new Hexagon(100, 100));

        // Assert
        assertThrows(AssertionError.class, run);
    }

    @Test
    void testShouldNotGetHexagonWithIntegersGreaterThanBoardSize() {
        // Arrange
        Board board = new Board(42, 42);

        // Act
        Executable run = () -> board.getHexagon(100, 100);

        // Assert
        assertThrows(AssertionError.class, run);
    }
}
