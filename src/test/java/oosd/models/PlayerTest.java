package oosd.models;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.soviet.KirovAirship;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void testCreatePlayer() {
        // Arrange
        String playerName = "John Tester";

        // Act
        Player player = new Player(playerName);

        // Assert
        assertNotNull(player);
        assertEquals(playerName, player.getPlayerName());
    }

    @Test
    void testPlayerGetUnits() {
        // Arrange
        Player player = new Player("John Tester");
        new GISoldier(player);
        new KirovAirship(player);

        // Act
        List<Unit> units = player.getAllUnits();

        // Assert
        assertEquals(2, units.size());
        assertNotNull(units);
    }

    @Test
    void testComparePlayers() {
        // Arrange
        Player firstPlayer = new Player("John Tester");
        Player secondPlayer = new Player("John Tester");
        Player thirdPlayer = new Player("Wick John");

        // Act
        boolean samePlayerName = firstPlayer.equals(secondPlayer);
        boolean differentPlayerName = firstPlayer.equals(thirdPlayer);

        // Assert
        assertTrue(samePlayerName);
        assertFalse(differentPlayerName);
    }

    @Test
    void testPlayerNameShouldNotBeEmpty() {
        // Act
        Executable run = () -> new Player("");

        // Assert
        assertThrows(AssertionError.class, run);
    }

    @Test
    void testPlayersShouldNotHaveMoreThan20Units() {
        // Arrange
        Player player = new Player("John Doe");

        for (int i = 0; i < 20; i++) {
            // Kirov reporting!
            new KirovAirship(player);
        }

        // Act
        Executable run = () -> new KirovAirship(player);

        // Assert
        assertThrows(AssertionError.class, run);
    }
}
