package oosd;

import javafx.application.Application;
import javafx.stage.Stage;
import oosd.controllers.GameController;
import oosd.factories.InMemoryGameSetupFactory;
import oosd.factories.JsonGameSetupFactory;
import oosd.models.board.Board;
import oosd.models.game.Engine;
import oosd.models.game.GameEngine;
import oosd.models.player.Player;
import oosd.views.components.windows.GameWindow;

import java.util.List;

/**
 * GRASP: information expert
 * The main program class contains configuration information about the game.
 * If a user were to change specific units on the board, they can change it here in the main class.
 */
public class Main extends Application {
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
        GameWindow gameWindow = new GameWindow();
        gameWindow.render(primaryStage, gameController);
    }

    /**
     * Initialize game configuration data, which can be easily modified.
     *
     * @return the game engine
     */
    private Engine initializeGameEngine() {
        int boardRows = 10;
        int boardColumns = 10;

    	if (useJSONConfig == false)
    	{
        	InMemoryGameSetupFactory factory = new InMemoryGameSetupFactory();
        	Board board = factory.createBoard(boardColumns, boardRows);
        	List<Player> players = factory.createPlayers(board);
            return new GameEngine(board, players);
    	}
    	else {
    		JsonGameSetupFactory factoryJSON = new JsonGameSetupFactory();
        	Board board = factoryJSON.createBoard(boardColumns, boardRows);
        	List<Player> players = factoryJSON.createPlayers(board);
            return new GameEngine(board, players);
    	}
    }
}
