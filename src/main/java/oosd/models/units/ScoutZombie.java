package oosd.models.units;

import oosd.models.player.Player;

public class ScoutZombie extends Unit {
    public ScoutZombie(Player player) {
        super(player);
        super.getWinnables().add(Zombat.class);
    }

    @Override
    public String getName() {
        return "Scout Zombie";
    }
}
