package oosd.models;

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
        Player player = new Player("John Tester", Team.RED);

        // Act
        Unit unit = new Zombat(player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreateTank() {
        // Arrange
        Player player = new Player("John Tester", Team.RED);

        // Act
        Unit unit = new Tank(player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreateScoutZombie() {
        // Arrange
        Player player = new Player("John Tester", Team.RED);

        // Act
        Unit unit = new ScoutZombie(player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreatePlane() {
        // Arrange
        Player player = new Player("John Tester", Team.RED);

        // Act
        Unit unit = new Plane(player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreateJuggernautZombie() {
        // Arrange
        Player player = new Player("John Tester", Team.RED);

        // Act
        Unit unit = new JuggernautZombie(player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreateSoldier() {
        // Arrange
        Player player = new Player("John Tester", Team.RED);

        // Act
        Unit unit = new Soldier(player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testUnitGetPlayer() {
        // Arrange
        final String playerName = "John Tester";
        Player player = new Player(playerName, Team.RED);

        // Act
        Unit unit = new Soldier(player);

        // Assert
        assertNotNull(unit.getPlayer());
        assertEquals(player, unit.getPlayer());
        assertEquals(playerName, unit.getPlayer().getPlayerName());
    }

    @Test
    void testWinnableUnits() {
        // Arrange
        Player player = new Player("Jane Doe", Team.RED);
        Unit unit = new Soldier(player);

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
        Player player = new Player("Jane Doe", Team.RED);
        Unit unit = new Soldier(player);
        Unit otherUnit = new Soldier(player);

        // Act
        unit.setCaptured(true);

        // Assert
        assertTrue(unit.getCaptured());
        assertFalse(otherUnit.getCaptured());
    }
}
