package models.units;

import models.board.Hexagon;
import models.player.Player;

public class Zombat extends Unit {
	Zombat(Hexagon location, Player player) {
		super(location, player);
	}

	Zombat() {
		super();
	}

	@Override
	public void setWinnables() {
		// no winnables
	}
}
