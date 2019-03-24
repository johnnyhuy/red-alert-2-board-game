package oosd.models.units;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;

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
