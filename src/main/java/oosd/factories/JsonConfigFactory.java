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

public class JsonConfigFactory extends ConfigFactory
{			
	public Board createBoard()
	{
		return null;
	}

	@Override
	public List<Player> createPlayers(Board board)
	{
		return null;
	}
}
