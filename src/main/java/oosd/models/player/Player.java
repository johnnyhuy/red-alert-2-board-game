package oosd.models.player;

import oosd.models.units.Unit;

import java.util.List;

public class Player {
    private String playerName;
    private Team team;
    private List<Unit> units;

    public Player(String playerName, Team team) {
        super();
        this.playerName = playerName;
        this.team = team;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public Team getTeam() {
        return this.team;
    }

    public List<Unit> getUnits() {
        return this.units;
    }

    public void addUnit(Unit newUnit) throws IllegalArgumentException {
        for (Unit unit : this.units) {
//            if (unit.getLocation())
        }

//        this.units.add(unit);
    }
}
