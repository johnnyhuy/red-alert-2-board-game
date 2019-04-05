package oosd.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import oosd.models.GameEngine;
import oosd.models.board.Hexagon;
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
    private AnchorPane boardPane;

    private BoardView boardView;

    public GameController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void initialize() {
        boardView = new BoardView(this, gameEngine, boardPane);
        boardView.initialize();
    }

    public void board(MouseEvent event, Hexagon hexagon) {
        if (hexagon.getUnit() != null) {
            Hexagon previousHexagon = gameEngine.getSelectedHexagon();
            this.gameEngine.setSelectedHexagon(hexagon);
            this.boardView.selectUnit(previousHexagon, hexagon);
        }
    }
}
