package oosd.models.units;

import oosd.models.player.Player;

public class Plane extends Unit {
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
