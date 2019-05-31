package oosd.views;

import oosd.models.board.Piece;
import oosd.views.components.polygons.BoardPolygonManager;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * SOLID:  Single Responsibility Principle
 * The view should only be responsible for managing the user interface (e.g. interacting with the JavaFX library)
 */
@Component
public class BoardView implements View {
    private BoardPolygonManager boardPolygonManager;

    @Inject
    private ApplicationContext context;

    public void start() {
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
