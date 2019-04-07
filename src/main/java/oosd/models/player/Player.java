package oosd.models.player;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import oosd.models.units.Unit;

import java.util.ArrayList;
import java.util.List;

@Invariant({"playerName != null", "team != null", "units.size() >= 0"})
public class Player {
    private String playerName;
    private Team team;
    private List<Unit> units;

    public Player(String playerName, Team team) {
        this.playerName = playerName;
        this.team = team;
        this.units = new ArrayList<>();
    }

    @Ensures("playerName.size() > 0")
    public String getPlayerName() {
        return this.playerName;
    }

    public Team getTeam() {
        return this.team;
    }

    public List<Unit> getUnits() {
        return this.units;
    }

    @Ensures("Unit.size() == old (Unit.size()) + 1")
    public void addUnit(Unit newUnit) {
        this.units.add(newUnit);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Player)) {
            return false;
        }

        Player player = (Player) object;

        return player.getTeam() == getTeam() && player.getPlayerName() == getPlayerName();
    }
}
