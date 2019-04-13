package oosd.contracts.models;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;
import oosd.models.board.Board;
import oosd.models.board.Hexagon;

import static de.vksi.c4j.Condition.*;

public class BoardContract extends Board {
    @Target
    private Board target;

    public BoardContract(int columns, int rows) {
        super(columns, rows);

        if (preCondition()) {
            assert columns > 0;
            assert rows > 0;
        }
    }

    @ClassInvariant
    public void invariant() {
        assert target.getColumns() > 0;
        assert target.getRows() > 0;
    }

    @Override
    public Hexagon getHexagon(Hexagon hexagon) {
        if (preCondition()) {
            assert hexagon.getColumn() < target.getColumns();
            assert hexagon.getRow() < target.getRows();
        }

        if (postCondition()) {
            Hexagon targetHexagon = target.getHexagon(new Hexagon(hexagon.getColumn(), hexagon.getRow()));

            assert targetHexagon.getColumn() == hexagon.getColumn();
            assert targetHexagon.getRow() == hexagon.getRow();
        }

        return ignored();
    }

    @Override
    public Hexagon getHexagon(int column, int row) {
        if (preCondition()) {
            assert column < target.getColumns();
            assert row < target.getRows();
        }

        if (postCondition()) {
            Hexagon targetHexagon = target.getHexagon(column, row);

            assert targetHexagon.getColumn() == column;
            assert targetHexagon.getRow() == row;
        }

        return ignored();
    }
}
