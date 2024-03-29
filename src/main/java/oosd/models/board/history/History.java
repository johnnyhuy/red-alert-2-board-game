package oosd.models.board.history;

import oosd.models.game.Engine;

import java.util.Stack;

import static oosd.helpers.ListHelper.isEmpty;
import static oosd.helpers.ObjectHelper.exists;

/**
 * Design pattern: used the command pattern here to encapsulate specific functions
 * called from the object to manage history.
 * <p>
 * Similar to a facade, the user of this command object does not need to know how to manage
 * the history snapshot stack.
 */
public class History {
    private Stack<Snapshot> history = new Stack<>();
    private final Engine engine;
    private Snapshot savedSnapshot;

    public History(Engine engine) {
        this.engine = engine;
    }

    /**
     * Whether save snapshot exists.
     *
     * @return boolean
     */
    public boolean saveExists() {
        return exists(savedSnapshot);
    }

    /**
     * Save a the start of the stack.
     */
    public void save() {
        this.savedSnapshot = engine.save();
    }

    /**
     * Restore from a saved snapshot.
     */
    public void restore() {
        engine.restore(this.savedSnapshot);
        this.savedSnapshot = null;
    }

    /**
     * Backup board history.
     */
    public void backup() {
        this.history.push(engine.save());
    }

    /**
     * Undo board history.
     */
    public void undo() {
        if (isEmpty(this.history)) {
            return;
        }

        Snapshot snapshot = this.history.pop();
        engine.restore(snapshot);
    }

    /**
     * Go back to the very start of the snapshot.
     */
    public void reset() {
        if (this.history.empty()) {
            return;
        }

        engine.restore(this.history.firstElement());

        while (!this.history.empty()) {
            this.history.pop();
        }
    }
}
