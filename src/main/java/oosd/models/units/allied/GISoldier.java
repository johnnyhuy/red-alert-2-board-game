package oosd.models.units.allied;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;
import oosd.models.units.soviet.Conscript;

import java.util.Collections;
import java.util.List;

public class GISoldier extends Allied {
    public GISoldier(Player player) {
        super(player);
    }

    private GISoldier(Player player, int defendTurns) {
        super(player, defendTurns);
    }

    public List<Class<? extends Unit>> getWinnables() {
        return Collections.singletonList(Conscript.class);
    }

    public String getName() {
        return "GI Soldier";
    }

    public String getImage() {
        return "gi_soldier";
    }

    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(2);
    }

    public Unit clone() {
        return new GISoldier(getPlayer(), getDefendTurns());
    }
}
