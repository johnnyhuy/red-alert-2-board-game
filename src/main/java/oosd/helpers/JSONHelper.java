package oosd.helpers;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONHelper
{
	private int getNumber(String field) {
		Object obj;
		try
		{
			obj = new JSONParser().parse(new FileReader("JSONExample.json"));
			JSONObject jo = (JSONObject) obj; 
			
			return (int) jo.get(field);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getJSONCol()
	{
		return getNumber("boardColumns");
	}
	
	public int getJSONRow()
	{
		return getNumber("boardRows");
	}	
}
