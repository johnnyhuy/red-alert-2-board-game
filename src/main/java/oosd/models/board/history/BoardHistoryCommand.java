package oosd.models.board.history;

import oosd.models.board.Board;

import java.util.Stack;

/**
 * Design pattern: used the command pattern here to encapsulate specific functions
 * called from the object to manage history.
 * <p>
 * Similar to a facade, the user of this command object does not need to know how to manage
 * the history snapshot stack.
 */
public class BoardHistoryCommand {
    private final Board board;
    private Stack<Snapshot> history = new Stack<>();

    public BoardHistoryCommand(Board board) {
        this.board = board;
    }

    public void backup(Snapshot snapshot) {
        this.history.push(snapshot);
    }

    public void undo() {
        Snapshot snapshot = this.history.pop();
        board.restore(snapshot);
    }
}
