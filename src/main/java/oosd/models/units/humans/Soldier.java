package oosd.models.units.humans;

import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.zombies.ScoutZombie;
import oosd.models.units.zombies.Zombat;

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
