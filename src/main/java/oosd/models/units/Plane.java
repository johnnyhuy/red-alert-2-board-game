package oosd.models.units;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;

public class Plane extends Unit {
    public Plane(Hexagon location, Player player) {
        super(location, player);
        super.getWinnables().add(Zombat.class);
        super.getWinnables().add(ScoutZombie.class);
        super.getWinnables().add(Soldier.class);
        super.getWinnables().add(JuggernautZombie.class);
        super.getWinnables().add(Tank.class);
    }
}
