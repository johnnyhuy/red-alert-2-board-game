package models.units;

import models.board.Hexagon;
import models.player.Player;

public class Tank extends Unit {

	public Tank(Hexagon location, Player player) {
		super(location, player);
		
	}

	public Tank() {
		
	}

	@Override
	public void setWinnables() {
		super.winnables.add(new Zombat());
		super.winnables.add(new ScoutZombie());
		super.winnables.add(new Soldier());	
		super.winnables.add(new JuggernaughtZombie());
		
	}
}
