package oosd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.allied.GrizzlyTank;
import oosd.models.units.allied.Harrier;
import oosd.models.units.soviet.Conscript;
import oosd.models.units.soviet.KirovAirship;
import oosd.models.units.soviet.RhinoTank;

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
    private final String windowTitle = "OOSD Game GameBoard";
    private final int sceneWidth = 1200;
    private final int sceneHeight = 900;

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
        Board board = new GameBoard(boardColumns, boardRows);

        Team redTeam = new Team("Red");
        Team blueTeam = new Team("Blue");

        Player playerOne = new Player("Johnny Dave", redTeam);
        Player playerTwo = new Player("Jane Doe", blueTeam);

        List<Player> players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));

        board.getPiece(0, 0).setUnit(new GISoldier(playerOne));
        board.getPiece(1, 0).setUnit(new GISoldier(playerOne));
        board.getPiece(2, 0).setUnit(new GrizzlyTank(playerOne));
        board.getPiece(3, 0).setUnit(new GrizzlyTank(playerOne));
        board.getPiece(4, 0).setUnit(new Harrier(playerOne));
        board.getPiece(5, 0).setUnit(new Harrier(playerOne));
        board.getPiece(6, 0).setUnit(new GrizzlyTank(playerOne));
        board.getPiece(7, 0).setUnit(new GrizzlyTank(playerOne));
        board.getPiece(8, 0).setUnit(new GISoldier(playerOne));
        board.getPiece(9, 0).setUnit(new GISoldier(playerOne));
        board.getPiece(0, 9).setUnit(new RhinoTank(playerTwo));
        board.getPiece(1, 9).setUnit(new RhinoTank(playerTwo));
        board.getPiece(2, 9).setUnit(new KirovAirship(playerTwo));
        board.getPiece(3, 9).setUnit(new KirovAirship(playerTwo));
        board.getPiece(4, 9).setUnit(new Conscript(playerTwo));
        board.getPiece(5, 9).setUnit(new Conscript(playerTwo));
        board.getPiece(6, 9).setUnit(new KirovAirship(playerTwo));
        board.getPiece(7, 9).setUnit(new KirovAirship(playerTwo));
        board.getPiece(8, 9).setUnit(new RhinoTank(playerTwo));
        board.getPiece(9, 9).setUnit(new RhinoTank(playerTwo));

        return new GameEngine(board, players);
    }
}
