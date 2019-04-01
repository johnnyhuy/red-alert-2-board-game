package oosd.models.units;

import oosd.models.player.Player;

public class JuggernautZombie extends Unit {
    public JuggernautZombie(Player player) {
        super(player);
        super.getWinnables().add(Zombat.class);
        super.getWinnables().add(ScoutZombie.class);
        super.getWinnables().add(Soldier.class);
    }
}
