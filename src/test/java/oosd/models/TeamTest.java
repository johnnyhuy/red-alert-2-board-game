package oosd.models;

import oosd.models.player.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void testGetNameShouldNotBeEmpty() {
        // Arrange
        String expectedTeamName = "";

        // Act
        Executable run = () -> new Team(expectedTeamName);

        // Assert
        assertThrows(AssertionError.class, run);
    }
}
