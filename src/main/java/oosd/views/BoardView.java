package oosd.views;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Hexagon;

/**
 * SOLID:  Single Responsibility Principle
 * The view should only be responsible for managing the user interface (e.g. interacting with the JavaFX library)
 */
public class BoardView extends View {
    private final GameController controller;
    private final GameEngine gameEngine;
    private final AnchorPane boardPane;
    private final Polygon[][] hexagonPolygons;
    private final Board board;

    private final double size = 50;
    private final double gap = size * 1.5;
    private final double tHeight = Math.sqrt(3);
    private final double halfIncrement = size * (tHeight / 2.0);
    private final double fullIncrement = size * tHeight;

    public BoardView(GameController controller, GameEngine gameEngine, AnchorPane boardPane) {
        this.controller = controller;
        this.gameEngine = gameEngine;
        this.boardPane = boardPane;
        this.board = this.gameEngine.getBoard();
        this.hexagonPolygons = new Polygon[board.getColumns()][board.getRows()];
    }

    public void selectUnit(Hexagon previousHexagon, Hexagon hexagon) {
        if (previousHexagon != null) {
            this.hexagonPolygons[previousHexagon.getColumn()][previousHexagon.getRow()].setFill(Paint.valueOf("#ffffff"));
        }

        this.hexagonPolygons[hexagon.getColumn()][hexagon.getRow()].setFill(Paint.valueOf("#dadada"));
    }

    public void initialize() {
        Hexagon[][] hexagons = board.getHexagons();

        int xOffset = 80;
        int yOffset = 80;
        int hexagonCount = 0;
        double x = 0;
        double y = 0;

        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                Hexagon hexagon = hexagons[xIndex][yIndex];

                Polygon hexagonPolygon = DrawHexagon(x, y, hexagon);
                this.hexagonPolygons[xIndex][yIndex] = hexagonPolygon;

                Circle unitCircle = new Circle();
                unitCircle.setRadius(size / 4);
                unitCircle.setFill(Paint.valueOf("#DADADA"));
                double unitOpacity = hexagon.getUnit() != null ? 1 : 0;
                unitCircle.setOpacity(unitOpacity);

                String name = hexagon.getUnit() != null ? hexagon.getUnit().getName() : "";
                final Text text = new Text();
                text.setText(name);

                final StackPane stack = new StackPane();
                stack.getChildren().addAll(hexagonPolygon, unitCircle, text);
                stack.setLayoutX(xOffset + x);
                stack.setLayoutY(yOffset + y);
                stack.setAlignment(Pos.CENTER);

                this.boardPane.getChildren().add(stack);

                // Every even element set the y value down
                if (hexagonCount % 2 == 0) {
                    y = y + halfIncrement;
                } else {
                    y = y - halfIncrement;
                }

                hexagonCount++;

                x = xIndex == board.getColumns() - 1 ? 0 : x + gap;
            }

            y = yIndex == board.getRows() - 1 ? 0 : y + fullIncrement;
        }
    }

    private Polygon DrawHexagon(double x, double y, Hexagon hexagon) {
        Polygon hexagonPolygon = new Polygon();
        hexagonPolygon.setOnMouseClicked(event -> controller.board(event, hexagon));
        hexagonPolygon.setFill(Paint.valueOf("#ffffff"));
        hexagonPolygon.setStrokeWidth(2);
        hexagonPolygon.setStroke(Paint.valueOf("#000000"));
        hexagonPolygon.getPoints().addAll(
                x, y,
                x + size, y,
                x + gap, y + halfIncrement,
                x + size, y + fullIncrement,
                x, y + fullIncrement,
                x - (size / 2.0), y + halfIncrement
        );

        return hexagonPolygon;
    }
}
