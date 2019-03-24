package models.units;

import models.board.Hexagon;
import models.player.Player;

public class JuggernaughtZombie extends Unit{
	JuggernaughtZombie(Hexagon location, Player player) {
		super(location, player);			
	}

	@Override
	public void setWinnables() {
		super.getWinnables().add(new Zombat());
		super.getWinnables().add(new ScoutZombie());
		super.getWinnables().add(new Soldier());
	}
}
