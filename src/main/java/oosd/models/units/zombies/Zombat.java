package oosd.models.units.zombies;

import oosd.models.player.Player;

public class Zombat extends Zombie {
    public Zombat(Player player) {
        super(player);
    }

    @Override
    public String getName() {
        return "Zombat";
    }

    @Override
    public int getMove() {
        return 6;
    }
}
