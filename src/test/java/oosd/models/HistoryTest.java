package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.board.history.History;
import oosd.models.game.*;
import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryTest {
    @Test
    void testUndoBoardHistoryCommand() {
        // Arrange
        Board board = new GameBoard(2, 2);
        Player player = new Player("John Tester");
        List<Player> players = Collections.singletonList(player);
        Unit unit = new GISoldier(player);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);
        board.getPiece(0, 0).setUnit(unit);
        History command = new History(engine);

        // Act
        command.backup();
        board.getPiece(0, 0).setUnit(null);
        board.getPiece(1, 0).setUnit(unit);
        command.undo();

        // Assert
        assertEquals(unit, engine.getBoard().getPiece(0, 0).getUnit());
    }

    @Test
    void testUndoMoreThanBackup() {
        // Arrange
        Board board = new GameBoard(2, 2);
        Player player = new Player("John Tester");
        List<Player> players = Collections.singletonList(player);
        Unit unit = new GISoldier(player);
        PlayerService playerService = new GamePlayerService(players);
        TurnService turnService = new GameTurnService(playerService, 10);
        Engine engine = new GameEngine(board, playerService, turnService);
        board.getPiece(0, 0).setUnit(unit);
        History command = new History(engine);

        // Act
        command.backup();
        board.getPiece(0, 0).setUnit(null);
        board.getPiece(1, 0).setUnit(unit);
        command.undo();
        command.undo();

        // Assert
        assertEquals(unit, engine.getBoard().getPiece(0, 0).getUnit());
    }
}
