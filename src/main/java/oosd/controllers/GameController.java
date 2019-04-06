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

    public void board(MouseEvent event, Hexagon clickedHexagon) {
        Hexagon selectedHexagon = gameEngine.getSelectedHexagon();

        // TODO: refactor me!
        if (clickedHexagon.getUnit() != null) {
            gameEngine.setSelectedHexagon(clickedHexagon);

            boardView.selectUnit(selectedHexagon, clickedHexagon);
        } else if (selectedHexagon != null) {
            if (!clickedHexagon.getUnit().getUnitBehaviour().isValidMove(gameEngine, clickedHexagon)) {
                return;
            }

            clickedHexagon.setUnit(selectedHexagon.getUnit());
            selectedHexagon.setUnit(null);
            gameEngine.setSelectedHexagon(null);

            boardView.moveUnit(selectedHexagon, clickedHexagon);
        }
    }
}
