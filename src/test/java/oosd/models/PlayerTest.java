package oosd.models;

import oosd.models.player.Player;
import oosd.models.player.Team;
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
        Team team = new Team("Red");

        // Act
        Player player = new Player(playerName, team);

        // Assert
        assertNotNull(player);
        assertEquals(playerName, player.getPlayerName());
        assertEquals(team, player.getTeam());
    }

    @Test
    void testGetPlayerTeam() {
        // Arrange
        String playerName = "John Tester";
        Team team = new Team("Red");

        // Act
        Player player = new Player(playerName, team);

        // Assert
        assertNotNull(player.getTeam());
        assertEquals(team, player.getTeam());
    }

    @Test
    void testPlayerGetUnits() {
        // Arrange
        Player player = new Player("John Tester", new Team("Red"));
        new GISoldier(player);
        new KirovAirship(player);

        // Act
        List<Unit> units = player.getUnits();

        // Assert
        assertEquals(2, units.size());
        assertNotNull(units);
    }

    @Test
    void testComparePlayers() {
        // Arrange
        Team redTeam = new Team("Red");
        Team blueTeam = new Team("Blue");
        Team cyanTeam = new Team("Cyan");
        Player firstPlayer = new Player("John Tester", redTeam);
        Player secondPlayer = new Player("Jane Tester", blueTeam);
        Player thirdPlayer = new Player("John Tester", cyanTeam);
        Player forthPlayer = new Player("John Tester", redTeam);

        // Act
        boolean differentPlayer = firstPlayer.equals(secondPlayer);
        boolean sameNameDifferentTeam = firstPlayer.equals(thirdPlayer);
        boolean sameNameAndTeam = firstPlayer.equals(forthPlayer);

        // Assert
        assertFalse(differentPlayer);
        assertFalse(sameNameDifferentTeam);
        assertTrue(sameNameAndTeam);
    }

    @Test
    void testPlayerNameShouldNotBeEmpty() {
        // Act
        Executable run = () -> new Player("", new Team("Team"));

        // Assert
        assertThrows(AssertionError.class, run);
    }

    @Test
    void testPlayersShouldNotHaveMoreThan20Units() {
        // Arrange
        Player player = new Player("John Doe", new Team("Team"));

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
