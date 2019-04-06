package oosd.models.units.humans;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;
import oosd.models.units.zombies.JuggernautZombie;
import oosd.models.units.zombies.ScoutZombie;
import oosd.models.units.zombies.Zombat;

import java.util.Arrays;
import java.util.List;

public class Plane extends Humans {
    public Plane(Player player) {
        super(player);
    }

    @Override
    public List<Class<? extends Unit>> getWinnables() {
        return Arrays.asList(Zombat.class, ScoutZombie.class, Soldier.class, JuggernautZombie.class, Tank.class);
    }

    @Override
    public String getName() {
        return "P";
    }

    @Override
    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(6);
    }
}
