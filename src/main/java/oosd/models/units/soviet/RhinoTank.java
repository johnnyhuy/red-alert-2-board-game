package oosd.models.units.soviet;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.allied.GrizzlyTank;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.Arrays;
import java.util.List;

public class RhinoTank extends Soviet {
    public RhinoTank(Player player) {
        super(player);
    }

    private RhinoTank(Player player, int defendTurns) {
        super(player, defendTurns);
    }

    public List<Class<? extends Unit>> getWinnables() {
        return Arrays.asList(GISoldier.class, GrizzlyTank.class);
    }

    public String getName() {
        return "Rhino Tank";
    }

    public String getImage() {
        return "rhino_tank";
    }

    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(3);
    }

    public Unit clone() {
        return new RhinoTank(getPlayer(), getDefendTurns());
    }

    public int getDefaultDefendTurns() {
        return 3;
    }
}
