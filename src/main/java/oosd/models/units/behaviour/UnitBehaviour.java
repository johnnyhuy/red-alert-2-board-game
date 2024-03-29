package oosd.models.units.behaviour;

import oosd.models.board.Piece;
import oosd.models.game.Engine;

import java.util.List;

/**
 * GRASP: open to extension, closed to modification
 * Behaviour can be extended by creating additional classes altering unit behaviour.
 */
public abstract class UnitBehaviour {
    /**
     * Get valid moves from a given piece.
     *
     * @param piece used to validate against
     * @return list of valid pieces
     */
    public abstract List<Piece> getValidMoves(Engine engine, Piece piece);

    /**
     * Check whether the given piece is valid based on the selected piece.
     *
     * @param checkPiece used to check against the selected piece valid moves
     * @return whether the piece location is a valid move
     */
    public abstract boolean isValidMove(Engine engine, Piece checkPiece);
}
