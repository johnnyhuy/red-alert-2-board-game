package oosd.models;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.allied.GrizzlyTank;
import oosd.models.units.allied.Harrier;
import oosd.models.units.soviet.Conscript;
import oosd.models.units.soviet.KirovAirship;
import oosd.models.units.soviet.RhinoTank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    @Test
    void testCreateZombat() {
        // Arrange
        Player player = new Player("John Tester");

        // Act
        Unit unit = new KirovAirship(player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreateTank() {
        // Arrange
        Player player = new Player("John Tester");

        // Act
        Unit unit = new GrizzlyTank(player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreateScoutZombie() {
        // Arrange
        Player player = new Player("John Tester");

        // Act
        Unit unit = new RhinoTank(player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreatePlane() {
        // Arrange
        Player player = new Player("John Tester");

        // Act
        Unit unit = new Harrier(player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreateJuggernautZombie() {
        // Arrange
        Player player = new Player("John Tester");

        // Act
        Unit unit = new Conscript(player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testCreateSoldier() {
        // Arrange
        Player player = new Player("John Tester");

        // Act
        Unit unit = new GISoldier(player);

        // Assert
        assertNotNull(unit);
        assertNotNull(unit.getPlayer());
    }

    @Test
    void testUnitGetPlayer() {
        // Arrange
        final String playerName = "John Tester";
        Player player = new Player(playerName);

        // Act
        Unit unit = new GISoldier(player);

        // Assert
        assertNotNull(unit.getPlayer());
        assertEquals(player, unit.getPlayer());
        assertEquals(playerName, unit.getPlayer().getPlayerName());
    }

    @Test
    void testWinnableUnits() {
        // Arrange
        Player player = new Player("Jane Doe");
        Unit unit = new GISoldier(player);

        // Act
        List<Class<? extends Unit>> winnables = unit.getWinnables();

        // Assert
        assertFalse(winnables.contains(KirovAirship.class));
        assertTrue(winnables.contains(Conscript.class));
    }

    @Test
    void testUnitCaptured() {
        // Arrange
        Player player = new Player("Jane Doe");
        Unit unit = new GISoldier(player);
        Unit otherUnit = new GISoldier(player);

        // Act
        unit.setCaptured(true);

        // Assert
        assertTrue(unit.getCaptured());
        assertFalse(otherUnit.getCaptured());
    }

    @Test
    void testIsWinnable() {
        // Arrange
        Player player = new Player("Jane Doe");
        Unit unit = new GISoldier(player);
        Unit otherUnit = new Conscript(player);

        // Act
        boolean isWinnable = unit.isWinnable(otherUnit);

        // Assert
        assertTrue(isWinnable);
    }

    @Test
    void testIsNotWinnable() {
        // Arrange
        Player player = new Player("Jane Doe");
        Unit unit = new GISoldier(player);
        Unit otherUnit = new RhinoTank(player);

        // Act
        boolean isWinnable = unit.isWinnable(otherUnit);

        // Assert
        assertFalse(isWinnable);
    }
}
