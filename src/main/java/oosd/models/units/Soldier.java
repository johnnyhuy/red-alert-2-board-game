package oosd.models.units;

import oosd.models.player.Player;

public class Soldier extends Unit {
    public Soldier(Player player) {
        super(player);
        super.getWinnables().add(Zombat.class);
        super.getWinnables().add(ScoutZombie.class);
    }

    @Override
    public String getName() {
        return "Solider";
    }

    @Override
    public int getMove() {
        return 2;
    }
}
