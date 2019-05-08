package oosd.views.factories;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.views.View;

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

    // TODO: piece positions aren't getting saved properly with this
    public <T> HashMap<Piece, T> createPiecePolygons(T polygon) {
        HashMap<Piece, T> pieces = new HashMap<>();

        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                pieces.put(board.getPiece(xIndex, yIndex), polygon);
            }
        }

        return pieces;
    }
}
