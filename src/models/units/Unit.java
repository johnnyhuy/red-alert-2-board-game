package models.units;
import java.util.*;
import models.board.Hexagon;
import models.player.Player;

public abstract class Unit {

    private Hexagon location;
    private Player player;
    public ArrayList<Unit> winnables = new ArrayList<Unit>();
    public ArrayList<Player> allies = new ArrayList<Player>();
    private boolean captured;
    
    
    public Unit(Hexagon location,Player player){
    	this.location = location;
    	this.player = player;
    	this.captured = false;
    }
    
    public Unit(){

    }
    
    
    public void setStatus(boolean captured) {
    	this.captured = captured; 		
    }
        
    
    public void setLocation(Hexagon location) {
    	this.location = location;	
    }
    
    
    public void setAlly(Player ally) {
    	this.allies.add(ally);
    }
    
    public ArrayList<Player> getAllies() {
    	return allies;
    }
    
    public ArrayList<Unit> getWinnables() {
    	return winnables;
	}
    

    abstract public void setWinnables();
    
 

}
