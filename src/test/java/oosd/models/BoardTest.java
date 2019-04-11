package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    @Test
    void testGetBoardColumns() {
        // Arrange
        final int expectedColumns = 6;
        Board board = new Board(expectedColumns, 6);

        // Act
        int columns = board.getColumns();

        // Assert
        assertEquals(expectedColumns, columns);
    }

    @Test
    void testGetBoardRow() {
        // Arrange
        final int expectedRows = 6;
        Board board = new Board(expectedRows, 6);

        // Act
        int rows = board.getRows();

        // Assert
        assertEquals(expectedRows, rows);
    }

    @Test
    void testGetHexagonWithObject() {
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
    void testGetHexagonWithInt() {
        // Arrange
        Board board = new Board(6, 6);

        // Act
        Hexagon selectedHexagon = board.getHexagon(1, 1);

        // Assert
        assertEquals(selectedHexagon.getColumn(), 1);
        assertEquals(selectedHexagon.getRow(), 1);
    }

    @Test
    void testGetHexagonAndSetUnit() {
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
}
