package oosd.models.game;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameLogger implements Logger {
    private List<Log> logs = new ArrayList<>();

    @Override
    public void log(String text) {
        log(text, Color.valueOf("#FAFB32"));
    }

    @Override
    public void log(String text, Color color) {
        logs.add(new Log(text, color));
    }

    @Override
    public List<Log> getLogs() {
        return logs;
    }
}
