package oosd.models.units.behaviour;

import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.models.units.Unit;
import oosd.models.units.behaviour.enums.LinearDirections;

import java.util.ArrayList;
import java.util.List;

import static oosd.helpers.ObjectHelper.exists;

public class LinearUnitBehaviour extends UnitBehaviour {
    private final int moves;
    private List<Piece> validMoves;

    public LinearUnitBehaviour(int moves) {
        this.moves = moves;
        this.validMoves = new ArrayList<>();
    }

    @Override
    public List<Piece> getValidMoves(Engine engine, Piece piece) {
        Board board = engine.getBoard();

        for (LinearDirections direction : LinearDirections.values()) {
            validateDirection(engine, piece, board, direction);
        }

        return validMoves;
    }

    @Override
    public boolean isValidMove(Engine engine, Piece checkPiece) {
        for (Piece piece : getValidMoves(engine, engine.getSelected())) {
            if (piece.equals(checkPiece)) {
                return true;
            }
        }

        return false;
    }

    private void validateDirection(Engine engine, Piece piece, Board board, LinearDirections direction) {
        int columns = piece.getColumn();
        int rows = piece.getRow();
        int move = 1;
        boolean isInBoard;
        boolean enemyFound = false;

        while (move <= moves) {
            rows = direction.nextRow(columns, rows);
            columns = direction.nextColumn(columns, rows);
            isInBoard = columns < board.getColumns() && columns >= 0 && rows < board.getRows() && rows >= 0;

            if (!isInBoard || enemyFound) {
                return;
            }

            Piece validatingPiece = board.getPiece(columns, rows);
            Unit unit = validatingPiece.getUnit();
            if (exists(unit)) {
                if (unit.getPlayer().equals(engine.getTurn()) || unit.getDefendStatus(validatingPiece) || !piece.getUnit().isWinnable(unit)) {
                    return;
                } else {
                    enemyFound = true;
                }
            }

            validMoves.add(validatingPiece);
            move++;
        }
    }
}
