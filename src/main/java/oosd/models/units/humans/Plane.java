package oosd.models.units.humans;

import oosd.models.player.Player;
import oosd.models.units.zombies.JuggernautZombie;
import oosd.models.units.zombies.ScoutZombie;
import oosd.models.units.zombies.Zombat;

public class Plane extends Humans {
    public Plane(Player player) {
        super(player);
        super.getWinnables().add(Zombat.class);
        super.getWinnables().add(ScoutZombie.class);
        super.getWinnables().add(Soldier.class);
        super.getWinnables().add(JuggernautZombie.class);
        super.getWinnables().add(Tank.class);
    }

    @Override
    public String getName() {
        return "Plane";
    }

    @Override
    public int getMove() {
        return 6;
    }
}
