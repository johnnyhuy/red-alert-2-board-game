package oosd.factories;

import oosd.models.board.Board;
import oosd.models.player.Player;
import oosd.models.player.Team;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class JsonConfigFactory implements ConfigFactory
{			
	public Board createBoard() throws FileNotFoundException, IOException, ParseException
	{
		Object obj = new JSONParser().parse(new FileReader("JSONExample.json"));
		
		JSONObject jo = (JSONObject) obj;
		
		int boardColumns = (int) jo.get("boardColumns");
		int boardRows = (int) jo.get("boardRows");
		
		Board board = new Board(boardColumns, boardRows);
        return board;
	}

	@Override
	public List<Player> createPlayers(Board board)
	{
Object obj = new JSONParser().parse(new FileReader("JSONExample.json"));
		
		JSONObject jo = (JSONObject) obj;
		
		return null;
	}
}
