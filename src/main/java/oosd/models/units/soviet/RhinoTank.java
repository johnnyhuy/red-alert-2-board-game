package oosd.models.units.soviet;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.Collections;
import java.util.List;

public class RhinoTank extends Soviet {
    public RhinoTank(Player player) {
        super(player);
        super.setImage("Rhino Tank");
        super.setName("Rhino Tank");  
    }

    @Override
    public List<Class<? extends Unit>> getWinnables() {
        return Collections.singletonList(KirovAirship.class);
    }

    @Override
    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(3);
    }
}
