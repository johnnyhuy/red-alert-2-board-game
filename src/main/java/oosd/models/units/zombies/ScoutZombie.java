package oosd.models.units.zombies;

import oosd.models.player.Player;

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
    public int getMove() {
        return 4;
    }
}
