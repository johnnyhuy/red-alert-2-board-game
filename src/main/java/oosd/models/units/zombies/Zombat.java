package oosd.models.units.zombies;

import oosd.models.player.Player;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;

public class Zombat extends Zombie {
    public Zombat(Player player) {
        super(player);
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
