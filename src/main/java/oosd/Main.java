package oosd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.allied.GrizzlyTank;
import oosd.models.units.allied.Harrier;
import oosd.models.units.soviet.Conscript;
import oosd.models.units.soviet.KirovAirship;
import oosd.models.units.soviet.RhinoTank;
import oosd.views.View;

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
    private final int sceneWidth = 1024;
    private final int sceneHeight = 856;

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
        Board board = new Board(boardColumns, boardRows);

        Team redTeam = new Team("Red");
        Team blueTeam = new Team("Blue");

        Player playerOne = new Player("Johnny Dave", redTeam);
        Player playerTwo = new Player("Jane Doe", blueTeam);

        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));

        playerOne.addUnit(new GISoldier(new Piece(0, 0)));
        playerOne.addUnit(new GISoldier(new Piece(1, 0)));
        playerOne.addUnit(new GrizzlyTank(new Piece(2, 0)));
        playerOne.addUnit(new GrizzlyTank(new Piece(3, 0)));
        playerOne.addUnit(new Harrier(new Piece(4, 0)));
        playerOne.addUnit(new Harrier(new Piece(5, 0)));
        playerOne.addUnit(new GrizzlyTank(new Piece(6, 0)));
        playerOne.addUnit(new GrizzlyTank(new Piece(7, 0)));
        playerOne.addUnit(new GISoldier(new Piece(8, 0)));
        playerOne.addUnit(new GISoldier(new Piece(9, 0)));
        playerTwo.addUnit(new RhinoTank(new Piece(0, 9)));
        playerTwo.addUnit(new RhinoTank(new Piece(1, 9)));
        playerTwo.addUnit(new KirovAirship(new Piece(2, 9)));
        playerTwo.addUnit(new KirovAirship(new Piece(3, 9)));
        playerTwo.addUnit(new Conscript(new Piece(4, 9)));
        playerTwo.addUnit(new Conscript(new Piece(5, 9)));
        playerTwo.addUnit(new KirovAirship(new Piece(6, 9)));
        playerTwo.addUnit(new KirovAirship(new Piece(7, 9)));
        playerTwo.addUnit(new RhinoTank(new Piece(8, 9)));
        playerTwo.addUnit(new RhinoTank(new Piece(9, 9)));

        return new GameEngine(board, players);
    }
}
