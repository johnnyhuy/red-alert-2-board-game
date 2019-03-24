package oosd.models.units;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;

public class Tank extends Unit {
	Tank(Hexagon location, Player player) {
		super(location, player);
	}

	Tank() {
		super();
	}

	@Override
	public void setWinnables() {
		super.getWinnables().add(new Zombat());
		super.getWinnables().add(new ScoutZombie());
		super.getWinnables().add(new Soldier());
		super.getWinnables().add(new JuggernaughtZombie());
	}
}
