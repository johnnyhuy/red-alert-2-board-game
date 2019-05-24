package oosd.controllers;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.views.BoardView;
import oosd.views.View;
import oosd.views.components.panes.WindowGridPane;

import java.util.Objects;

/**
 * GRASP: The controller
 * Used to handle requests from other objects include the view and model.
 * Acts as a middleman that delegates tasks to other objects.
 * Cleanly separates the user interface (view) from the business objects (model)
 */
public class GameController extends Controller {
    private final Engine engine;
    private Stage primaryStage;
    private BoardView boardView;
    private WindowGridPane windowGridPane;

    public GameController(Engine engine, Stage primaryStage) {
        this.engine = engine;
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize() {
        boardView = new BoardView(this, engine, windowGridPane);
        boardView.render();
    }

    public void start() {
        final String windowTitle = "Red Alert 2 Board Game";
        final String windowIcon = "allied.png";
        final String styles = "style/main.css";

        windowGridPane = new WindowGridPane();
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("board.fxml"));
        loader.setController(this);

        try {
            loader.setRoot(windowGridPane);
            loader.load();

            Scene content = new Scene(windowGridPane, 1200, 900);
            content.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource(styles)).toString());

            primaryStage.setScene(content);
            primaryStage.setTitle(windowTitle);
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image(View.class.getResource(windowIcon).toString()));
            primaryStage.show();
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Game error");
            alert.setHeaderText("Whoops! Game crashed...");
            alert.setContentText("I can't seem to load the game window so I'm going to die now :(");
            alert.showAndWait();

            System.exit(1);
        }
    }

    /**
     * Used to select a unit.
     *
     * @param event mouse event
     * @param selectedPiece object
     * @param piece object
     */
    public void selectUnit(MouseEvent event, Piece selectedPiece, Piece piece) {
        engine.selectUnit(piece);
        boardView.selectUnit(selectedPiece, piece);
    }

    /**
     * Tasked to move the unit.
     *
     * @param event mouse event
     * @param selectedPiece object
     * @param piece object
     */
    public void moveUnit(Event event, Piece selectedPiece, Piece piece) {
        engine.moveUnit(selectedPiece, piece);
        boardView.moveUnit(selectedPiece, piece);
    }

    /**
     * Defend a given piece.
     *
     * @param event mouse event
     * @param piece object
     */
    public void defendUnit(MouseEvent event, Piece piece) {
        engine.defendUnit(piece);
        boardView.defendUnit(piece);
    }

    /**
     * Attack a given piece.
     *
     * @param mouseEvent    mouse event
     * @param selectedPiece object
     * @param piece         object
     */
    public void attackUnit(MouseEvent mouseEvent, Piece selectedPiece, Piece piece) {
        engine.attackUnit(selectedPiece, piece);
        boardView.attackUnit(selectedPiece, piece);
    }

    /**
     * Undo a move in the game.
     *
     * @param mouseEvent mouse event
     */
    public void undoMove(MouseEvent mouseEvent) {
        engine.undoTurn();
        boardView.undoMove();
    }
}
