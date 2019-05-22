package oosd.views.components.panes;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import oosd.controllers.GameController;
import oosd.models.game.Engine;
import oosd.views.components.images.ToolbarIcon;
import oosd.views.handlers.DefendClickHandler;
import oosd.views.handlers.UndoClickHandler;

public class ToolbarPane extends HBox {
    public void initialise(Engine engine, GameController gameController) {
        Button undoButton = (Button) this.lookup("#undoButton");
        undoButton.setOnMouseClicked(new UndoClickHandler(engine, gameController));
        undoButton.setGraphic(new ToolbarIcon("undo"));
        undoButton.setOnMousePressed(event -> undoButton.setGraphic(new ToolbarIcon("undo_active")));
        undoButton.setOnMouseEntered(event -> undoButton.setGraphic(new ToolbarIcon("undo_hover")));
        undoButton.setOnMouseExited(event -> undoButton.setGraphic(new ToolbarIcon("undo")));

        Button defendButton = (Button) this.lookup("#defendButton");
        defendButton.setOnMouseClicked(new DefendClickHandler(engine, gameController));
        defendButton.setGraphic(new ToolbarIcon("shield"));
        defendButton.setOnMousePressed(event -> defendButton.setGraphic(new ToolbarIcon("shield_active")));
        defendButton.setOnMouseEntered(event -> defendButton.setGraphic(new ToolbarIcon("shield_hover")));
        defendButton.setOnMouseExited(event -> defendButton.setGraphic(new ToolbarIcon("shield")));
    }
}
