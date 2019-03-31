package oosd.views;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Hexagon;

public class BoardView extends View {
    private final GameController controller;
    private final GameEngine gameEngine;
    private final AnchorPane boardPane;

    public BoardView(GameController controller, GameEngine gameEngine, AnchorPane boardPane) {
        this.controller = controller;
        this.gameEngine = gameEngine;
        this.boardPane = boardPane;
    }

    @Override
    public void render() {
        Board board = this.gameEngine.getBoard();
        Hexagon[][] hexagons = board.getHexagons();

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

        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                Hexagon hexagon = hexagons[xIndex][yIndex];

                Polygon hexagonPolygon = new Polygon();
                hexagonPolygon.setOnMouseClicked(event -> controller.handleHexagonClick(event, hexagon));
                hexagonPolygon.getPoints().addAll(
                        xOffset + x, yOffset + y,
                        xOffset + x + size, yOffset + y,
                        xOffset + x + gap, yOffset + y + halfIncrement,
                        xOffset + x + size, yOffset + y + fullIncrement,
                        xOffset + x, yOffset + y + fullIncrement,
                        xOffset + x - (size / 2.0), yOffset + y + halfIncrement
                );

                hexagonPolygon.setFill(Paint.valueOf("#ffffff"));
                hexagonPolygon.setStrokeWidth(2);
                hexagonPolygon.setStroke(Paint.valueOf("#000000"));
                this.boardPane.getChildren().add(hexagonPolygon);

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
}
