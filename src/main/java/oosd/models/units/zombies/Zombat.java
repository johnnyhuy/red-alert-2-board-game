package oosd.models.units.zombies;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;
import oosd.models.units.humans.Soldier;
import oosd.models.units.humans.Tank;

import java.util.Arrays;
import java.util.List;

public class Zombat extends Zombie {
    public Zombat(Player player) {
        super(player);
    }

    @Override
    public List<Class<? extends Unit>> getWinnables() {
        return Arrays.asList(Soldier.class, Tank.class);
    }

    @Override
    public String getName() {
        return "Zombat";
    }

    @Override
    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(5);
    }
}
