package oosd.models.units.behaviour;

import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.units.behaviour.enums.LinearDirections;

import java.util.ArrayList;
import java.util.List;

// @invariant moves > 0
public class LinearUnitBehaviour extends UnitBehaviour {
    private final int moves;
    private List<Hexagon> validMoves;

    public LinearUnitBehaviour(int moves) {
        this.moves = moves;
        this.validMoves = new ArrayList<>();
    }

    @Override
    public List<Hexagon> getValidMoves(GameEngine gameEngine, Hexagon hexagon) {
        Board board = gameEngine.getBoard();

        for (LinearDirections direction : LinearDirections.values()) {
            int columns = hexagon.getColumn();
            int rows = hexagon.getRow();
            int move = 1;
            boolean isInBoard;

            while (move <= moves) {
                rows = direction.getRows(columns, rows);
                columns = direction.getColumns(columns, rows);
                isInBoard = columns < board.getColumns() && columns >= 0 && rows < board.getRows() && rows >= 0;

                if (!isInBoard) {
                    break;
                }

                if (board.getHexagon(columns, rows).getUnit() != null) {
                    break;
                }

                validMoves.add(new Hexagon(columns, rows));
                move++;
            }
        }

        return validMoves;
    }

    @Override
    public boolean isValidMove(GameEngine gameEngine, Hexagon checkHexagon) {
        for (Hexagon hexagon : getValidMoves(gameEngine, gameEngine.getSelectedHexagon())) {
            if (hexagon.equals(checkHexagon)) {
                return true;
            }
        }

        return false;
    }
}
