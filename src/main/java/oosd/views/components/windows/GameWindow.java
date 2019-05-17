package oosd.views.components.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oosd.controllers.Controller;
import oosd.views.View;

import java.io.IOException;

/**
 * Design pattern: facade used to abstract window UI build logic.
 * The window needs a stage and controller to populate itself.
 * Frame specific details are stored in private final fields to make it immutable.
 */
public class GameWindow {
    private final int sceneWidth;
    private final int sceneHeight;
    private final String windowTitle;
    private final String boardFileName;

    public GameWindow() {
        this.sceneWidth = 1200;
        this.sceneHeight = 900;
        this.windowTitle = "Red Alert 2 Board Game";
        this.boardFileName = "board.fxml";
    }

    public void render(Stage primaryStage, Controller controller) {
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource(boardFileName));
        loader.setController(controller);

        try {
            Pane pane = loader.load();
            Scene content = new Scene(pane, sceneWidth, sceneHeight);

            primaryStage.setScene(content);
            primaryStage.setTitle(windowTitle);
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image(View.class.getResource("allied.png").toString()));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
