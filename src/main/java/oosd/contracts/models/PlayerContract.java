package oosd.contracts.models;

import de.vksi.c4j.Target;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.Unit;

import static de.vksi.c4j.Condition.*;

public class PlayerContract extends Player {
    @Target
    private Player target;

    public PlayerContract(String playerName, Team team) {
        super(playerName, team);

        if (preCondition()) {
            assert !playerName.isEmpty();
        }
    }

    @Override
    public void addUnit(Unit newUnit) {
        if (preCondition()) {
            assert target.getUnits().size() < 20;
        }

        if (postCondition()) {
            assert target.getUnits().size() == old(target.getUnits().size() + 1);
        }
    }
}
