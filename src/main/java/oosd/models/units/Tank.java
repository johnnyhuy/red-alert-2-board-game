package oosd.models.units;

import oosd.models.player.Player;

public class Tank extends Unit {
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
        return 4;
    }
}
