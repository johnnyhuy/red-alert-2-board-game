package oosd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oosd.controllers.GameController;
import oosd.factories.InMemoryGameSetupFactory;
import oosd.factories.JsonGameSetupFactory;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.player.Player;

import java.util.List;

/**
 * GRASP: information expert
 * The main program class contains configuration information about the game.
 * If a user were to change specific units on the board, they can change it here in the main class.
 */
public class Main extends Application {
    private final String boardFileName = "board.fxml";
    private final String windowTitle = "OOSD Game GameBoard";
    private final int sceneWidth = 1200;
    private final int sceneHeight = 900;
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

        primaryStage.setScene(content);
        primaryStage.setTitle(windowTitle);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Initialize game configuration data, which can be easily modified.
     *
     * @return the game engine
     */
    private GameEngine initializeGameEngine() {

        if (useJSONConfig) {
            JsonGameSetupFactory factoryJSON = new JsonGameSetupFactory();
            Board board = factoryJSON.createBoard();
            List<Player> players = factoryJSON.createPlayers(board);
            return new GameEngine(board, players);
        } else {
            InMemoryGameSetupFactory factory = new InMemoryGameSetupFactory();
            Board board = factory.createBoard();
            List<Player> players = factory.createPlayers(board);
            return new GameEngine(board, players);
        }
    }
}
