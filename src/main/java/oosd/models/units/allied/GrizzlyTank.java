package oosd.models.units.allied;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;
import oosd.models.units.soviet.Conscript;
import oosd.models.units.soviet.RhinoTank;

import java.util.Arrays;
import java.util.List;

public class GrizzlyTank extends Allied {
    public GrizzlyTank(Player player) {
        super(player);
    }

    private GrizzlyTank(Player player, int defendTurns) {
        super(player, defendTurns);
    }

    public List<Class<? extends Unit>> getWinnables() {
        return Arrays.asList(RhinoTank.class, Conscript.class);
    }

    public String getName() {
        return "Grizzly Tank";
    }

    public String getImage() {
        return "grizzly_tank";
    }

    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(2);
    }

    public Unit clone() {
        return new GrizzlyTank(getPlayer(), getDefendTurns());
    }
}
