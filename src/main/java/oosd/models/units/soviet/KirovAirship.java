package oosd.models.units.soviet;

import oosd.models.board.Piece;
import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.allied.GrizzlyTank;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.Arrays;
import java.util.List;

public class KirovAirship extends Soviet {
    public KirovAirship(Piece location) {
        super(location);
    }

    @Override
    public List<Class<? extends Unit>> getWinnables() {
        return Arrays.asList(GISoldier.class, GrizzlyTank.class);
    }

    @Override
    public String getName() {
        return "Kirov Airship";
    }

    @Override
    public String getImage() {
        return "kirov_airship";
    }

    @Override
    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(5);
    }
}
