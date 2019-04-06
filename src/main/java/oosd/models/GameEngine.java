package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.player.Player;

import java.util.Iterator;
import java.util.List;

public class GameEngine {
    private Board board;
    private Hexagon selectedHexagon;
    private Player turn;
    private List<Player> players;

    public GameEngine(Board board, List<Player> players) {
        this.board = board;
        this.players = players;

        // Whoever we add to the players list, the first one takes the turn
        Player firstPlayer = players.get(0);
        if (firstPlayer != null) {
            this.turn = firstPlayer;
        }
    }

    /**
     * Get the game board.
     *
     * @return board that contains the game
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Get the selected hexagon user clicks.
     *
     * @return selected hexagon
     */
    public Hexagon getSelectedHexagon() {
        return selectedHexagon;
    }

    /**
     * Set the selected hexagon on in the game.
     *
     * @param selectedHexagon selected hexagon
     */
    public void setSelectedHexagon(Hexagon selectedHexagon) {
        this.selectedHexagon = selectedHexagon;
    }
}
