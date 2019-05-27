package oosd.models.game;

import javafx.scene.paint.Color;

public class Log {
    private String text;
    private Color color;

    public Log(String text, Color color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }
}
