package oosd.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import oosd.controllers.GameController;
import oosd.models.game.Engine;
import oosd.views.components.images.ToolbarIcon;
import oosd.views.handlers.*;
import org.springframework.context.ApplicationContext;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class ToolbarPresenter implements Initializable {
    private final ApplicationContext context;
    private Engine engine;

    @FXML
    private Button undoButton;

    @FXML
    private Button defendButton;

    @FXML
    private Button forfeitButton;

    @FXML
    private Button saveGameButton;

    @FXML
    private Button restoreGameButton;

    @Inject
    public ToolbarPresenter(ApplicationContext context, Engine engine) {
        this.context = context;
        this.engine = engine;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameController gameController = context.getBean(GameController.class);

        Button undoButton = getUndoButton();
        undoButton.setOnMouseClicked(new UndoClickHandler(engine, gameController));
        undoButton.setGraphic(new ToolbarIcon("undo"));
        undoButton.setOnMousePressed(event -> undoButton.setGraphic(new ToolbarIcon("undo_active")));
        undoButton.setOnMouseEntered(event -> undoButton.setGraphic(new ToolbarIcon("undo_hover")));
        undoButton.setOnMouseExited(event -> undoButton.setGraphic(new ToolbarIcon("undo")));

        Button defendButton = getDefendButton();
        defendButton.setOnMouseClicked(new DefendClickHandler(engine, gameController));
        defendButton.setGraphic(new ToolbarIcon("shield"));
        defendButton.setOnMousePressed(event -> defendButton.setGraphic(new ToolbarIcon("shield_active")));
        defendButton.setOnMouseEntered(event -> defendButton.setGraphic(new ToolbarIcon("shield_hover")));
        defendButton.setOnMouseExited(event -> defendButton.setGraphic(new ToolbarIcon("shield")));

        Button forfeitButton = getForfeitButton();
        forfeitButton.setOnMouseClicked(new ForfeitClickHandler(engine, gameController));
        forfeitButton.setGraphic(new ToolbarIcon("skull"));
        forfeitButton.setOnMousePressed(event -> forfeitButton.setGraphic(new ToolbarIcon("skull_active")));
        forfeitButton.setOnMouseEntered(event -> forfeitButton.setGraphic(new ToolbarIcon("skull_hover")));
        forfeitButton.setOnMouseExited(event -> forfeitButton.setGraphic(new ToolbarIcon("skull")));

        Button saveGameButton = getSaveGameButton();
        saveGameButton.setOnMouseClicked(new SaveGameClickHandler(engine, gameController));
        saveGameButton.setGraphic(new ToolbarIcon("save"));
        saveGameButton.setOnMousePressed(event -> saveGameButton.setGraphic(new ToolbarIcon("save_active")));
        saveGameButton.setOnMouseEntered(event -> saveGameButton.setGraphic(new ToolbarIcon("save_hover")));
        saveGameButton.setOnMouseExited(event -> saveGameButton.setGraphic(new ToolbarIcon("save")));

        Button restoreGameButton = getRestoreGameButton();
        restoreGameButton.setOnMouseClicked(new RestoreGameClickHandler(engine, gameController));
        restoreGameButton.setGraphic(new ToolbarIcon("restore"));
        restoreGameButton.setOnMousePressed(event -> restoreGameButton.setGraphic(new ToolbarIcon("restore_active")));
        restoreGameButton.setOnMouseEntered(event -> restoreGameButton.setGraphic(new ToolbarIcon("restore_hover")));
        restoreGameButton.setOnMouseExited(event -> restoreGameButton.setGraphic(new ToolbarIcon("restore")));
    }

    public Button getUndoButton() {
        return undoButton;
    }

    public Button getDefendButton() {
        return defendButton;
    }

    public Button getForfeitButton() {
        return forfeitButton;
    }

    public Button getSaveGameButton() {
        return saveGameButton;
    }

    public Button getRestoreGameButton() {
        return restoreGameButton;
    }
}
