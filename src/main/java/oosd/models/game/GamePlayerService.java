package oosd.models.game;

import oosd.models.player.Player;

import java.util.List;

/**
 * GRASP: information export, where the turn service should only manage
 * player turns on a given game.
 * <p>
 * We've defined it as a service to keep it generic as possible when dealing
 * with player turns.
 */
public class GamePlayerService implements PlayerService {
    private List<Player> players;

    public GamePlayerService(List<Player> players) {
        this.players = players;
    }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
