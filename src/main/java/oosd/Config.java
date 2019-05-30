package oosd;

import oosd.factories.JsonGameSetupFactory;
import oosd.models.board.Board;
import oosd.models.game.Engine;
import oosd.models.game.GameEngine;
import oosd.models.game.GameLogger;
import oosd.models.player.Player;
import oosd.views.*;
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
        JsonGameSetupFactory factoryJSON = new JsonGameSetupFactory();
        Board board = factoryJSON.createBoard();
        List<Player> players = factoryJSON.createPlayers(board);
        return new GameEngine(board, players);
    }

    @Bean
    public GamePresenter gamePresenter() {
        return new GamePresenter(context);
    }

    @Bean
    public SidebarPresenter sidebarPresenter() {
        return new SidebarPresenter(context.getBean(Engine.class), context.getBean(GameLogger.class));
    }

    @Bean
    public ToolbarPresenter toolbarPresenter() {
        return new ToolbarPresenter(context, context.getBean(Engine.class));
    }

    @Bean
    public GameLogger gameLogger() {
        return new GameLogger();
    }

    @Bean
    public BoardView boardView() {
        return new BoardView(context.getBean(Engine.class), context.getBean(GamePresenter.class), context.getBean(ToolbarPresenter.class), context.getBean(SidebarPresenter.class));
    }

    @Bean
    public WelcomeView welcomeView() {
        return new WelcomeView();
    }
}
