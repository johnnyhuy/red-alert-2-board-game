package oosd.views.components.text;

import javafx.scene.text.Text;
import oosd.models.game.Engine;

public class TurnCountText extends Text {
    public TurnCountText(Engine engine) {
        getStyleClass().add("game-text-yellow");
        setLayoutX(65);
        setLayoutY(20);
        setText(String.format("Remaining turns: %s", engine.getRemainingTurns()));
    }
}
