package oosd.factories;

import java.util.List;
import oosd.models.board.Board;
import oosd.models.player.Player;

public interface GameSetupFactory
{
	Board createBoard(int boardColumns, int boardRows);
	List<Player> createPlayers(Board board);
}
