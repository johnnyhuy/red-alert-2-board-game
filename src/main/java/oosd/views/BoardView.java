package oosd.views;

import oosd.controllers.GameController;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.views.components.polygons.BoardPolygonManager;
import oosd.views.components.polygons.BoardPolygonMap;
import oosd.views.presenters.GamePresenter;
import oosd.views.presenters.SidebarPresenter;
import oosd.views.presenters.ToolbarPresenter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * SOLID:  Single Responsibility Principle
 * The view should only be responsible for managing the user interface (e.g. interacting with the JavaFX library)
 */
@Component
public class BoardView implements View {
    private final GamePresenter gamePresenter;
    private final Engine engine;
    private BoardPolygonMap boardPolygonMap;
    private ToolbarPresenter toolbarPresenter;
    private SidebarPresenter sidebarPresenter;
    private GameController gameController;
    private BoardPolygonManager boardPolygonManager;

    @Inject
    private ApplicationContext context;

    @Inject
    public BoardView(Engine engine, GamePresenter gamePresenter, ToolbarPresenter toolbarPresenter, SidebarPresenter sidebarPresenter) {
        this.gamePresenter = gamePresenter;
        this.engine = engine;
        this.toolbarPresenter = toolbarPresenter;
        this.sidebarPresenter = sidebarPresenter;
    }

    public void start() {
        boardPolygonMap = context.getBean(BoardPolygonMap.class);
        gameController = context.getBean(GameController.class);
        boardPolygonManager = context.getBean(BoardPolygonManager.class);
        boardPolygonManager.createBoard();
    }

    public void selectUnit(Piece selectedPiece, Piece clickedPiece) {
        boardPolygonManager.selectPiece(selectedPiece, clickedPiece);
    }

    public void update() {
        boardPolygonManager.updateBoard();
    }
}
