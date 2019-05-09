package oosd.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import oosd.models.board.Board;
import oosd.models.player.Player;
import oosd.models.player.Team;

public abstract class JsonConfigFactory implements ConfigFactory
{	
	public JsonConfigFactory()
	{

	}

	@Override
	public Board createBoard(int boardColumns, int boardRows)
	{
        return null;
	}

	@Override
	public List<Player> createPlayers()
	{
		return null;
	}

}
