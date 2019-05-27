package oosd;

import oosd.factories.InMemoryGameSetupFactory;
import oosd.models.board.Board;
import oosd.models.game.Engine;
import oosd.models.game.GameEngine;
import oosd.models.player.Player;
import oosd.views.BoardView;
import oosd.views.GamePresenter;
import oosd.views.WelcomeView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;
import java.util.List;

@Configuration
@ComponentScan("oosd")
public class Config {
    private ApplicationContext context;

    @Inject
    public Config(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    @Bean
    public Engine engine() {
        InMemoryGameSetupFactory factoryJSON = new InMemoryGameSetupFactory();
        Board board = factoryJSON.createBoard();
        List<Player> players = factoryJSON.createPlayers(board);
        return new GameEngine(board, players);
    }

    @Bean
    public GamePresenter gamePresenter() {
        return new GamePresenter();
    }

    @Bean
    public BoardView boardView() {
        return new BoardView(context.getBean(Engine.class), context.getBean(GamePresenter.class));
    }

    @Bean
    public WelcomeView welcomeView() {
        return new WelcomeView();
    }
}
