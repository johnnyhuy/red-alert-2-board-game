package oosd.models;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class HexagonTest {
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
        Player player = new Player("John Tester", new Team("Red"));
        Unit unit = new GISoldier(player);

        // Act
        hexagon.setUnit(unit);

        // Assert
        assertEquals(unit, hexagon.getUnit());
    }

    @Test
    void testShouldNotBeNegativeCreateHexagonRowsAndColumns() {
        // Act
        Executable run = () -> new Hexagon(-100, -100);

        // Assert
        assertThrows(AssertionError.class, run);
    }
}
