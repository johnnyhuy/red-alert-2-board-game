package models.units;

import models.board.Hexagon;
import models.player.Player;

public class Zombat extends Unit {

	public Zombat(Hexagon location, Player player) {
		super(location, player);
		
	}

	public Zombat() {
		
	}

	@Override
	public void setWinnables() {
		//no winnables
		
	}
}
