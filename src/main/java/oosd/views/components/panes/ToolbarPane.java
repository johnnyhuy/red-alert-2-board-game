package oosd.views.components.panes;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.views.handlers.UndoClickHandler;

public class ToolbarPane extends HBox {
    public void initialise(GameEngine gameEngine, GameController gameController) {
        Button undoButton = (Button) this.lookup("#undoButton");
        undoButton.setOnMouseClicked(new UndoClickHandler(gameEngine, gameController));
    }
}
