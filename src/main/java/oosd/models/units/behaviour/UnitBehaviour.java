package oosd.models.units.behaviour;

import oosd.models.GameEngine;
import oosd.models.board.Piece;
import oosd.models.units.Unit;

import java.util.List;

/**
 * GRASP: open to extension, closed to modification
 * Behaviour can be extended by creating additional classes altering unit behaviour.
 */
public abstract class UnitBehaviour {
    /**
     * Get valid moves from a given piece.
     *
     * @param unit object
     * @return list of valid pieces
     */
    public abstract List<Piece> getValidMoves(GameEngine gameEngine, Unit unit);

    /**
     * Check whether the given piece is valid based on the selected piece.
     *
     * @param checkPiece used to check against the selected piece valid moves
     * @return whether the piece location is a valid move
     */
    public abstract boolean isValidMove(GameEngine gameEngine, Piece checkPiece);
}
