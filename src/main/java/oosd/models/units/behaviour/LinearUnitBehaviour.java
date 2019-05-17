package oosd.models.units.behaviour;

import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Piece;
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
    public List<Piece> getValidMoves(GameEngine gameEngine, Piece piece) {
        Board board = gameEngine.getBoard();

        for (LinearDirections direction : LinearDirections.values()) {
            validateDirection(gameEngine, piece, board, direction);
        }

        return validMoves;
    }

    private void validateDirection(GameEngine gameEngine, Piece piece, Board board, LinearDirections direction) {
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

            Unit unit = board.getPiece(columns, rows).getUnit();
            if (exists(unit)) {
                if (unit.getPlayer().equals(gameEngine.getTurn()) || unit.getDefendStatus()) {
                    return;
                } else {
                    enemyFound = true;
                }
            }

            validMoves.add(board.getPiece(columns, rows));
            move++;
        }
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
