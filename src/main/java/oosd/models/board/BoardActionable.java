package oosd.models.board;

public interface BoardActionable {
    void apply(int column, int row);
}
