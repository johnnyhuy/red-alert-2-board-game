package oosd.models.units;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;

public class Tank extends Unit {
    public Tank(Hexagon location, Player player) {
        super(location, player);
        super.getWinnables().add(Zombat.class);
        super.getWinnables().add(ScoutZombie.class);
        super.getWinnables().add(Soldier.class);
        super.getWinnables().add(JuggernautZombie.class);
    }
}
