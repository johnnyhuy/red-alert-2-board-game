package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.Soldier;
import oosd.models.units.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void testCompareLocationsHexagon() {
        // Arrange
        Hexagon hexagonOne = new Hexagon(1, 1);
        Hexagon hexagonTwo = new Hexagon(1, 1);
        Hexagon hexagonThree = new Hexagon(2, 5);

        // Act
        boolean shouldEqual = hexagonOne.equals(hexagonTwo);
        boolean shouldNotEqual = hexagonOne.equals(hexagonThree);

        // Assert
        assertTrue(shouldEqual);
        assertFalse(shouldNotEqual);
    }

    @Test
    void testGetHexagonLocation() {
        // Arrange
        Hexagon hexagon = new Hexagon(1, 1);

        // Act
        int row = hexagon.getRow();
        int column = hexagon.getColumn();

        // Assert
        assertEquals(1, row);
        assertEquals(1, column);
    }

    @Test
    void testGetHexagonPlayer() {
        // Arrange
        Hexagon hexagon = new Hexagon(1, 1);
        Player player = new Player("John Tester", Team.RED);
        Unit unit = new Soldier(player);

        // Act
        hexagon.setUnit(unit);

        // Assert
        assertEquals(unit, hexagon.getUnit());
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
    void testGetHexagonSetUnit() {
        // Arrange
        Player player = new Player("John Tester", Team.RED);
        Unit unit = new Soldier(player);
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
