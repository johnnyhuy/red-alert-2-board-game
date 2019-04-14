package oosd.models.mocks;

import oosd.models.board.Piece;
import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.ArrayList;
import java.util.List;

public class MockUnitNotWinnables extends Unit {
    public MockUnitNotWinnables(Piece location) {
        super(location);
    }

    @Override
    public List<Class<? extends Unit>> getWinnables() {
        return new ArrayList<>();
    }

    @Override
    public String getName() {
        return "Mock unit with no winnables";
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
