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
    }

    public List<Class<? extends Unit>> getWinnables() {
        return Arrays.asList(GISoldier.class, GrizzlyTank.class);
    }

    public String getName() {
        return "Kirov Airship";
    }

    public String getImage() {
        return "kirov_airship";
    }

    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(5);
    }

    public int getDefaultDefendCount() {
        return 1;
    }
}
