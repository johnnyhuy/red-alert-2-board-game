package oosd.models.units;

import oosd.models.player.Player;

public class Zombat extends Unit {
    public Zombat(Player player) {
        super(player);
    }

    @Override
    public String getName() {
        return "Zombat";
    }

    @Override
    public int getMove() {
        return 5;
    }
}
