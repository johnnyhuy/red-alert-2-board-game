package oosd.factories;

import java.util.List;

import oosd.models.board.Board;
import oosd.models.player.Player;

interface ConfigFactory
{
	public Board createBoard(int boardColumns, int boardRows);
	
	public List<Player> createPlayers(Board board);
}
