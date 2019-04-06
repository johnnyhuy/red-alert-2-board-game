package oosd.models.units.humans;

import oosd.models.player.Player;
import oosd.models.units.behaviour.LinearUnitBehaviour;
import oosd.models.units.behaviour.UnitBehaviour;
import oosd.models.units.zombies.JuggernautZombie;
import oosd.models.units.zombies.ScoutZombie;
import oosd.models.units.zombies.Zombat;

public class Tank extends Humans {
    public Tank(Player player) {
        super(player);
        super.getWinnables().add(Zombat.class);
        super.getWinnables().add(ScoutZombie.class);
        super.getWinnables().add(Soldier.class);
        super.getWinnables().add(JuggernautZombie.class);
    }

    @Override
    public String getName() {
        return "Tank";
    }

    @Override
    public int getMove() {
        return 3;
    }

    @Override
    public UnitBehaviour getUnitBehaviour() {
        return new LinearUnitBehaviour(2);
    }
}
