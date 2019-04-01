package oosd.models;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    @Test
    void testCreateZombat() {
        // Arrange
        Hexagon hexagon = new Hexagon(1, 1);
        Player player = new Player("John Tester", Team.RED);

        // Act
        Unit unit = new Zombat(hexagon, player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getLocation());
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreateTank() {
        // Arrange
        Hexagon hexagon = new Hexagon(1, 1);
        Player player = new Player("John Tester", Team.RED);

        // Act
        Unit unit = new Tank(hexagon, player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getLocation());
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreateScoutZombie() {
        // Arrange
        Hexagon hexagon = new Hexagon(1, 1);
        Player player = new Player("John Tester", Team.RED);

        // Act
        Unit unit = new ScoutZombie(hexagon, player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getLocation());
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreatePlane() {
        // Arrange
        Hexagon hexagon = new Hexagon(1, 1);
        Player player = new Player("John Tester", Team.RED);

        // Act
        Unit unit = new Plane(hexagon, player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getLocation());
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreateJuggernautZombie() {
        // Arrange
        Hexagon hexagon = new Hexagon(1, 1);
        Player player = new Player("John Tester", Team.RED);

        // Act
        Unit unit = new JuggernautZombie(hexagon, player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getLocation());
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreateSoldier() {
        // Arrange
        Hexagon hexagon = new Hexagon(1, 1);
        Player player = new Player("John Tester", Team.RED);

        // Act
        Unit unit = new Soldier(hexagon, player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getLocation());
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testUnitGetPlayer() {
        // Arrange
        final String playerName = "John Tester";
        Hexagon hexagon = new Hexagon(1, 1);
        Player player = new Player(playerName, Team.RED);

        // Act
        Unit unit = new Soldier(hexagon, player);

        // Assert
        assertNotNull(unit.getPlayer());
        assertEquals(player, unit.getPlayer());
        assertEquals(playerName, unit.getPlayer().getPlayerName());
    }

    @Test
    void testUnitGetLocation() {
        // Arrange
        int row = 1;
        int column = 1;
        Hexagon hexagon = new Hexagon(row, column);
        Player player = new Player("Jane Doe", Team.RED);

        // Act
        Unit unit = new Soldier(hexagon, player);

        // Assert
        assertNotNull(unit.getLocation());
        assertEquals(row, unit.getLocation().getRow());
        assertEquals(column, unit.getLocation().getColumn());
    }

    @Test
    void testWinnableUnits() {
        // Arrange
        int row = 1;
        int column = 1;
        Player player = new Player("Jane Doe", Team.RED);
        Hexagon hexagon = new Hexagon(row, column);
        Unit unit = new Soldier(hexagon, player);

        // Act
        ArrayList<Class<? extends Unit>> winnables = unit.getWinnables();

        // Assert
        assertTrue(winnables.contains(Zombat.class));
        assertTrue(winnables.contains(ScoutZombie.class));
        assertFalse(winnables.contains(Soldier.class));
    }

    @Test
    void testUnitCaptured() {
        // Arrange
        int row = 1;
        int column = 1;
        Player player = new Player("Jane Doe", Team.RED);
        Hexagon hexagon = new Hexagon(row, column);
        Unit unit = new Soldier(hexagon, player);
        Unit otherUnit = new Soldier(hexagon, player);

        // Act
        unit.setCaptured(true);

        // Assert
        assertTrue(unit.getCaptured());
        assertFalse(otherUnit.getCaptured());
    }
}
