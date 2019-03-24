package oosd.views;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import oosd.controllers.GameController;
import oosd.models.board.Board;
import oosd.models.board.Hexagon;

public class BoardView extends View {
    private final GameController controller;
    private final Board board;
    private final AnchorPane tilePane;

    public BoardView(GameController controller, Board board, AnchorPane tilePane) {
        this.controller = controller;
        this.board = board;
        this.tilePane = tilePane;
    }

    @Override
    public void render() {
        Hexagon[][] hexagons = this.board.getHexagons();

        // Size of each side
        final double size = 50;

        // Height of the equilateral triangle
        final double tHeight = Math.sqrt(3);

        // Small gap between each shape (the left/right triangle bit)
        double gap = size * 1.5;

        // Increments used to place the next polygon
        double halfIncrement = size * (tHeight / 2.0);
        double fullIncrement = size * tHeight;

        int xOffset = 100;
        int yOffset = 100;

        int hexagonCount = 0;
        double x = 0;
        double y = 0;

        for (int yIndex = 0; yIndex < this.board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < this.board.getColumns(); xIndex++) {
                Hexagon hexagon = hexagons[xIndex][yIndex];
                hexagon.setOnMouseClicked(event -> controller.handleHexagonClick(event, hexagon));

                hexagon.getPoints().addAll(
                        xOffset + x, yOffset + y,
                        xOffset + x + size, yOffset + y,
                        xOffset + x + gap, yOffset + y + halfIncrement,
                        xOffset + x + size, yOffset + y + fullIncrement,
                        xOffset + x, yOffset + y + fullIncrement,
                        xOffset + x - (size / 2.0), yOffset + y + halfIncrement
                );

                hexagon.setFill(Paint.valueOf("#ffffff"));
                hexagon.setStrokeWidth(2);
                hexagon.setStroke(Paint.valueOf("#000000"));
                this.tilePane.getChildren().add(hexagon);

                // Every even element set the y value down
                if (hexagonCount % 2 == 0) {
                    y = y + halfIncrement;
                } else {
                    y = y - halfIncrement;
                }

                hexagonCount++;

                x = xIndex == this.board.getColumns() - 1 ? 0 : x + gap;
            }

            y = yIndex == this.board.getRows() - 1 ? 0 : y + fullIncrement;
        }
    }
}
