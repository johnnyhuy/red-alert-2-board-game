package oosd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import oosd.views.View;
import oosd.views.components.panes.GameWindowPane;

import java.util.Objects;

/**
 * GRASP: information expert
 * The main program class contains configuration information about the game.
 * If a user were to change specific units on the board, they can change it here in the main class.
 */
public class Main extends Application {
    /**
     * Boilerplate code for JavaFX.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * GRASP: The creator
     * Creates the initialized game logic and base UI objects at the start of the program.
     *
     * @param stage JavaFX primary window
     */
    @Override
    public void start(Stage stage) {
        final String windowTitle = "Red Alert 2 Board Game";
        final String windowIcon = "soviet.png";
        final String styles = "style/main.css";

        GameWindowPane gameWindowPane = new GameWindowPane();

        Scene content = new Scene(gameWindowPane, 1200, 900);
        content.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource(styles)).toString());

        stage.setScene(content);
        stage.setTitle(windowTitle);
        stage.setResizable(false);
        stage.getIcons().add(new Image(View.class.getResource(windowIcon).toString()));
        stage.show();
    }
}
