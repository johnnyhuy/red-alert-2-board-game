package oosd.factories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import oosd.models.board.Board;
import oosd.models.player.Player;

public abstract class ConfigFactory
{
	public Board createBoard(int boardColumns, int boardRows)
	{
		// to be implemented
		return null;
	}
	
	public List<Player> createPlayers(Board board)
	{
		return null;
	}
}
