package oosd.models.units.soviet;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.allied.GrizzlyTank;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.Arrays;
import java.util.List;

public class KirovAirship extends Soviet {
    public KirovAirship(Player player) {
        super(player);
        super.setImage("Kirov Airship");
        super.setName("Kirov Airship");  
    }

    @Override
    public List<Class<? extends Unit>> getWinnables() {
        return Arrays.asList(GISoldier.class, GrizzlyTank.class);
    }

    @Override
    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(5);
    }
}
