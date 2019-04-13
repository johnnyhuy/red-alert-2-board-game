package oosd.views;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import oosd.controllers.GameController;
import oosd.factories.ViewComponentFactory;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.views.components.HexagonViewComponent;

/**
 * SOLID:  Single Responsibility Principle
 * The view should only be responsible for managing the user interface (e.g. interacting with the JavaFX library)
 */
public class BoardView extends View {
    private final Board board;
    private final Pane boardPane;
    private final GameController controller;
    private final HexagonViewComponent selectionHexagons;
    private final HexagonViewComponent backgroundHexagons;
    private final HexagonViewComponent unitHexagons;
    private final ViewComponentFactory boardFactory;
    private GameEngine gameEngine;
    private Pane sidebar;
    private Text playerTurn;

    public BoardView(GameController controller, GameEngine gameEngine, Pane boardPane, Pane sidebar) {
        this.controller = controller;
        this.gameEngine = gameEngine;
        this.boardPane = boardPane;
        this.board = gameEngine.getBoard();
        this.sidebar = sidebar;
        this.boardFactory = new ViewComponentFactory(board.getColumns(), board.getRows());
        this.backgroundHexagons = boardFactory.createHexagons();
        this.unitHexagons = boardFactory.createHexagons();
        this.selectionHexagons = boardFactory.createHexagons();
        this.playerTurn = (Text) sidebar.lookup("#playerTurn");
    }

    public void moveUnit(Hexagon selectedHexagon, Hexagon clickedHexagon) {
        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                selectionHexagons.getHexagon(xIndex, yIndex).setVisible(false);
            }
        }

        selectionHexagons.getHexagon(selectedHexagon).setOpacity(1.0);
        selectionHexagons.getHexagon(selectedHexagon).setVisible(false);
        unitHexagons.getHexagon(selectedHexagon).setVisible(false);
        unitHexagons.getHexagon(clickedHexagon).setVisible(true);
        unitHexagons.getHexagon(clickedHexagon).setFill(boardFactory.createViewImage(clickedHexagon.getUnit().getImage()));
        playerTurn.setText("Player turn: " + gameEngine.getTurn().getPlayerName());
    }

    public void selectUnit(Hexagon selectedHexagon, Hexagon clickedHexagon) {
        if (selectedHexagon != null) {
            selectionHexagons.getHexagon(selectedHexagon).setVisible(false);

            for (Hexagon hexagon : selectedHexagon.getUnit().getUnitBehaviour().getValidMoves(gameEngine, selectedHexagon)) {
                selectionHexagons.getHexagon(hexagon).setVisible(false);
            }
        }

        for (Hexagon hexagon : clickedHexagon.getUnit().getUnitBehaviour().getValidMoves(gameEngine, clickedHexagon)) {
            selectionHexagons.getHexagon(hexagon).setVisible(true);
            selectionHexagons.getHexagon(hexagon).setFill(Paint.valueOf("#00C400"));
        }

        selectionHexagons.getHexagon(clickedHexagon).setFill(Paint.valueOf("#dadada"));
        selectionHexagons.getHexagon(clickedHexagon).setOpacity(0.5);
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
                    unitHexagons.getHexagon(xIndex, yIndex).setFill(boardFactory.createViewImage(hexagon.getUnit().getImage()));
                } else {
                    unitHexagons.getHexagon(xIndex, yIndex).setVisible(false);
                }

                backgroundHexagons.getHexagon(xIndex, yIndex).setFill(boardFactory.createViewImage("grass"));
                backgroundHexagons.getHexagon(xIndex, yIndex).setStrokeWidth(2);
                backgroundHexagons.getHexagon(xIndex, yIndex).setStroke(Paint.valueOf("#706c1c"));

                selectionHexagons.getHexagon(xIndex, yIndex).setVisible(false);

                final StackPane stack = new StackPane();
                stack.setOnMouseClicked(event -> handleHexagonClick(event, hexagon));
                stack.getChildren().addAll(backgroundHexagons.getHexagon(xIndex, yIndex), unitHexagons.getHexagon(xIndex, yIndex), selectionHexagons.getHexagon(xIndex, yIndex));
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

    private void handleHexagonClick(MouseEvent event, Hexagon hexagon) {
        Hexagon selectedHexagon = gameEngine.getSelectedHexagon();

        if (hexagon.getUnit() != null) {
            if (!hexagon.getUnit().getPlayer().equals(gameEngine.getTurn())) {
                return;
            }

            controller.selectUnit(event, selectedHexagon, hexagon);

            return;
        }

        if (selectedHexagon != null) {
            if (!selectedHexagon.getUnit().getUnitBehaviour().isValidMove(gameEngine, hexagon)) {
                return;
            }

            controller.moveUnit(event, selectedHexagon, hexagon);
        }
    }
}
