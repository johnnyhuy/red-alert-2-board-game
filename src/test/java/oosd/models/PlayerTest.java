package oosd.models;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.JuggernautZombie;
import oosd.models.units.Soldier;
import oosd.models.units.Unit;
import oosd.models.units.Zombat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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
    void testPlayerAddUnit() {
        // Arrange
        Hexagon hexagon = new Hexagon(1, 1);
        Player player = new Player("John Tester", Team.RED);
        Unit soldier = new Soldier(hexagon, player);
        Unit zombat = new Zombat(hexagon, player);

        // Act
        player.addUnit(zombat);
        player.addUnit(soldier);

        // Assert
        assertEquals(2, player.getUnits().size());
        assertNotNull(player.getUnits());
    }

    @Test
    void testThrowExceptionOnSameLocationUnits() {
        // Arrange
        int row = 1;
        int column = 1;
        Hexagon hexagon = new Hexagon(row, column);
        Player player = new Player("Jane Doe", Team.RED);
        Unit firstUnit = new Soldier(hexagon, player);
        Unit secondUnit = new JuggernautZombie(hexagon, player);
        player.addUnit(firstUnit);

        // Act
        Executable addAnotherUnit = () -> player.addUnit(secondUnit);

        // Assert
        assertThrows(IllegalArgumentException.class, addAnotherUnit);
    }
}
