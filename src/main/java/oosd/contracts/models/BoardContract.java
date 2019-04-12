package oosd.contracts.models;

import de.vksi.c4j.Target;
import oosd.models.board.Board;

public class BoardContract extends Board {
    @Target
    private Board target;

    public BoardContract(int columns, int rows) {
        super(columns, rows);
    }
}
