package oosd.views.components.panes;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import oosd.models.game.Engine;
import oosd.models.player.Player;

import static oosd.helpers.ObjectHelper.exists;

public class SidebarPane extends Pane {
    public Text getPlayerTurnText() {
        return (Text) this.lookup("#playerTurn");
    }

    public void initialise(Engine engine) {
        Player player = engine.getTurn();
        Text playerTurn = getPlayerTurnText();

        if (exists(player)) {
            playerTurn.setText("Player turn: " + player.getPlayerName());
        }
    }
}
