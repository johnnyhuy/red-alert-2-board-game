package oosd.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import oosd.models.GameEngine;
import oosd.models.board.Piece;
import oosd.views.BoardView;

/**
 * GRASP: The controller
 * Used to handle requests from other objects include the view and model.
 * Acts as a middleman that delegates tasks to other objects.
 * Cleanly separates the user interface (view) from the business objects (model)
 */
public class GameController extends Controller {
    private final GameEngine gameEngine;

    @FXML
    private Pane windowGridPane;

    @FXML
    private StackPane boardPane;

    @FXML
    private Pane sidebar;

    @FXML
    private HBox toolbar;

    private BoardView boardView;

    public GameController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        System.out.println("poop");
    }

    @Override
    public void initialize() {
        boardView = new BoardView(this, gameEngine, windowGridPane, boardPane, sidebar, toolbar);
        boardView.initialize();
    }

    /**
     * Used to select a unit.
     *
     * @param event mouse event
     * @param selectedPiece object
     * @param piece object
     */
    public void selectUnit(MouseEvent event, Piece selectedPiece, Piece piece) {
        gameEngine.setSelectedPiece(piece);
        boardView.selectUnit(selectedPiece, piece);
    }

    /**
     * Tasked to move the unit.
     *
     * @param event mouse event
     * @param selectedPiece object
     * @param piece object
     */
    public void moveUnit(MouseEvent event, Piece selectedPiece, Piece piece) {
        piece.setUnit(selectedPiece.getUnit());
        selectedPiece.setUnit(null);
        gameEngine.setSelectedPiece(null);
        gameEngine.getNextTurn();
        boardView.moveUnit(selectedPiece, piece);
    }
}
