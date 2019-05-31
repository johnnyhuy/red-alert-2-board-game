package oosd.views.components.text;

import javafx.scene.text.Text;
import oosd.models.game.Engine;

public class PlayerTurnText extends Text {
    public PlayerTurnText() {
        getStyleClass().add("game-text-yellow");
        setLayoutX(65);
        setLayoutY(20);
    }

    public void updateTurn(Engine engine) {
        setText(String.format("Player turn: %s", engine.getTurnService().getTurn().getPlayerName()));
    }
}
