package oosd.views.components.text;

import javafx.scene.text.Text;
import oosd.models.player.Player;

public class PlayerInfoText extends Text {
    public PlayerInfoText(Player player) {
        String playerName = player.getPlayerName();
        int aliveUnits = player.getAliveUnits().size();
        double wins = player.getWins();
        double losses = player.getLosses();
        double ratio = losses != 0 ? wins / losses : wins;

        getStyleClass().add("game-text-yellow");
        setLayoutX(65);
        setLayoutY(20);
        setText(String.format("\nPlayer: %s\nWin/Lose: %.0f/%.0f (%.2f)\nRemaining units: %d", playerName, wins, losses, ratio, aliveUnits));
    }
}
