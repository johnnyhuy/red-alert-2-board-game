package oosd.models.game;

import oosd.models.player.Player;

import java.util.List;

public interface PlayerService {
    /**
     * Get the players in the game.
     *
     * @return list of players in the game
     */
    List<Player> getPlayers();

    /**
     * Setup players.
     *
     * @param players list of players
     */
    void setPlayers(List<Player> players);
}
