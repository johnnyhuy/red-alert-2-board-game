package oosd.models.units.zombies;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.humans.Soldier;

public class JuggernautZombie extends Unit {
    public JuggernautZombie(Player player) {
        super(player);
        super.getWinnables().add(Zombat.class);
        super.getWinnables().add(ScoutZombie.class);
        super.getWinnables().add(Soldier.class);
    }

    public String getName() {
        return "Jug Zombie";
    }

    @Override
    public int getMove() {
        return 1;
    }
}
