package models.units;

import models.board.Hexagon;
import models.player.Player;

public class Zombat extends Unit {
	Zombat(Hexagon location, Player player) {
		super(location, player);
	}

	@Override
	public void setWinnables() {
		// no winnables
	}
}
