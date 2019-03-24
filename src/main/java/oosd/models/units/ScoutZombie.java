package oosd.models.units;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;

public class ScoutZombie extends Unit {
	ScoutZombie(Hexagon location, Player player) {
		super(location, player);
	}

	ScoutZombie() {
		super();
	}

	@Override
	public void setWinnables() {
		super.getWinnables().add(new Zombat());
	}
}
