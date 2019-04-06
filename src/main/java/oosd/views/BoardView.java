package oosd.views;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
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
    private final Pane boardPane;
    private final GameController controller;
    private final Polygon[][] selectionHexagons;
    private GameEngine gameEngine;
    private Pane sidebar;
    private final Polygon[][] backgroundHexagons;
    private final Polygon[][] unitHexagons;
    private final BoardFactory boardFactory;
    private Text playerTurn;

    public BoardView(GameController controller, GameEngine gameEngine, Pane boardPane, Pane sidebar) {
        this.controller = controller;
        this.gameEngine = gameEngine;
        this.boardPane = boardPane;
        this.board = gameEngine.getBoard();
        this.sidebar = sidebar;
        this.boardFactory = new BoardFactory(board.getColumns(), board.getRows());
        this.backgroundHexagons = boardFactory.createHexagons();
        this.unitHexagons = boardFactory.createHexagons();
        this.selectionHexagons = boardFactory.createHexagons();
        this.playerTurn = (Text) sidebar.lookup("#playerTurn");
    }

    public void moveUnit(Hexagon selectedHexagon, Hexagon clickedHexagon) {
        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                selectionHexagons[xIndex][yIndex].setVisible(false);
            }
        }

        selectionHexagons[selectedHexagon.getColumn()][selectedHexagon.getRow()].setOpacity(1.0);
        selectionHexagons[selectedHexagon.getColumn()][selectedHexagon.getRow()].setVisible(false);
        unitHexagons[selectedHexagon.getColumn()][selectedHexagon.getRow()].setVisible(false);
        unitHexagons[clickedHexagon.getColumn()][clickedHexagon.getRow()].setVisible(true);
        unitHexagons[clickedHexagon.getColumn()][clickedHexagon.getRow()].setFill(boardFactory.createViewImage(clickedHexagon.getUnit().getImage()));
        playerTurn.setText("Player turn: " + gameEngine.getTurn().getPlayerName());
    }

    public void selectUnit(Hexagon selectedHexagon, Hexagon clickedHexagon) {
        if (selectedHexagon != null) {
            selectionHexagons[selectedHexagon.getColumn()][selectedHexagon.getRow()].setVisible(false);

            for (Hexagon hexagon : selectedHexagon.getUnit().getUnitBehaviour().getValidMoves(gameEngine, selectedHexagon)) {
                selectionHexagons[hexagon.getColumn()][hexagon.getRow()].setVisible(false);
            }
        }

        for (Hexagon hexagon : clickedHexagon.getUnit().getUnitBehaviour().getValidMoves(gameEngine, clickedHexagon)) {
            selectionHexagons[hexagon.getColumn()][hexagon.getRow()].setVisible(true);
            selectionHexagons[hexagon.getColumn()][hexagon.getRow()].setFill(Paint.valueOf("#00C400"));
        }

        selectionHexagons[clickedHexagon.getColumn()][clickedHexagon.getRow()].setFill(Paint.valueOf("#dadada"));
        selectionHexagons[clickedHexagon.getColumn()][clickedHexagon.getRow()].setOpacity(0.5);
    }

    public void initialize() {
        double x = 0;
        double y = 0;
        int hexagonCount = 0;

        playerTurn.setText("Player turn: " + gameEngine.getTurn().getPlayerName());

        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                Hexagon hexagon = board.getHexagon(xIndex, yIndex);

                if (hexagon.getUnit() != null) {
                    unitHexagons[xIndex][yIndex].setFill(boardFactory.createViewImage(hexagon.getUnit().getImage()));
                } else {
                    unitHexagons[xIndex][yIndex].setVisible(false);
                }

                backgroundHexagons[xIndex][yIndex].setFill(boardFactory.createViewImage("grass"));
                backgroundHexagons[xIndex][yIndex].setStrokeWidth(2);
                backgroundHexagons[xIndex][yIndex].setStroke(Paint.valueOf("#706c1c"));

                selectionHexagons[xIndex][yIndex].setVisible(false);

                final StackPane stack = new StackPane();
                stack.setOnMouseClicked(event -> controller.board(event, hexagon));
                stack.getChildren().addAll(backgroundHexagons[xIndex][yIndex], unitHexagons[xIndex][yIndex], selectionHexagons[xIndex][yIndex]);
                stack.setLayoutX(x);
                stack.setLayoutY(y);

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
