package oosd.views.factories;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.views.View;
import oosd.views.components.BackgroundPiecePolygon;
import oosd.views.components.DefendPieceImage;
import oosd.views.components.SelectionPiecePolygon;
import oosd.views.components.UnitPiecePolygon;

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
public class ViewComponentFactory {
    private Board board;

    public ViewComponentFactory(Board board) {
        this.board = board;
    }

    public ImagePattern createImage(String imageName) {
        Image image = new Image(View.class.getResource(imageName + ".png").toString());
        return new ImagePattern(image);
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
