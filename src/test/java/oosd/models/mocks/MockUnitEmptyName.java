package oosd.models.mocks;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.Collections;
import java.util.List;

public class MockUnitEmptyName extends Unit {
    public MockUnitEmptyName(Player player) {
        super(player);
    }

    @Override
    public List<Class<? extends Unit>> getWinnables() {
        return Collections.singletonList(MockUnitNotWinnables.class);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public UnitBehaviour getUnitBehaviour() {
        return null;
    }
}
