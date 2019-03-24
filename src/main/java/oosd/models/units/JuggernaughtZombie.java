package oosd.models.units;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;

public class JuggernaughtZombie extends Unit {
    JuggernaughtZombie(Hexagon location, Player player) {
        super(location, player);
    }

    JuggernaughtZombie() {
        super();
    }

    @Override
    public void setWinnables() {
        super.getWinnables().add(new Zombat());
        super.getWinnables().add(new ScoutZombie());
        super.getWinnables().add(new Soldier());
    }
}
