package models.units;

import models.board.Hexagon;
import models.player.Player;

public class ScoutZombie extends Unit {

	public ScoutZombie(Hexagon location, Player player) {
		super(location, player);
		
	}
	

	public ScoutZombie() {
		
	}


	@Override
	public void setWinnables() {
		super.winnables.add(new Zombat());					
	}
}
