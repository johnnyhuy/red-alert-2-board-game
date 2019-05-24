package oosd.models.player;

import oosd.models.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String playerName;
    private List<Unit> units;
    private int undoMoves = 0;
    private int turns = 0;
    private int wins = 0;
    private int losses = 0;
    private boolean canUndo = true;

    public Player(String playerName) {
        this.playerName = playerName;
        this.units = new ArrayList<>();
    }

    /**
     * Get the player name
     *
     * @return string of the player name
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Get a list of units.
     *
     * @return list of units
     */
    public List<Unit> getAllUnits() {
        return this.units;
    }

    /**
     * Get alive units.
     *
     * @return number of alive units
     */
    public List<Unit> getAliveUnits() {
        return this.units;
    }

    /**
     * Add units to the player.
     *
     * Design pattern: composite pattern used to add multiple implementations of a unit.
     *
     * @param newUnit to be added to the list of units
     */
    public void addUnit(Unit newUnit) {
        this.units.add(newUnit);
    }

    /**
     * Compare players by their team and name
     *
     * @param object to compare to the player
     * @return whether the player is equal
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Player)) {
            return false;
        }

        Player player = (Player) object;

        return player.getPlayerName().equals(getPlayerName());
    }

    /**
     * Get the amount of moves to undo.
     *
     * @return number of undo moves
     */
    public int getUndoMoves() {
        return undoMoves;
    }

    /**
     * Increment undo moves.
     */
    public void incrementUndoMoves() {
        undoMoves++;
    }

    /**
     * Get the undo status.
     *
     * @return boolean to check undo status
     */
    public boolean getUndoStatus() {
        return undoMoves < 3 && canUndo;
    }

    /**
     * Update undo status.
     */
    public void updateUndoStatus() {
        if (undoMoves > 0) {
            canUndo = false;
        }
    }

    /**
     * Get the players turns.
     *
     * @return amount of turns
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Increment a turn to the player.
     */
    public void incrementTurn() {
        turns++;
    }

    /**
     * Get player wins
     *
     * @return number of wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Increment wins
     */
    public void incrementWin() {
        this.wins++;
    }

    /**
     * Get losses
     *
     * @return number of losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Increment losses.
     */
    public void incrementLoss() {
        this.losses++;
    }
}
