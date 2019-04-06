package oosd.models.units.zombies;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;
import oosd.models.units.humans.Soldier;

import java.util.Arrays;
import java.util.List;

public class JuggernautZombie extends Zombie {
    public JuggernautZombie(Player player) {
        super(player);
    }

    @Override
    public List<Class<? extends Unit>> getWinnables() {
        return Arrays.asList(Zombat.class, ScoutZombie.class, Soldier.class);
    }

    public String getName() {
        return "Jug Zombie";
    }

    @Override
    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(1);
    }
}
