package oosd.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final String windowTitle = "OOSD Game Board";
    private final String boardFileName = "board.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(this.boardFileName));

        Scene content = new Scene(root, 800, 800);
        primaryStage.setScene(content);

        primaryStage.setTitle(this.windowTitle);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
