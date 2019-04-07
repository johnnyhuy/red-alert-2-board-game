package oosd.models;

import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.soviet.KirovAirship;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void testCreatePlayer() {
        // Arrange
        String playerName = "John Tester";
        Team team = Team.RED;

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
        Team team = Team.RED;

        // Act
        Player player = new Player(playerName, team);

        // Assert
        assertNotNull(player.getTeam());
        assertEquals(team, player.getTeam());
    }

    @Test
    void testPlayerGetUnits() {
        // Arrange
        Player player = new Player("John Tester", Team.RED);
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
        Player firstPlayer = new Player("John Tester", Team.RED);
        Player secondPlayer = new Player("Jane Tester", Team.BLUE);
        Player thirdPlayer = new Player("John Tester", Team.BLUE);
        Player forthPlayer = new Player("John Tester", Team.RED);

        // Act
        boolean differentPlayer = firstPlayer.equals(secondPlayer);
        boolean sameNameDifferentTeam = firstPlayer.equals(thirdPlayer);
        boolean sameNameAndTeam = firstPlayer.equals(forthPlayer);

        // Assert
        assertFalse(differentPlayer);
        assertFalse(sameNameDifferentTeam);
        assertTrue(sameNameAndTeam);
    }
}
