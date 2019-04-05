package oosd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oosd.controllers.GameController;
import oosd.models.GameEngine;

public class Main extends Application {
    /**
     * GRASP: The creator
     * Creates the initialized game logic and base UI objects at the start of the program.
     *
     * @param primaryStage JavaFX primary window
     * @throws Exception if the startup dies
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        final String boardFileName = "board.fxml";
        final String windowTitle = "OOSD Game Board";
        final int sceneWidth = 950;
        final int sceneHeight = 1100;
        final int boardColumns = 10;
        final int boardRows = 10;

        GameEngine gameEngine = new GameEngine(boardColumns, boardRows);
        gameEngine.initialize();
        GameController gameController = new GameController(gameEngine);

        FXMLLoader loader = new FXMLLoader(GameController.class.getResource(boardFileName));
        loader.setController(gameController);

        AnchorPane anchorPane = loader.load();
        Scene content = new Scene(anchorPane, sceneWidth, sceneHeight);

        primaryStage.setScene(content);
        primaryStage.setTitle(windowTitle);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
