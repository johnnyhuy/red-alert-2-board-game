package models.units;

import models.board.Hexagon;
import models.player.Player;

public class Plane extends Unit {
	public Plane(Hexagon location, Player player) {
		super(location, player);
	}

	Plane() {
		super();
	}

	@Override
	public void setWinnables() {
		super.getWinnables().add(new Zombat());
		super.getWinnables().add(new ScoutZombie());
		super.getWinnables().add(new Soldier());
		super.getWinnables().add(new JuggernaughtZombie());
		super.getWinnables().add(new Tank());
	}
}
