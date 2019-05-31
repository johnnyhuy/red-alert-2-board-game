package oosd.models.game;

import javafx.scene.paint.Color;

import java.util.List;

public interface Logger {
    /**
     * Log text.
     *
     * @param text to log
     */
    void log(String text);

    /**
     * Log text with color.
     *
     * @param text  to log
     * @param color to paint text
     */
    void log(String text, Color color);

    /**
     * Get a list of logs.
     *
     * @return list of logs
     */
    List<Log> getLogs();
}
