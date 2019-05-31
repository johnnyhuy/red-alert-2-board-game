package oosd;

import oosd.factories.GameSetupFactory;
import oosd.factories.JsonGameSetupFactory;
import oosd.models.board.Board;
import oosd.models.game.*;
import oosd.views.BoardView;
import oosd.views.WelcomeView;
import oosd.views.presenters.GamePresenter;
import oosd.views.presenters.SidebarPresenter;
import oosd.views.presenters.ToolbarPresenter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

/**
 * Dependency injection: used to de-couple high level components from low level components
 * where we're generally using singleton objects to initialize and capture components.
 * <p>
 * Design pattern: creator pattern is used since where managing mostly singletons in the
 * startup Spring application context.
 */
@Configuration
@ComponentScan("oosd")
public class Config {
    private ApplicationContext context;

    @Inject
    public Config(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    /**
     * Switch this to factory out for in-memory or JSON game setup.
     *
     * @return game setup factory
     */
    @Bean
    public GameSetupFactory gameSetupFactory() {
        return new JsonGameSetupFactory();
    }

    /**
     * Low level component, the game engine.
     *
     * @return game engine
     */
    @Bean
    public Engine engine() {
        return new GameEngine(context.getBean(Board.class), context.getBean(PlayerService.class), context.getBean(TurnService.class));
    }

    /**
     * The game board
     *
     * @return game board
     */
    @Bean
    public Board board() {
        GameSetupFactory factory = context.getBean(GameSetupFactory.class);
        return factory.createBoard();
    }

    @Bean
    public PlayerService playerService() {
        GameSetupFactory factory = context.getBean(GameSetupFactory.class);
        return new GamePlayerService(factory.createPlayers(context.getBean(Board.class)));
    }

    @Bean
    public TurnService turnService() {
        return new GameTurnService(context.getBean(PlayerService.class), 10);
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
    public Logger gameLogger() {
        return new GameLogger();
    }

    @Bean
    public BoardView boardView() {
        return new BoardView();
    }

    @Bean
    public WelcomeView welcomeView() {
        return new WelcomeView();
    }
}
