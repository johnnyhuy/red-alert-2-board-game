package oosd.models.game;

import oosd.models.player.Player;

import java.util.List;
import java.util.ListIterator;

import static oosd.helpers.ListHelper.isNotEmpty;

/**
 * GRASP: information export, where the turn service should only manage
 * player turns on a given game.
 * <p>
 * We've defined it as a service to keep it generic as possible when dealing
 * with player turns.
 */
public class GameTurnService implements TurnService {
    private ListIterator<Player> playerIterator;
    private Player turn;
    private PlayerService playerService;
    private int turnLimit;

    public GameTurnService(PlayerService playerService, int turnLimit) {
        this.playerService = playerService;
        this.turnLimit = turnLimit;

        // Whoever we add to the players list, the first one takes the turn
        List<Player> players = playerService.getPlayers();
        if (isNotEmpty(players)) {
            resetTurn();
        }
    }

    @Override
    public Player getTurn() {
        return this.turn;
    }

    @Override
    public void setTurn(Player player) {
        this.playerIterator = getPlayers().listIterator();

        while (playerIterator.hasNext()) {
            Player nextPlayer = playerIterator.next();
            if (nextPlayer.equals(player)) {
                this.turn = nextPlayer;
                break;
            }
        }
    }

    @Override
    public int getTurns() {
        int turnCount = 0;

        for (Player player : getPlayers()) {
            turnCount += player.getTurns();
        }

        return turnCount;
    }

    @Override
    public int getRemainingTurns() {
        int turns = turnLimit - getTurns();

        // A bit of defensive programming here
        return turns < 0 ? 0 : turns;
    }

    @Override
    public int getTurnLimit() {
        return this.turnLimit;
    }

    @Override
    public void getNextTurn() {
        if (!playerIterator.hasNext()) {
            playerIterator = getPlayers().listIterator();
        }

        turn.incrementTurn();
        turn = playerIterator.next();
    }

    @Override
    public void resetTurn() {
        this.playerIterator = getPlayers().listIterator();
        this.turn = playerIterator.next();
    }

    private List<Player> getPlayers() {
        return this.playerService.getPlayers();
    }
}
