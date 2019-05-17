package oosd.models.board.history;

import oosd.models.board.Board;

import java.util.Stack;

import static oosd.helpers.ListHelper.isEmpty;

/**
 * Design pattern: used the command pattern here to encapsulate specific functions
 * called from the object to manage history.
 * <p>
 * Similar to a facade, the user of this command object does not need to know how to manage
 * the history snapshot stack.
 */
public class BoardHistory {
    private final Board board;
    private Stack<Snapshot> history = new Stack<>();

    public BoardHistory(Board board) {
        this.board = board;
    }

    /**
     * Backup board history.
     */
    public void backup() {
        this.history.push(board.save());
    }

    /**
     * Undo board history.
     */
    public void undo() {
        if (isEmpty(this.history)) {
            return;
        }

        Snapshot snapshot = this.history.pop();
        board.restore(snapshot);
    }
}
