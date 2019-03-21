package models.units;

import models.board.Hexagon;
import models.player.Player;

public class Plane extends Unit {

	public Plane(Hexagon location, Player player) {
		super(location, player);
		
	}

	@Override
	public void setWinnables() {
		super.winnables.add(new Zombat());
		super.winnables.add(new ScoutZombie());
		super.winnables.add(new Soldier());	
		super.winnables.add(new JuggernaughtZombie());
		super.winnables.add(new Tank());
		
	}
}
