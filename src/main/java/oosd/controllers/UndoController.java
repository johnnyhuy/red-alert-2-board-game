package oosd.controllers;

import javafx.fxml.FXML;
import oosd.models.GameEngine;
import oosd.views.components.panes.WindowGridPane;

/**
 * GRASP: The controller
 * Used to handle requests from other objects include the view and model.
 * Acts as a middleman that delegates tasks to other objects.
 * Cleanly separates the user interface (view) from the business objects (model)
 */
public class UndoController extends Controller {
    private final GameEngine gameEngine;

    @FXML
    private WindowGridPane windowGridPane;

    public UndoController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void initialize() {
    }
}
