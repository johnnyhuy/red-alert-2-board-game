package oosd.views.components.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oosd.controllers.Controller;

import java.io.IOException;

/**
 * Design pattern: facade used to abstract window UI build logic.
 * The window needs a stage and controller to populate itself.
 * Frame specific details are stored in private final fields to make it immutable.
 */
public class UndoWindow {
    private final int sceneWidth;
    private final int sceneHeight;
    private final String windowTitle;
    private final String boardFileName;
    private final Stage stage;

    public UndoWindow() {
        this.sceneWidth = 400;
        this.sceneHeight = 300;
        this.windowTitle = "Undo moves";
        this.boardFileName = "undo.fxml";
        this.stage = new Stage();
    }

    public void render(Controller controller) {
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource(boardFileName));
        loader.setController(controller);

        try {
            Pane pane = loader.load();
            Scene content = new Scene(pane, sceneWidth, sceneHeight);

            stage.setScene(content);
            stage.setTitle(windowTitle);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
