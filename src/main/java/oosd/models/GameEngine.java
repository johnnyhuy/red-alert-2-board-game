package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.*;
import com.google.java.contract.Ensures;

public class GameEngine {
    private Board board;

    public GameEngine() {
        final int boardRow = 6;
        final int boardColumn = 6;

        this.board = new Board(boardColumn, boardRow);
    }

    @Ensures({
    	"String i = playerOne.getPlayerName()",
    	"i.compareTo('Johnny Dave') == true",
    	"String j = playerTwo.getPlayerName()",
    	"j.compareTo('Jane Doe') == true",
    	"tank.getLocation() == hexagons[0][0]",
    	"plane.getLocation() == hexagons[1][0]",
    	"soldier.getLocation() == hexagons[2][0]",
    	"juggernautZombie.getLocation() == hexagons[0][5]",
    	"scoutZombie.getLocation() == hexagons[1][5]",
    	"zombat.getLocation() == hexagons[2][5]"
    })
    public void initialize() {
    	Hexagon[][] hexagons = this.board.getHexagons();
        Player playerOne = new Player("Johnny Dave", Team.RED);
        Player playerTwo = new Player("Jane Doe", Team.BLUE);
        
        Unit tank = new Tank(hexagons[0][0], playerOne);
        Unit plane = new Plane(hexagons[1][0], playerOne);
        Unit soldier = new Soldier(hexagons[2][0], playerOne);
        Unit juggernautZombie = new JuggernautZombie(hexagons[0][5], playerOne);
        Unit scoutZombie = new ScoutZombie(hexagons[1][5], playerOne);
        Unit zombat = new Zombat(hexagons[2][5], playerOne);
    }

    public Board getBoard() {
        return this.board;
    }
}
