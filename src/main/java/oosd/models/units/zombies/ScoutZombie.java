package oosd.models.units.zombies;

import oosd.models.player.Player;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;

public class ScoutZombie extends Zombie {
    public ScoutZombie(Player player) {
        super(player);
        super.getWinnables().add(Zombat.class);
    }

    @Override
    public String getName() {
        return "Scout Zombie";
    }

    @Override
    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(3);
    }
}
