package oosd.views;

import javafx.fxml.FXML;
import oosd.controllers.GameController;
import oosd.models.game.Engine;
import oosd.views.components.panes.BoardPane;
import oosd.views.components.panes.SidebarPane;
import oosd.views.components.panes.ToolbarPane;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class GamePresenter implements Presenter {
    final private Engine engine;
    private GameController gameController;
    @FXML
    private SidebarPane sidebar;
    @FXML
    private ToolbarPane toolbar;
    @FXML
    private BoardPane board;

    @Inject
    public GamePresenter(Engine engine) {
        this.engine = engine;
    }

    @FXML
    @Override
    public void initialize() {
        gameController = new GameController(engine, this);
        gameController.start();
    }

    public ToolbarPane getToolbarPane() {
        return toolbar;
    }

    public SidebarPane getSidebar() {
        return sidebar;
    }

    public BoardPane getBoardPane() {
        return board;
    }

    public GameController getGameController() {
        return gameController;
    }
}
