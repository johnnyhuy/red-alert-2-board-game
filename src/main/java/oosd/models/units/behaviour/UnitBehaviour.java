package oosd.models.units.behaviour;

import oosd.models.GameEngine;
import oosd.models.board.Hexagon;

import java.util.List;

/**
 * GRASP: open to extension, closed to modification
 * Behaviour can be extended by creating additional classes altering unit behaviour.
 */
public abstract class UnitBehaviour {
    /**
     * Get valid moves from a given hexagon.
     *
     * @param hexagon used to validate against
     * @return list of valid hexagons
     */
    public abstract List<Hexagon> getValidMoves(GameEngine gameEngine, Hexagon hexagon);

    /**
     * Check whether the given hexagon is valid based on the selected hexagon.
     *
     * @param checkHexagon used to check against the selected hexagon valid moves
     * @return whether the hexagon location is a valid move
     */
    public abstract boolean isValidMove(GameEngine gameEngine, Hexagon checkHexagon);
}
