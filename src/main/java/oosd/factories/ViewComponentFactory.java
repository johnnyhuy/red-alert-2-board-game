package oosd.factories;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import oosd.views.View;
import oosd.views.components.PieceViewComponent;

/**
 * GRASP: The creator
 * This class is responsible for creating UI components in a board layout.
 * <p>
 * GRASP: Information expert
 * This factory should know the dimensions of the pieces on the board.
 * No one else should alter or make different sized polygons other than this class.
 */
public class ViewComponentFactory {
    private final double equalTriangleHeight = Math.sqrt(3);
    private final double halfIncrement = getSize() * (getEqualTriangleHeight() / 2.0);
    private final double fullIncrement = getSize() * getEqualTriangleHeight();
    private int columns;
    private int rows;
    private double gap = getSize() * 1.5;

    public ViewComponentFactory(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public ImagePattern createViewImage(String imageName) {
        Image image = new Image(View.class.getResource(imageName + ".png").toString());
        return new ImagePattern(image);
    }

    public PieceViewComponent createPieces() {
        Polygon[][] pieces = new Polygon[this.columns][this.rows];
        double x = 0;
        double y = 0;

        for (int xIndex = 0; xIndex < this.rows; xIndex++) {
            for (int yIndex = 0; yIndex < this.columns; yIndex++) {
                pieces[xIndex][yIndex] = new Polygon();
                pieces[xIndex][yIndex].getPoints().addAll(
                        x, y,
                        x + getSize(), y,
                        x + getGap(), y + getHalfIncrement(),
                        x + getSize(), y + getFullIncrement(),
                        x, y + getFullIncrement(),
                        x - (getSize() / 2.0), y + getHalfIncrement()
                );
            }
        }

        return new PieceViewComponent(pieces);
    }

    private double getSize() {
        return 40;
    }

    public double getEqualTriangleHeight() {
        return equalTriangleHeight;
    }

    public double getHalfIncrement() {
        return halfIncrement;
    }

    public double getFullIncrement() {
        return fullIncrement;
    }

    public double getGap() {
        return gap;
    }
}
