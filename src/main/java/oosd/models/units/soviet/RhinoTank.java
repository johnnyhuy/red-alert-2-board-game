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

    public RhinoTank(int defendTurns) {
        super(defendTurns);
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

    public int getDefaultDefendTurns() {
        return 3;
    }

    @Override
    public Unit save() {
        return new RhinoTank(getDefendTurns());
    }
}
