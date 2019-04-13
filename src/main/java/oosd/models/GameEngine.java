package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.player.Player;

import java.util.Iterator;
import java.util.List;

import static oosd.helpers.ListHelper.isNotEmpty;

// TODO: Convert this to C4J
public class GameEngine {
    private Board board;
    private Hexagon selectedHexagon;
    private Player turn;
    private List<Player> players;
    private Iterator<Player> playersIterator;

    public GameEngine(Board board, List<Player> players) {
        this.board = board;
        this.players = players;

        // Whoever we add to the players list, the first one takes the turn
        if (isNotEmpty(players)) {
            this.playersIterator = players.listIterator();
            this.turn = playersIterator.next();
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

    /**
     * Get the players in the game.
     *
     * @return list of players in the game
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Get the turn of the game.
     *
     * @return a player in the turn
     */
    public Player getTurn() {
        return this.turn;
    }

    /**
     * Get the next turn by going through the list sequentially.
     *
     * @return player in the turn
     */
    public Player getNextTurn() {
        if (playersIterator.hasNext()) {
            Player nextPlayer = playersIterator.next();
            turn = nextPlayer;

            return nextPlayer;
        }

        playersIterator = players.listIterator();
        Player nextPlayer = playersIterator.next();
        turn = nextPlayer;

        return nextPlayer;
    }
}
