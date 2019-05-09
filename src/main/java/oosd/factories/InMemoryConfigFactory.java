package oosd.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import oosd.models.board.Board;
import oosd.models.player.Player;
import oosd.models.player.Team;

public abstract class InMemoryConfigFactory implements ConfigFactory
{	
	public InMemoryConfigFactory()
	{

	}

	@Override
	public Board createBoard(int boardColumns, int boardRows)
	{
        Board board = new Board(boardColumns, boardRows);
        return board;
	}

	@Override
	public List<Player> createPlayers()
	{
		Team redTeam = new Team("Red");
        Team blueTeam = new Team("Blue");

        Player playerOne = new Player("Johnny Dave", redTeam);
        Player playerTwo = new Player("Jane Doe", blueTeam);

        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
		return players;
	}

}
