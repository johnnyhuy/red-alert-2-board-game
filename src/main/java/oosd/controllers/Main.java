package oosd.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        final String boardFileName = "board.fxml";
        final String windowTitle = "OOSD Game Board";
        final int sceneWidth = 625;
        final int sceneHeight = 775;

        Parent root = FXMLLoader.load(getClass().getResource(boardFileName));

        Scene content = new Scene(root, sceneWidth, sceneHeight);
        primaryStage.setScene(content);

        primaryStage.setTitle(windowTitle);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
