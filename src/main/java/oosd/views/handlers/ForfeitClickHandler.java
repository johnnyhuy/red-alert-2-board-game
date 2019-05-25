package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.game.Engine;
import oosd.models.player.Player;

import java.util.Optional;

public class ForfeitClickHandler implements EventHandler<MouseEvent> {
    private Engine engine;
    private GameController gameController;

    public ForfeitClickHandler(Engine engine, GameController gameController) {
        this.engine = engine;
        this.gameController = gameController;
    }

    @Override
    public void handle(MouseEvent event) {
        Player player = engine.getTurn();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Forfeit game");
        alert.setHeaderText("Forfeiting the game");
        alert.setContentText(String.format("%s wants to forfeit the game, are you sure commander?", player.getPlayerName()));
        Optional<ButtonType> result = alert.showAndWait();

        if (!result.isPresent()) {
            return;
        }

        if (result.get().equals(ButtonType.OK)) {
            gameController.forfeit();
        }
    }
}
