package oosd.factories;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

/**
 * GRASP: The creator
 * This class is responsible for creating UI components in a board layout.
 * <p>
 * GRASP: Information expert
 * This factory should know the dimensions of the hexagons on the board.
 * No one else should alter or make different sized polygons other than this class.
 */
public class BoardFactory {
    private final double equalTriangleHeight = Math.sqrt(3);
    private final double halfIncrement = getSize() * (getEqualTriangleHeight() / 2.0);
    private final double fullIncrement = getSize() * getEqualTriangleHeight();
    private int columns;
    private int rows;
    private double gap = getSize() * 1.5;

    public BoardFactory(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public Circle[][] createUnitCircle() {
        Circle[][] unitCircles = new Circle[this.columns][this.rows];

        for (int xIndex = 0; xIndex < this.rows; xIndex++) {
            for (int yIndex = 0; yIndex < this.columns; yIndex++) {
                unitCircles[xIndex][yIndex] = new Circle();
                unitCircles[xIndex][yIndex].setRadius(getSize() / 4);
                unitCircles[xIndex][yIndex].setFill(Paint.valueOf("#DADADA"));
            }
        }

        return unitCircles;
    }

    public Polygon[][] createHexagon() {
        Polygon[][] hexagons = new Polygon[this.columns][this.rows];
        double x = 0;
        double y = 0;

        for (int xIndex = 0; xIndex < this.rows; xIndex++) {
            for (int yIndex = 0; yIndex < this.columns; yIndex++) {
                hexagons[xIndex][yIndex] = new Polygon();
                hexagons[xIndex][yIndex].setFill(Paint.valueOf("#ffffff"));
                hexagons[xIndex][yIndex].setStrokeWidth(2);
                hexagons[xIndex][yIndex].setStroke(Paint.valueOf("#000000"));
                hexagons[xIndex][yIndex].getPoints().addAll(
                        x, y,
                        x + getSize(), y,
                        x + getGap(), y + getHalfIncrement(),
                        x + getSize(), y + getFullIncrement(),
                        x, y + getFullIncrement(),
                        x - (getSize() / 2), y + getHalfIncrement()
                );
            }
        }

        return hexagons;
    }

    public Text[][] createUnitText() {
        Text[][] text = new Text[this.columns][this.rows];

        for (int xIndex = 0; xIndex < this.rows; xIndex++) {
            for (int yIndex = 0; yIndex < this.columns; yIndex++) {
                text[xIndex][yIndex] = new Text();
            }
        }

        return text;
    }

    public double getSize() {
        return 50;
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
