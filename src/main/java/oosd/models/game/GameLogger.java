package oosd.models.game;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameLogger {
    private List<Log> logs = new ArrayList<>();

    public void log(String text) {
        log(text, Color.BLACK);
    }

    public void log(String text, Color color) {
        logs.add(new Log(text, color));
    }

    public List<Log> getLogs() {
        return logs;
    }
}
