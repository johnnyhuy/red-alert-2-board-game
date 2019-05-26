package oosd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import oosd.controllers.Controller;
import oosd.views.View;
import oosd.views.components.alerts.ErrorAlert;
import oosd.views.components.panes.GameWindowPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Objects;

/**
 * GRASP: information expert
 * The main program class contains configuration information about the game.
 * If a user were to change specific units on the board, they can change it here in the main class.
 */
public class Main extends Application {
    private boolean useJSONConfig = true;

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
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        GameWindowPane gameWindowPane = new GameWindowPane();

        final String windowTitle = "Red Alert 2 Board Game";
        final String windowIcon = "soviet.png";
        final String styles = "style/main.css";

        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("board.fxml"));
        loader.setRoot(gameWindowPane);
        loader.setControllerFactory(context::getBean);

        try {
            loader.load();
        } catch (Exception exception) {
            ErrorAlert errorAlert = new ErrorAlert();
            errorAlert.exit();
        }

        Scene content = new Scene(gameWindowPane, 1200, 900);
        content.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource(styles)).toString());

        stage.setScene(content);
        stage.setTitle(windowTitle);
        stage.setResizable(false);
        stage.getIcons().add(new Image(View.class.getResource(windowIcon).toString()));
        stage.show();
    }
}
