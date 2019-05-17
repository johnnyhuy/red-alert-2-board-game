package oosd;

import javafx.application.Application;
import javafx.stage.Stage;
import oosd.controllers.GameController;
import oosd.factories.InMemoryConfigFactory;
import oosd.factories.JsonConfigFactory;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.player.Player;
import oosd.views.View;
import oosd.models.player.Team;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.allied.GrizzlyTank;
import oosd.models.units.allied.Harrier;
import oosd.models.units.soviet.Conscript;
import oosd.models.units.soviet.KirovAirship;
import oosd.models.units.soviet.RhinoTank;
import oosd.views.components.windows.GameWindow;

import java.util.ArrayList;
import java.util.Arrays;

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
    private GameEngine initializeGameEngine() {
        int boardRows = 10;
        int boardColumns = 10;

    	if (useJSONConfig == false)
    	{
        	InMemoryConfigFactory factory = new InMemoryConfigFactory();
        	Board board = factory.createBoard(boardColumns, boardRows);
        	List<Player> players = factory.createPlayers(board);
            return new GameEngine(board, players);
    	}
    	else
    	{
    		JsonConfigFactory factoryJSON = new JsonConfigFactory();
        	Board board = factoryJSON.createBoard(boardColumns, boardRows);
        	List<Player> players = factoryJSON.createPlayers(board);
            return new GameEngine(board, players);
    	}
    }
}
