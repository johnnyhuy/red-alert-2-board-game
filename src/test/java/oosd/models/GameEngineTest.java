package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.Unit;
import oosd.models.units.humans.Soldier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {
    @Test
    void testCheckBoardExistsOnGameEngine() {
        // Act
        Board board = new Board(2, 2);
        GameEngine gameEngine = new GameEngine(board, new ArrayList<>());

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
        Board board = new Board(2, 2);
        GameEngine gameEngine = new GameEngine(board, new ArrayList<>());

        // Assert
        assertEquals(gameEngine.getBoard().getRows(), rows);
        assertEquals(gameEngine.getBoard().getColumns(), columns);
        assertNotNull(gameEngine.getBoard().getHexagon(columns - 1, rows - 1));
    }

    @Test
    void testSetAndGetSelectedHexagon() {
        // Arrange
        Hexagon expectedHexagon = new Hexagon(1, 1);
        Board board = new Board(2, 2);
        GameEngine gameEngine = new GameEngine(board, new ArrayList<>());

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
        Board board = new Board(2, 2);
        GameEngine gameEngine = new GameEngine(board, new ArrayList<>());
        Hexagon unitHexagon = gameEngine.getBoard().getHexagon(0, 0);
        Hexagon selectedHexagon = gameEngine.getBoard().getHexagon(0, 1);
        unitHexagon.setUnit(unit);
        selectedHexagon.setUnit(unit);
        gameEngine.setSelectedHexagon(selectedHexagon);

        // Act
        boolean isValidMove = unitHexagon.getUnit().getUnitBehaviour().isValidMove(gameEngine, unitHexagon);

        // Assert
        assertFalse(isValidMove);
    }

    @Test
    void testGetValidMoves() {
        // Arrange
        Player player = new Player("John Tester", Team.RED);
        Unit unit = new Soldier(player);
        Board board = new Board(2, 2);
        GameEngine gameEngine = new GameEngine(board, new ArrayList<>());
        Hexagon hexagon = gameEngine.getBoard().getHexagon(0, 1);
        hexagon.setUnit(unit);

        // Act
        List<Hexagon> validMove = hexagon.getUnit().getUnitBehaviour().getValidMoves(gameEngine, hexagon);

        // Assert
        assertTrue(validMove.size() > 0);
    }

    @Test
    void testGetFirstTurnOnFirstPlayer() {
        // Arrange
        Board board = new Board(2, 2);
        List<Player> players = new ArrayList<>();
        players.add(new Player("John Tester", Team.RED));
        GameEngine gameEngine = new GameEngine(board, players);

        // Act
        Player player = gameEngine.getTurn();

        // Assert
        assertNotNull(player);
    }

    @Test
    void testGetNextTurn() {
        // Arrange
        Player playerOne = new Player("Johnny Dave", Team.RED);
        Player playerTwo = new Player("Jane Doe", Team.BLUE);
        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        Board board = new Board(2, 2);
        GameEngine gameEngine = new GameEngine(board, players);

        // Act
        Player firstTurn = gameEngine.getTurn();
        Player secondTurn = gameEngine.getNextTurn();
        Player thirdTurn = gameEngine.getNextTurn();
        Player forthTurn = gameEngine.getNextTurn();

        // Assert
        assertEquals(playerOne, firstTurn);
        assertEquals(playerTwo, secondTurn);
        assertEquals(playerOne, thirdTurn);
        assertEquals(playerTwo, forthTurn);
    }

    @Test
    void testGetPlayers() {
        // Arrange
        Player playerOne = new Player("Johnny Dave", Team.RED);
        Player playerTwo = new Player("Jane Doe", Team.BLUE);
        List<Player> expectedPlayers = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        Board board = new Board(2, 2);
        GameEngine gameEngine = new GameEngine(board, expectedPlayers);

        // Act
        List<Player> actualPlayers = gameEngine.getPlayers();

        // Assert
        assertEquals(expectedPlayers, actualPlayers);
        assertEquals(2, actualPlayers.size());
    }
}
