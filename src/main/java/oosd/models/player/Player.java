package oosd.models.player;

import com.sun.istack.Nullable;
import de.vksi.c4j.ContractReference;
import oosd.contracts.models.PlayerContract;
import oosd.models.board.Piece;
import oosd.models.units.Unit;

import java.util.ArrayList;
import java.util.List;

@ContractReference(PlayerContract.class)
public class Player {
    private String playerName;
    private Team team;
    private List<Unit> units;

    public Player(String playerName, Team team) {
        this.playerName = playerName;
        this.team = team;
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
     * Get the team of the player
     *
     * @return team of player
     */
    public Team getTeam() {
        return this.team;
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

        return player.getTeam() == getTeam() && player.getPlayerName().equals(getPlayerName());
    }

    /**
     * Get player unit based on piece location.
     *
     * @param location piece object
     * @return unit object
     */
    public Unit getUnit(Piece location) {
        for (Unit unit : units) {
            if (unit.getLocation().equals(location)) {
                return unit;
            }
        }

        return null;
    }

    /**
     * Get player unit based on integer coordinates.
     *
     * @param column integer
     * @param row integer
     * @return unit object
     */
    @Nullable
    public Unit getUnit(int column, int row) {
        for (Unit unit : units) {
            if (unit.getLocation().equals(new Piece(column, row))) {
                return unit;
            }
        }

        return null;
    }
}
