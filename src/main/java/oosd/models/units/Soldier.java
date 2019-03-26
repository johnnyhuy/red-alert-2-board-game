package oosd.models.units;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;

public class Soldier extends Unit {
    public Soldier(Hexagon location, Player player) {
        super(location, player);
        super.getWinnables().add(Zombat.class);
        super.getWinnables().add(ScoutZombie.class);
    }
}
