package oosd.models.units.zombies;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.Collections;
import java.util.List;

public class ScoutZombie extends Zombie {
    public ScoutZombie(Player player) {
        super(player);
    }

    @Override
    public List<Class<? extends Unit>> getWinnables() {
        return Collections.singletonList(Zombat.class);
    }

    @Override
    public String getName() {
        return "SZ";
    }

    @Override
    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(3);
    }
}
