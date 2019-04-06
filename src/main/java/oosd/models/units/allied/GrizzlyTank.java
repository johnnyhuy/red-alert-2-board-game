package oosd.models.units.allied;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;
import oosd.models.units.soviet.Conscript;
import oosd.models.units.soviet.KirovAirship;
import oosd.models.units.soviet.RhinoTank;

import java.util.Arrays;
import java.util.List;

public class GrizzlyTank extends Allied {
    public GrizzlyTank(Player player) {
        super(player);
    }

    @Override
    public List<Class<? extends Unit>> getWinnables() {
        return Arrays.asList(KirovAirship.class, RhinoTank.class, GISoldier.class, Conscript.class);
    }

    @Override
    public String getName() {
        return "Grizzly Tank";
    }

    @Override
    public String getImage() {
        return "grizzly_tank";
    }

    @Override
    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(2);
    }
}
