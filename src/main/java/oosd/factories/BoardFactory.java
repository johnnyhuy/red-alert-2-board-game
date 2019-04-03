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
    private final double size = 50;
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

        for (int columnIndex = 0; columnIndex < this.rows; columnIndex++) {
            for (int rowIndex = 0; rowIndex < this.columns; rowIndex++) {
                unitCircles[columnIndex][rowIndex] = new Circle();
                unitCircles[columnIndex][rowIndex].setRadius(getSize() / 4);
                unitCircles[columnIndex][rowIndex].setFill(Paint.valueOf("#DADADA"));
            }
        }

        return unitCircles;
    }

    public Polygon[][] createHexagon() {
        Polygon[][] hexagons = new Polygon[this.columns][this.rows];
        double x = 0;
        double y = 0;

        for (int columnIndex = 0; columnIndex < this.rows; columnIndex++) {
            for (int rowIndex = 0; rowIndex < this.columns; rowIndex++) {
                hexagons[columnIndex][rowIndex] = new Polygon();
                hexagons[columnIndex][rowIndex].setFill(Paint.valueOf("#ffffff"));
                hexagons[columnIndex][rowIndex].setStrokeWidth(2);
                hexagons[columnIndex][rowIndex].setStroke(Paint.valueOf("#000000"));
                hexagons[columnIndex][rowIndex].getPoints().addAll(
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

        for (int columnIndex = 0; columnIndex < this.rows; columnIndex++) {
            for (int rowIndex = 0; rowIndex < this.columns; rowIndex++) {
                text[columnIndex][rowIndex] = new Text();
            }
        }

        return text;
    }

    public double getSize() {
        return size;
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
