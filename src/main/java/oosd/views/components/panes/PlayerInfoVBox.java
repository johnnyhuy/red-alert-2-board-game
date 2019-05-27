package oosd.views.components.panes;

import javafx.scene.layout.VBox;
import oosd.models.game.Engine;
import oosd.models.player.Player;
import oosd.views.components.text.PlayerInfoText;
import oosd.views.components.text.TurnCountText;
import org.springframework.stereotype.Component;

@Component
public class PlayerInfoVBox extends VBox {
    public void update(Engine engine) {
        getChildren().clear();
        getChildren().add(new TurnCountText(engine));

        for (Player player : engine.getPlayers()) {
            PlayerInfoText text = new PlayerInfoText(player);
            getChildren().add(text);
        }
    }
}
