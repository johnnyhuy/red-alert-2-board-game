package oosd.helpers;

import org.json.simple.JSONObject;

import static oosd.helpers.NumberHelper.toInt;

public class JsonGameSetupHelper {
    public static int getBoardColumns(JSONObject jsonObject) {
        return toInt((long) jsonObject.get("board_columns"));
    }

    public static int getBoardRows(JSONObject jsonObject) {
        return toInt((long) jsonObject.get("board_rows"));
    }
}
