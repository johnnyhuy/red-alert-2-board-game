package oosd.models.player;

import oosd.models.units.Unit;

import java.util.List;

public class Player {
    private String playerName;
    private Team team;
    private List<Unit> units;

    public Player(String playerName, Team team, List<Unit> units) {
        super();
        this.playerName = playerName;
        this.team = team;
        this.units = units;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Team getTeam() {
        return team;
    }

    public List<Unit> getUnits() {
        return units;
    }
}
