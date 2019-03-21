package models.units;

import models.board.Hexagon;
import models.player.Player;

public class Soldier extends Unit {

	public Soldier(Hexagon location, Player player) {
		super(location, player);
		
	}

	public Soldier() {
		
	}

	@Override
	public void setWinnables() {
		super.winnables.add(new Zombat());
		super.winnables.add(new ScoutZombie());
			
		
	}
}
