package models.units;

import models.board.Hexagon;
import models.player.Player;

public class Soldier extends Unit {
	Soldier(Hexagon location, Player player) {
		super(location, player);
	}

	Soldier() {
		super();
	}

	@Override
	public void setWinnables() {
		super.getWinnables().add(new Zombat());
		super.getWinnables().add(new ScoutZombie());
	}
}
