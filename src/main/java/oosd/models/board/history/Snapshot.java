package oosd.models.board.history;

public interface Snapshot<T> {
    T getState();
}
