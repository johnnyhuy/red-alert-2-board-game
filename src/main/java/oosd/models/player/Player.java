package oosd.models.player;

import de.vksi.c4j.ContractReference;
import oosd.contracts.models.PlayerContract;
import oosd.models.Savable;
import oosd.models.units.Unit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

import static oosd.helpers.ObjectHelper.isNull;

@ContractReference(PlayerContract.class)
public class Player implements Savable<Player> {
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

    public Player(String playerName, int undoMoves, int turns, int wins, int losses) {
        this(playerName);
        this.undoMoves = undoMoves;
        this.turns = turns;
        this.wins = wins;
        this.losses = losses;
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
        List<Unit> aliveUnits = new ArrayList<>();

        for (Unit unit : this.units) {
            if (unit.isCaptured()) {
                continue;
            }

            aliveUnits.add(unit);
        }

        return aliveUnits;
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
        } else if (object == this)
            return true;

        Player player = (Player) object;

        return this.equals(player);
    }

    /**
     * Compare players.
     *
     * @param player object
     * @return whether the player is truly equal
     */
    public boolean equals(Player player) {
        if (isNull(player)) {
            return false;
        }

        return new EqualsBuilder()
                .append(this.playerName, player.getPlayerName())
                .isEquals();
    }

    /**
     * Produce a hash code for the object.
     *
     * @return hash code integer
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(this.playerName)
                .toHashCode();
    }

    /**
     * Set the player if they can undo.
     *
     * @param canUndo boolean
     */
    public void setCanUndo(boolean canUndo) {
        this.canUndo = canUndo;
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
    public void setUndoMoves(int moves) {
        undoMoves = moves;
    }

    /**
     * Get the undo status.
     *
     * @return boolean to check undo status
     */
    public boolean canUndo() {
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
     * Set the turns.
     *
     * @param turns number of turns
     */
    public void setTurns(int turns) {
        this.turns = turns;
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

    /**
     * Set the wins.
     *
     * @param wins number of wins
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Set the number of losses.
     *
     * @param losses number of losses
     */
    public void setLosses(int losses) {
        this.losses = losses;
    }

    @Override
    public Player save() {
        return new Player(playerName, undoMoves, turns, wins, losses);
    }
}
