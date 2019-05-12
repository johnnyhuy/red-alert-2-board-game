package oosd.models.units.soviet;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.Arrays;
import java.util.List;

public class Conscript extends Soviet {
    public Conscript(Player player) {
        super(player);
    }

    public List<Class<? extends Unit>> getWinnables() {
        return Arrays.asList(KirovAirship.class, RhinoTank.class, GISoldier.class);
    }

    public String getName() {
        return "Conscript";
    }

    public String getImage() {
        return "conscript";
    }

    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(1);
    }
}
