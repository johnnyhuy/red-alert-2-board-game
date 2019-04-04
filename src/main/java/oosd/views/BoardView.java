package oosd.views;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import oosd.controllers.GameController;
import oosd.factories.BoardFactory;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Hexagon;

/**
 * SOLID:  Single Responsibility Principle
 * The view should only be responsible for managing the user interface (e.g. interacting with the JavaFX library)
 */
public class BoardView extends View {
    private final Board board;
    private final GameController controller;
    private final AnchorPane boardPane;
    private final Polygon[][] hexagonPolygons;
    private final Text[][] unitText;
    private final Circle[][] unitCircles;
    private final BoardFactory boardFactory;

    public BoardView(GameController controller, GameEngine gameEngine, AnchorPane boardPane) {
        this.controller = controller;
        this.boardPane = boardPane;
        this.board = gameEngine.getBoard();

        this.boardFactory = new BoardFactory(board.getColumns(), board.getRows());
        this.hexagonPolygons = boardFactory.createHexagon();
        this.unitText = boardFactory.createUnitText();
        this.unitCircles = boardFactory.createUnitCircle();
    }

    public void selectUnit(Hexagon previousHexagon, Hexagon hexagon) {
        if (previousHexagon != null) {
            hexagonPolygons[previousHexagon.getColumn()][previousHexagon.getRow()].setFill(Paint.valueOf("#ffffff"));
        }

        int northEastOffset = 0;
        int northWestOffset = 0;
        int southEastOffset = 0;
        int southWestOffset = 0;

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
            int north = hexagon.getRow() - move;
            if (north >= board.getRows() || north < 0) {
                continue;
            }

            hexagonPolygons[hexagon.getColumn()][north].setFill(Paint.valueOf("green"));
        }

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
            int south = hexagon.getRow() + move;
            if (south >= board.getRows()) {
                continue;
            }

            hexagonPolygons[hexagon.getColumn()][south].setFill(Paint.valueOf("green"));
        }

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
            int west = hexagon.getColumn() - move;
            if (west >= board.getColumns() || west < 0) {
                continue;
            }

            if (west % 2 != 0) {
                northWestOffset++;
            }

            int northWest = hexagon.getRow() - northWestOffset;
            if (northWest >= board.getRows() || northWest < 0) {
                continue;
            }

            hexagonPolygons[west][northWest].setFill(Paint.valueOf("green"));
        }

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
            int west = hexagon.getColumn() - move;
            if (west >= board.getColumns() || west < 0) {
                continue;
            }

            if (west % 2 == 0) {
                southWestOffset++;
            }

            int southWest = hexagon.getRow() + southWestOffset;
            if (southWest >= board.getRows()) {
                continue;
            }

            hexagonPolygons[west][southWest].setFill(Paint.valueOf("red"));
        }

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
            int east = hexagon.getColumn() + move;
            if (east >= board.getColumns()) {
                continue;
            }

            if (east % 2 != 0) {
                northEastOffset++;
            }

            int northEast = hexagon.getRow() - northEastOffset;
            if (northEast >= board.getRows() || northEast < 0) {
                continue;
            }

            hexagonPolygons[east][northEast].setFill(Paint.valueOf("green"));
        }

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
            int east = hexagon.getColumn() + move;
            if (east >= board.getColumns()) {
                continue;
            }

            if (east % 2 == 0) {
                southEastOffset++;
            }

            int southEast = hexagon.getRow() + southEastOffset;
            if (southEast >= board.getRows()) {
                continue;
            }

            hexagonPolygons[east][southEast].setFill(Paint.valueOf("red"));
        }

        hexagonPolygons[hexagon.getColumn()][hexagon.getRow()].setFill(Paint.valueOf("#dadada"));
    }

    public void initialize() {
        Hexagon[][] hexagons = board.getHexagons();

        int xOffset = 80;
        int yOffset = 80;
        double x = 0;
        double y = 0;
        int hexagonCount = 0;
        int rows = board.getRows();
        int columns = board.getColumns();

        for (int yIndex = 0; yIndex < rows; yIndex++) {
            for (int xIndex = 0; xIndex < columns; xIndex++) {
                Hexagon hexagon = hexagons[xIndex][yIndex];

                double unitOpacity = hexagon.getUnit() != null ? 1 : 0;
                unitCircles[xIndex][yIndex].setOpacity(unitOpacity);

                String name = hexagon.getUnit() != null ? hexagon.getUnit().getName() : "";
//                unitText[xIndex][yIndex].setText(name);

                Text test = new Text(xIndex + ", " + yIndex);

                final StackPane stack = new StackPane();
                stack.setOnMouseClicked(event -> controller.board(event, hexagon));
                stack.getChildren().addAll(hexagonPolygons[xIndex][yIndex], unitCircles[xIndex][yIndex], unitText[xIndex][yIndex], test);
                stack.setLayoutX(xOffset + x);
                stack.setLayoutY(yOffset + y);
                stack.setAlignment(Pos.CENTER);

                boardPane.getChildren().add(stack);

                if (hexagonCount % 2 == 0) {
                    y = y + boardFactory.getHalfIncrement();
                } else {
                    y = y - boardFactory.getHalfIncrement();
                }

                hexagonCount++;

                x = xIndex == columns - 1 ? 0 : x + boardFactory.getGap();
            }

            y = yIndex == rows - 1 ? 0 : y + boardFactory.getFullIncrement();
        }
    }
}
