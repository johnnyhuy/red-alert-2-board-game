package oosd.contracts.models;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;
import oosd.models.board.Piece;

import static de.vksi.c4j.Condition.preCondition;

public class PieceContract extends Piece {
    @Target
    private Piece target;

    public PieceContract(int columns, int rows) {
        super(columns, rows);

        if (preCondition()) {
            assert columns >= 0;
            assert rows >= 0;
        }
    }

    @ClassInvariant
    public void invariant() {
        assert target.getColumn() >= 0;
        assert target.getRow() >= 0;
    }
}
