package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.board.history.BoardHistory;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardHistoryTest {
    @Test
    void testUndoBoardHistoryCommand() {
        // Arrange
        Board board = new GameBoard(2, 2);
        Player player = new Player("John Tester", new Team("Red"));
        Unit unit = new GISoldier(player);
        board.getPiece(0, 0).setUnit(unit);
        BoardHistory command = new BoardHistory(board);

        // Act
        command.backup();
        board.getPiece(0, 0).setUnit(null);
        board.getPiece(1, 0).setUnit(unit);
        command.undo();

        // Assert
        assertEquals(unit, board.getPiece(0, 0).getUnit());
    }

    @Test
    void testUndoMoreThanBackup() {
        // Arrange
        Board board = new GameBoard(2, 2);
        Player player = new Player("John Tester", new Team("Red"));
        Unit unit = new GISoldier(player);
        board.getPiece(0, 0).setUnit(unit);
        BoardHistory command = new BoardHistory(board);

        // Act
        command.backup();
        board.getPiece(0, 0).setUnit(null);
        board.getPiece(1, 0).setUnit(unit);
        command.undo();
        command.undo();

        // Assert
        assertEquals(unit, board.getPiece(0, 0).getUnit());
    }
}
