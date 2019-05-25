package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.game.Engine;
import oosd.models.player.Player;

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

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Forfeit game");
        alert.setHeaderText("Forfeiting the game");
        alert.setContentText(String.format("%s wants to forfeit the game, are you sure commander?", player.getPlayerName()));
        alert.showAndWait();

        gameController.forfeit();
    }
}
