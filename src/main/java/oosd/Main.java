package oosd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.humans.Plane;
import oosd.models.units.humans.Soldier;
import oosd.models.units.humans.Tank;
import oosd.models.units.zombies.JuggernautZombie;
import oosd.models.units.zombies.ScoutZombie;
import oosd.models.units.zombies.Zombat;

import java.util.ArrayList;
import java.util.Arrays;
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
    private final int sceneWidth = 800;
    private final int sceneHeight = 600;

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
        primaryStage.show();
    }

    /**
     * Initialize game configuration data, which can be easily modified.
     *
     * @return the game engine
     */
    private GameEngine initializeGameEngine() {
        Board board = new Board(boardColumns, boardRows);

        Player playerOne = new Player("Johnny Dave", Team.RED);
        Player playerTwo = new Player("Jane Doe", Team.BLUE);

        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));

        board.getHexagon(0, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(1, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(2, 0).setUnit(new Tank(playerOne));
        board.getHexagon(3, 0).setUnit(new Tank(playerOne));
        board.getHexagon(4, 0).setUnit(new Plane(playerOne));
        board.getHexagon(5, 0).setUnit(new Plane(playerOne));
        board.getHexagon(6, 0).setUnit(new Tank(playerOne));
        board.getHexagon(7, 0).setUnit(new Tank(playerOne));
        board.getHexagon(8, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(9, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(0, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(1, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(2, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(3, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(4, 9).setUnit(new JuggernautZombie(playerTwo));
        board.getHexagon(5, 9).setUnit(new JuggernautZombie(playerTwo));
        board.getHexagon(6, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(7, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(8, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(9, 9).setUnit(new ScoutZombie(playerTwo));

        return new GameEngine(board, players);
    }
}
