package oosd.models.board.history;

import oosd.models.board.Board;
import oosd.models.player.Player;

import java.util.Collection;
import java.util.Stack;

import static oosd.helpers.ListHelper.isEmpty;

/**
 * Design pattern: used the command pattern here to encapsulate specific functions
 * called from the object to manage history.
 * <p>
 * Similar to a facade, the user of this command object does not need to know how to manage
 * the history snapshot stack.
 */
public class History {
    private final Board board;
    private Collection<Player> players;
    private Stack<Snapshot> history = new Stack<>();

    public History(Board board, Collection<Player> players) {
        this.board = board;
        this.players = players;
    }

    /**
     * Backup board history.
     */
    public void backup() {
        this.history.push(board.save(players));
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

    /**
     * Go back to the very start of the snapshot.
     */
    public void reset() {
        board.restore(this.history.firstElement());
    }
}
