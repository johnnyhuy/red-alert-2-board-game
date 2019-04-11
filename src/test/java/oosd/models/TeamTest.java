package oosd.models;

import oosd.models.player.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamTest {
    @Test
    void testGetTeamName() {
        // Arrange
        String expectedTeamName = "Super Team";
        Team team = new Team(expectedTeamName);

        // Act
        String actualTeamName = team.getName();

        // Assert
        assertEquals(expectedTeamName, actualTeamName);
    }
}
