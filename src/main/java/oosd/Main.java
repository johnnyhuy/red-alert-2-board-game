package oosd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import oosd.controllers.GameController;
import oosd.factories.InMemoryConfigFactory;
import oosd.factories.JsonConfigFactory;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.player.Player;
import oosd.views.View;

import java.util.List;

/**
 * GRASP: information expert
 * The main program class contains configuration information about the game.
 * If a user were to change specific units on the board, they can change it here in the main class.
 */
public class Main extends Application {
    private final int boardColumns = 10;
    private final int boardRows = 10;
    private final String boardFileName = "board.fxml";
    private final String windowTitle = "OOSD Game Board";
    private final int sceneWidth = 1024;
    private final int sceneHeight = 856;
    private boolean useJSONConfig = false;

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
     * @param primaryStage JavaFX primary window
     * @throws Exception if the startup dies
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        GameController gameController = new GameController(initializeGameEngine());

        FXMLLoader loader = new FXMLLoader(GameController.class.getResource(boardFileName));
        loader.setController(gameController);

        Pane pane = loader.load();
        Scene content = new Scene(pane, sceneWidth, sceneHeight);
        content.setFill(new ImagePattern(new Image(View.class.getResource("menu.png").toString())));

        primaryStage.setScene(content);
        primaryStage.setTitle(windowTitle);
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
    }

    /**
     * Initialize game configuration data, which can be easily modified.
     *
     * @return the game engine
     */
    private GameEngine initializeGameEngine() {
    	
    	if (useJSONConfig == false)
    	{
        	InMemoryConfigFactory factory = new InMemoryConfigFactory();
        	Board board = factory.createBoard(boardColumns, boardRows);
        	List<Player> players = factory.createPlayers(board);
            return new GameEngine(board, players);
    	}
    	else {
    		JsonConfigFactory factoryJSON = new JsonConfigFactory();
        	Board board = factoryJSON.createBoard(boardColumns, boardRows);
        	List<Player> players = factoryJSON.createPlayers(board);
            return new GameEngine(board, players);
    	}
    }
}
