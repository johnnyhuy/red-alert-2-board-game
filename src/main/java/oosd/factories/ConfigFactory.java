package oosd.factories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import oosd.models.board.Board;
import oosd.models.player.Player;

public interface ConfigFactory
{
	Board createBoard(int boardColumns, int boardRows);
	List<Player> createPlayers(Board board);
}
