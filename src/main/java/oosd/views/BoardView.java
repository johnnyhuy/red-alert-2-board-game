package oosd.views;

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
        this.unitCircles = boardFactory.createUnitCircles();
    }

    public void moveUnit(Hexagon selectedHexagon, Hexagon clickedHexagon) {
        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                hexagonPolygons[xIndex][yIndex].setFill(Paint.valueOf("#ffffff"));
            }
        }

        hexagonPolygons[selectedHexagon.getColumn()][selectedHexagon.getRow()].setFill(Paint.valueOf("#ffffff"));
        unitCircles[selectedHexagon.getColumn()][selectedHexagon.getRow()].setVisible(false);
        unitCircles[clickedHexagon.getColumn()][clickedHexagon.getRow()].setVisible(true);
        unitText[selectedHexagon.getColumn()][selectedHexagon.getRow()].setText("");
        unitText[clickedHexagon.getColumn()][clickedHexagon.getRow()].setText(clickedHexagon.getUnit().getName());
    }

    public void selectUnit(Hexagon selectedHexagon, Hexagon clickedHexagon) {
        if (selectedHexagon != null) {
            hexagonPolygons[selectedHexagon.getColumn()][selectedHexagon.getRow()].setFill(Paint.valueOf("#ffffff"));

            for (Hexagon hexagon : selectedHexagon.getUnit().getUnitBehaviour().getValidMoves(gameEngine, selectedHexagon)) {
                hexagonPolygons[hexagon.getColumn()][hexagon.getRow()].setFill(Paint.valueOf("#ffffff"));
            }
        }

        for (Hexagon hexagon : clickedHexagon.getUnit().getUnitBehaviour().getValidMoves(gameEngine, clickedHexagon)) {
            hexagonPolygons[hexagon.getColumn()][hexagon.getRow()].setFill(Paint.valueOf("green"));
        }

        hexagonPolygons[clickedHexagon.getColumn()][clickedHexagon.getRow()].setFill(Paint.valueOf("#dadada"));
    }

    public void initialize() {
        int xOffset = 80;
        int yOffset = 80;
        double x = 0;
        double y = 0;
        int hexagonCount = 0;

        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                Hexagon hexagon = board.getHexagon(new Hexagon(xIndex, yIndex));

                unitCircles[xIndex][yIndex].setVisible(hexagon.getUnit() != null);
                unitText[xIndex][yIndex].setText(hexagon.getUnit() != null ? hexagon.getUnit().getName() : "");

                final StackPane stack = new StackPane();
                stack.setOnMouseClicked(event -> controller.board(event, hexagon));
                stack.getChildren().addAll(hexagonPolygons[xIndex][yIndex], unitCircles[xIndex][yIndex], unitText[xIndex][yIndex]);
                stack.setLayoutX(xOffset + x);
                stack.setLayoutY(yOffset + y);

                boardPane.getChildren().add(stack);

                if (hexagonCount % 2 == 0) {
                    y = y + boardFactory.getHalfIncrement();
                } else {
                    y = y - boardFactory.getHalfIncrement();
                }

                hexagonCount++;

                x = xIndex == board.getColumns() - 1 ? 0 : x + boardFactory.getGap();
            }

            y = yIndex == board.getRows() - 1 ? 0 : y + boardFactory.getFullIncrement();
        }
    }
}
