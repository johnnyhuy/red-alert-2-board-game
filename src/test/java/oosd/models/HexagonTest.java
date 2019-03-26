package oosd.models;

import oosd.models.board.Hexagon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
