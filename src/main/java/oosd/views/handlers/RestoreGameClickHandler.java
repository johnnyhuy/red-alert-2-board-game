package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.game.Engine;
import oosd.models.player.Player;

import java.util.Optional;

public class RestoreGameClickHandler implements EventHandler<MouseEvent> {
    private Engine engine;
    private GameController gameController;

    public RestoreGameClickHandler(Engine engine, GameController gameController) {
        this.engine = engine;
        this.gameController = gameController;
    }

    @Override
    public void handle(MouseEvent event) {
        if (!engine.saveGameExists()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot restore game");
            alert.setHeaderText("You cannot restore a saved game!");
            alert.setContentText("Commander, you need save a game before restoring.");
            alert.showAndWait();

            return;
        }

        Player player = engine.getTurn();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Restore game");
        alert.setHeaderText("Restore from the last saved state of the game.");
        alert.setContentText(String.format("%s wants to restore the game, are you sure commander?", player.getPlayerName()));
        Optional<ButtonType> result = alert.showAndWait();

        if (!result.isPresent()) {
            return;
        }

        if (result.get().equals(ButtonType.OK)) {
            gameController.restoreGame();
        }
    }
}
