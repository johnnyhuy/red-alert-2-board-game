package oosd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        final String boardFileName = "board.fxml";
        final String windowTitle = "OOSD Game Board";
        final int sceneWidth = 625;
        final int sceneHeight = 775;

        GameEngine gameEngine = new GameEngine();
        GameController gameController = new GameController(gameEngine);

        FXMLLoader loader = new FXMLLoader(GameController.class.getResource(boardFileName));
        loader.setController(gameController);

        AnchorPane anchorPane = loader.load();
        Scene content = new Scene(anchorPane, sceneWidth, sceneHeight);

        primaryStage.setScene(content);
        primaryStage.setTitle(windowTitle);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
