package oosd.views.factories;

import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.views.components.images.DefendPieceImage;
import oosd.views.components.polygons.BackgroundPiecePolygon;
import oosd.views.components.polygons.SelectionPiecePolygon;
import oosd.views.components.polygons.UnitPiecePolygon;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashMap;

/**
 * GRASP: The creator
 * This class is responsible for creating UI components in a board layout.
 *
 * GRASP: Information expert
 * This factory should know the dimensions of the pieces on the board.
 * No one else should alter or make different sized polygons other than this class.
 *
 * Design pattern: factory pattern used to produce custom view components for the UI.
 */
@Component
public class ViewComponentFactory {
    private Board board;

    @Inject
    public ViewComponentFactory(Engine engine) {
        this.board = engine.getBoard();
    }

    public HashMap<Piece, UnitPiecePolygon> createUnitPiecePolygons() {
        HashMap<Piece, UnitPiecePolygon> pieces = new HashMap<>();
        board.apply((column, row) -> pieces.put(board.getPiece(column, row), new UnitPiecePolygon()));

        return pieces;
    }

    public HashMap<Piece, SelectionPiecePolygon> createSelectionPiecePolygons() {
        HashMap<Piece, SelectionPiecePolygon> pieces = new HashMap<>();
        board.apply((column, row) -> pieces.put(board.getPiece(column, row), new SelectionPiecePolygon()));

        return pieces;
    }

    public HashMap<Piece, DefendPieceImage> createDefendPieceImage() {
        HashMap<Piece, DefendPieceImage> pieces = new HashMap<>();
        board.apply((column, row) -> pieces.put(board.getPiece(column, row), new DefendPieceImage()));

        return pieces;
    }

    public HashMap<Piece, BackgroundPiecePolygon> createBackgroundPiecePolygons() {
        HashMap<Piece, BackgroundPiecePolygon> pieces = new HashMap<>();
        board.apply((column, row) -> pieces.put(board.getPiece(column, row), new BackgroundPiecePolygon()));

        return pieces;
    }
}
