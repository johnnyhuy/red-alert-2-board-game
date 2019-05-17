package oosd.factories;

import oosd.models.board.Board;
import oosd.models.player.Player;

import java.util.List;

public abstract class GameSetupFactory
{
	public Board createBoard(int boardColumns, int boardRows)
	{
        // TODO: create board.
		return null;
	}
	
	public List<Player> createPlayers(Board board)
	{
		return null;
	}
}
