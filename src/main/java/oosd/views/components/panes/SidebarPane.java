package oosd.views.components.panes;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import oosd.models.game.Engine;
import oosd.models.player.Player;

import static oosd.helpers.ObjectHelper.exists;

public class SidebarPane extends AnchorPane {
    public Text getPlayerTurnText() {
        return (Text) this.lookup("#playerTurn");
    }

    public Text getTurnCountText() {
        return (Text) this.lookup("#turnCount");
    }

    public void initialise(Engine engine) {
        Player player = engine.getTurn();
        Text playerTurn = getPlayerTurnText();
        Text turnCountText = getTurnCountText();

        if (exists(player)) {
            playerTurn.setText("Player turn: " + player.getPlayerName());
        }

        turnCountText.setText("Remaining turns: " + engine.getRemainingTurns());
    }
}
