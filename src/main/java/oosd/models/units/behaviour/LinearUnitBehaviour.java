package oosd.models.units.behaviour;

import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.models.units.behaviour.enums.LinearDirections;

import java.util.ArrayList;
import java.util.List;

// @invariant moves > 0
public class LinearUnitBehaviour extends UnitBehaviour {
    private final int moves;
    private List<Piece> validMoves;

    public LinearUnitBehaviour(int moves) {
        this.moves = moves;
        this.validMoves = new ArrayList<>();
    }

    @Override
    public List<Piece> getValidMoves(GameEngine gameEngine, Piece piece) {
        Board board = gameEngine.getBoard();

        for (LinearDirections direction : LinearDirections.values()) {
            int columns = piece.getColumn();
            int rows = piece.getRow();
            int move = 1;
            boolean isInBoard;

            while (move <= moves) {
                rows = direction.getRows(columns, rows);
                columns = direction.getColumns(columns, rows);
                isInBoard = columns < board.getColumns() && columns >= 0 && rows < board.getRows() && rows >= 0;

                if (!isInBoard) {
                    break;
                }

                if (board.getPiece(columns, rows).getUnit() != null) {
                    break;
                }

                validMoves.add(board.getPiece(columns, rows));
                move++;
            }
        }

        return validMoves;
    }

    @Override
    public boolean isValidMove(GameEngine gameEngine, Piece checkPiece) {
        for (Piece piece : getValidMoves(gameEngine, gameEngine.getSelectedPiece())) {
            if (piece.equals(checkPiece)) {
                return true;
            }
        }

        return false;
    }
}
