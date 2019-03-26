package oosd.models.units;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;

public class ScoutZombie extends Unit {
    public ScoutZombie(Hexagon location, Player player) {
        super(location, player);
        super.getWinnables().add(Zombat.class);
    }
}
