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
    private GameEngine gameEngine;
    private final AnchorPane boardPane;
    private final Polygon[][] hexagonPolygons;
    private final Text[][] unitText;
    private final Circle[][] unitCircles;
    private final BoardFactory boardFactory;

    public BoardView(GameController controller, GameEngine gameEngine, AnchorPane boardPane) {
        this.controller = controller;
        this.gameEngine = gameEngine;
        this.boardPane = boardPane;
        this.board = gameEngine.getBoard();

        this.boardFactory = new BoardFactory(board.getColumns(), board.getRows());
        this.hexagonPolygons = boardFactory.createHexagon();
        this.unitText = boardFactory.createUnitText();
        this.unitCircles = boardFactory.createUnitCircle();
    }

    public void selectUnit(Hexagon previousHexagon, Hexagon selectedHexagon) {
        if (previousHexagon != null) {
            hexagonPolygons[previousHexagon.getColumn()][previousHexagon.getRow()].setFill(Paint.valueOf("#ffffff"));

            for (Hexagon hexagon : gameEngine.getValidMoves(previousHexagon)) {
                hexagonPolygons[hexagon.getColumn()][hexagon.getRow()].setFill(Paint.valueOf("#ffffff"));
            }
        }

        for (Hexagon hexagon : gameEngine.getValidMoves(selectedHexagon)) {
            hexagonPolygons[hexagon.getColumn()][hexagon.getRow()].setFill(Paint.valueOf("green"));
        }

        hexagonPolygons[selectedHexagon.getColumn()][selectedHexagon.getRow()].setFill(Paint.valueOf("#dadada"));
    }

    public void initialize() {
        int xOffset = 80;
        int yOffset = 80;
        double x = 0;
        double y = 0;
        int hexagonCount = 0;
        int rows = board.getRows();
        int columns = board.getColumns();

        for (int yIndex = 0; yIndex < rows; yIndex++) {
            for (int xIndex = 0; xIndex < columns; xIndex++) {
                Hexagon hexagon = board.getHexagon(new Hexagon(xIndex, yIndex));

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
