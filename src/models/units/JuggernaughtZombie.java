package models.units;

import models.board.Hexagon;
import models.player.Player;

public class JuggernaughtZombie extends Unit{

	public JuggernaughtZombie(Hexagon location, Player player) {
		super(location, player);			
	}

	public JuggernaughtZombie() {
		
	}

	@Override
	public void setWinnables() {
		super.winnables.add(new Zombat());
		super.winnables.add(new ScoutZombie());
		super.winnables.add(new Soldier());		
	}
	
	
	
}
