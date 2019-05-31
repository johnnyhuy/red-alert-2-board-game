package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.game.Engine;
import oosd.models.player.Player;

import java.util.Optional;

public class SaveGameClickHandler implements EventHandler<MouseEvent> {
    private Engine engine;
    private GameController gameController;

    public SaveGameClickHandler(Engine engine, GameController gameController) {
        this.engine = engine;
        this.gameController = gameController;
    }

    @Override
    public void handle(MouseEvent event) {
        if (engine.saveGameExists()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot save game");
            alert.setHeaderText("You already have a saved game!");
            alert.setContentText("Commander, you need to restore the previous game before continuing.");
            alert.showAndWait();

            return;
        }

        Player player = engine.getTurnService().getTurn();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save game");
        alert.setHeaderText("Save the current state of the game");
        alert.setContentText(String.format("%s wants to save the game, are you sure commander?", player.getPlayerName()));
        Optional<ButtonType> result = alert.showAndWait();

        if (!result.isPresent()) {
            return;
        }

        if (result.get().equals(ButtonType.OK)) {
            gameController.saveGame();
        }
    }
}
