package oosd.helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonHelper {
    private int getNumber(String field) {
        try {
            JSONObject json = (JSONObject) new JSONParser().parse(new FileReader("JSONExample.json"));
            return (int) json.get(field);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getBoardColumns() {
        return getNumber("board_columns");
    }

    public int getBoardRows() {
        return getNumber("board_rows");
    }
}
