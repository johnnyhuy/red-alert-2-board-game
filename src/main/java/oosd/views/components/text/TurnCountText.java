package oosd.views.components.text;

import javafx.scene.text.Text;
import oosd.models.game.Engine;

public class TurnCountText extends Text {
    public TurnCountText() {
        getStyleClass().add("game-text");
        setLayoutX(65);
        setLayoutY(20);
    }

    public void updateTurn(Engine engine) {
        setText(String.format("Remaining turns: %s", engine.getRemainingTurns()));
    }
}
