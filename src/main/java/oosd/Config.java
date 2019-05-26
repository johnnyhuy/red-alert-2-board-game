package oosd;

import oosd.factories.JsonGameSetupFactory;
import oosd.models.board.Board;
import oosd.models.game.Engine;
import oosd.models.game.GameEngine;
import oosd.models.player.Player;
import oosd.views.GamePresenter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import java.util.List;

@Configuration
public class Config {
    private ApplicationContext applicationContext;

    @Inject
    public Config(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public Engine engine() {
        JsonGameSetupFactory factoryJSON = new JsonGameSetupFactory();
        Board board = factoryJSON.createBoard();
        List<Player> players = factoryJSON.createPlayers(board);
        return new GameEngine(board, players);
    }

    @Bean
    @Scope("prototype")
    public GamePresenter gamePresenter() {
        Engine engine = applicationContext.getBean(Engine.class);
        return new GamePresenter(engine);
    }
}
