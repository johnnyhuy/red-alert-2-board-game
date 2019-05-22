package oosd.models.player;

import de.vksi.c4j.ContractReference;
import oosd.contracts.models.PlayerContract;
import oosd.models.units.Unit;

import java.util.ArrayList;
import java.util.List;

@ContractReference(PlayerContract.class)
public class Player {
    private String playerName;
    private List<Unit> units;
    private int undoMoves = 0;
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
    public List<Unit> getUnits() {
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

    public int getUndoMoves() {
        return undoMoves;
    }

    public void incrementUndoMoves() {
        undoMoves++;
    }

    public boolean getUndoStatus() {
        return undoMoves < 3 && canUndo;
    }

    public void updateUndoStatus() {
        if (undoMoves > 0) {
            canUndo = false;
        }
    }
}
