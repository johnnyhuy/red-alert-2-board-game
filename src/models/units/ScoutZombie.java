package models.units;

import models.board.Hexagon;
import models.player.Player;

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
