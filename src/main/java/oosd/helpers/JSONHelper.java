package oosd.helpers;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONHelper
{
	
	public int getJSONCol() throws FileNotFoundException, IOException, ParseException
	{
		Object obj = new JSONParser().parse(new FileReader("JSONExample.json"));		
		JSONObject jo = (JSONObject) obj; 
		
		int target = (int) jo.get("boardColumns");
		
		return target;
	}
	
	public int getJSONRow() throws FileNotFoundException, IOException, ParseException
	{
		Object obj = new JSONParser().parse(new FileReader("JSONExample.json"));		
		JSONObject jo = (JSONObject) obj; 
		
		int target = (int) jo.get("boardRows");
		
		return target;
	}
	
}
