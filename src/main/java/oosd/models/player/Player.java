package oosd.models.player;

import oosd.models.units.Unit;

import java.util.ArrayList;
import java.util.List;
import static org.valid4j.Assertive.*;

public class Player {
    private String playerName;
    private Team team;
    private List<Unit> units;

    public Player(String playerName, Team team) {
        this.playerName = playerName;
        this.team = team;
        this.units = new ArrayList<>();
        
     // @Invariant
        assert units.size() > 0 : "Cannot have less than 1 unit.";
        assert playerName.length() >= 0 : "Player name doesn't exist.";
    }

    /**
     * Get the player name
     *
     * @return string of the player name
     */
    public String getPlayerName() {
        // @post.condition
    	require(playerName.length() > 0);
    	
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
        // @Pre-condition
    	ensure(units.size() < 20);
    	
    	int oldSize = units.size();
    	
        this.units.add(newUnit);       
        
        // @Post-condition
        require(units.size() == oldSize + 1);
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
