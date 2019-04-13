package oosd.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    private Pane windowGridPane;

    @FXML
    private Pane boardPane;

    @FXML
    private Pane sidebar;

    private BoardView boardView;

    public GameController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void initialize() {
        boardView = new BoardView(this, gameEngine, boardPane, sidebar);
        boardView.initialize();
    }

    /**
     * Used to select a unit.
     *
     * @param event mouse event
     * @param selectedHexagon object
     * @param hexagon object
     */
    public void selectUnit(MouseEvent event, Hexagon selectedHexagon, Hexagon hexagon) {
        gameEngine.setSelectedHexagon(hexagon);
        boardView.selectUnit(selectedHexagon, hexagon);
    }

    /**
     * Tasked to move the unit.
     *
     * @param event mouse event
     * @param selectedHexagon object
     * @param hexagon object
     */
    public void moveUnit(MouseEvent event, Hexagon selectedHexagon, Hexagon hexagon) {
        hexagon.setUnit(selectedHexagon.getUnit());
        selectedHexagon.setUnit(null);
        gameEngine.setSelectedHexagon(null);
        gameEngine.getNextTurn();
        boardView.moveUnit(selectedHexagon, hexagon);
    }
}
