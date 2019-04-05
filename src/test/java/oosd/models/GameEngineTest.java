package oosd.models;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.Soldier;
import oosd.models.units.Unit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {
    @Test
    void testCheckBoardExistsOnGameEngine() {
        // Act
        GameEngine gameEngine = new GameEngine(12, 12);

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
        GameEngine gameEngine = new GameEngine(columns, rows);

        // Assert
        assertEquals(gameEngine.getBoard().getRows(), rows);
        assertEquals(gameEngine.getBoard().getColumns(), columns);
        assertNotNull(gameEngine.getBoard().getHexagon(columns - 1, rows - 1));
    }

    @Test
    void testSetAndGetSelectedHexagon() {
        // Arrange
        Hexagon expectedHexagon = new Hexagon(1, 1);
        GameEngine gameEngine = new GameEngine(2, 2);

        // Act
        gameEngine.setSelectedHexagon(expectedHexagon);
        Hexagon selectedHexagon = gameEngine.getSelectedHexagon();

        // Assert
        assertEquals(expectedHexagon, selectedHexagon);
    }

    @Test
    void testIsInvalidMoveWhenUnitExists() {
        // Arrange
        Player player = new Player("John Tester", Team.RED);
        Unit unit = new Soldier(player);
        GameEngine gameEngine = new GameEngine(2, 2);
        Hexagon unitHexagon = gameEngine.getBoard().getHexagon(0, 0);
        Hexagon selectedHexagon = gameEngine.getBoard().getHexagon(0, 1);
        unitHexagon.setUnit(unit);
        selectedHexagon.setUnit(unit);
        gameEngine.setSelectedHexagon(selectedHexagon);

        // Act
        boolean isValidMove = gameEngine.isValidMove(unitHexagon);

        // Assert
        assertFalse(isValidMove);
    }

    @Test
    void testGetValidMoves() {
        // Arrange
        Player player = new Player("John Tester", Team.RED);
        Unit unit = new Soldier(player);
        GameEngine gameEngine = new GameEngine(2, 2);
        Hexagon hexagon = gameEngine.getBoard().getHexagon(0, 1);
        hexagon.setUnit(unit);

        // Act
        List<Hexagon> validMove = gameEngine.getValidMoves(hexagon);

        // Assert
        assertTrue(validMove.size() > 0);
    }

    @Test
    void testGetValidMovesUnitDoesNotExist() {
        // Arrange
        GameEngine gameEngine = new GameEngine(2, 2);
        Hexagon hexagon = gameEngine.getBoard().getHexagon(0, 1);

        // Act
        Executable action = () -> gameEngine.getValidMoves(hexagon);

        // Assert
        Exception exception = assertThrows(NullPointerException.class, action);
        assertEquals("Selected hexagon must have a unit to check move validation.", exception.getMessage());
    }
}
