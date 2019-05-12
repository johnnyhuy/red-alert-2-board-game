package oosd.models.player;

import de.vksi.c4j.ContractReference;
import oosd.contracts.models.PlayerContract;
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

        return player.getTeam() == getTeam() && player.getPlayerName().equals(getPlayerName());
    }
}
