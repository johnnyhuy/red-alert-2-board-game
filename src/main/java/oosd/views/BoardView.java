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

        int northEastMoveOffset = 0;
        int northWestMoveOffset = 0;
        int southEastMoveOffset = 0;
        int southWestMoveOffset = 0;

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
            int north = hexagon.getRow() - move;
            if (north >= board.getRows() || north < 0) {
                north = hexagon.getRow();
            }

            int south = hexagon.getRow() + move;
            if (south >= board.getRows()) {
                south = hexagon.getRow();
            }

            int west = hexagon.getColumn() - move;
            if (west >= board.getColumns() || west < 0) {
                west = hexagon.getColumn();
            }

            int east = hexagon.getColumn() + move;
            if (east >= board.getColumns()) {
                east = hexagon.getColumn();
            }

            if (east % 2 != 0) {
                northEastMoveOffset++;
            } else {
                southEastMoveOffset++;
            }

            if (west % 2 != 0) {
                northWestMoveOffset++;
            } else {
                southWestMoveOffset++;
            }

            int northEastOffset = hexagon.getRow() - northEastMoveOffset;
            if (northEastOffset >= board.getRows() || northEastOffset < 0) {
                northEastOffset = hexagon.getRow();
            }

            int northWestOffset = hexagon.getRow() - northWestMoveOffset;
            if (northWestOffset >= board.getRows() || northWestOffset < 0) {
                northWestOffset = hexagon.getRow();
            }

            int southEastOffset = hexagon.getRow() + southEastMoveOffset;
            if (southEastOffset >= board.getRows()) {
                southEastOffset = hexagon.getRow();
            }

            int southWestOffset = hexagon.getRow() + southWestMoveOffset;
            if (southWestOffset >= board.getRows()) {
                southWestOffset = hexagon.getRow();
            }

            hexagonPolygons[hexagon.getColumn()][north].setFill(Paint.valueOf("green"));
            hexagonPolygons[hexagon.getColumn()][south].setFill(Paint.valueOf("green"));
            hexagonPolygons[east][northEastOffset].setFill(Paint.valueOf("green"));
            hexagonPolygons[west][northWestOffset].setFill(Paint.valueOf("green"));
            hexagonPolygons[east][southEastOffset].setFill(Paint.valueOf("red"));
            hexagonPolygons[west][southWestOffset].setFill(Paint.valueOf("red"));
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
