package oosd.models.units.zombies;

import oosd.models.player.Player;
import oosd.models.units.Unit;

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
        return 6;
    }
}
