package oosd.factories;

import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.player.Player;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.allied.GrizzlyTank;
import oosd.models.units.allied.Harrier;
import oosd.models.units.soviet.Conscript;
import oosd.models.units.soviet.KirovAirship;
import oosd.models.units.soviet.RhinoTank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryGameSetupFactory implements GameSetupFactory {
    public Board createBoard() {
        return new GameBoard(10, 10);
	}

    @Override
    public List<Player> createPlayers(Board board) {
        Player playerOne = new Player("Johnny Dave");
        Player playerTwo = new Player("Jane Doe");

        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));

		board.getPiece(0, 0).setUnit(new GISoldier(playerOne));
        board.getPiece(1, 0).setUnit(new GISoldier(playerOne));
        board.getPiece(2, 0).setUnit(new GrizzlyTank(playerOne));
        board.getPiece(3, 0).setUnit(new GrizzlyTank(playerOne));
        board.getPiece(4, 0).setUnit(new Harrier(playerOne));
        board.getPiece(5, 0).setUnit(new Harrier(playerOne));
        board.getPiece(6, 0).setUnit(new GrizzlyTank(playerOne));
        board.getPiece(7, 0).setUnit(new GrizzlyTank(playerOne));
        board.getPiece(8, 0).setUnit(new GISoldier(playerOne));
        board.getPiece(9, 0).setUnit(new GISoldier(playerOne));
        board.getPiece(0, 9).setUnit(new RhinoTank(playerTwo));
        board.getPiece(1, 9).setUnit(new RhinoTank(playerTwo));
        board.getPiece(2, 9).setUnit(new KirovAirship(playerTwo));
        board.getPiece(3, 9).setUnit(new KirovAirship(playerTwo));
        board.getPiece(4, 9).setUnit(new Conscript(playerTwo));
        board.getPiece(5, 9).setUnit(new Conscript(playerTwo));
        board.getPiece(6, 9).setUnit(new KirovAirship(playerTwo));
        board.getPiece(7, 9).setUnit(new KirovAirship(playerTwo));
        board.getPiece(8, 9).setUnit(new RhinoTank(playerTwo));
        board.getPiece(9, 9).setUnit(new RhinoTank(playerTwo));

		return players;
	}
}
