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
import oosd.models.board.Piece;
import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.views.components.PieceViewComponent;

/**
 * SOLID:  Single Responsibility Principle
 * The view should only be responsible for managing the user interface (e.g. interacting with the JavaFX library)
 */
public class BoardView extends View {
    private final Board board;
    private final Pane boardPane;
    private final GameController controller;
    private final PieceViewComponent selectionPieces;
    private final PieceViewComponent backgroundPieces;
    private final PieceViewComponent unitPieces;
    private final ViewComponentFactory boardFactory;
    private GameEngine gameEngine;
    private Pane sidebar;
    private Text playerTurnText;
    private StackPane[][] stackPanes;

    public BoardView(GameController controller, GameEngine gameEngine, Pane boardPane, Pane sidebar) {
        this.controller = controller;
        this.gameEngine = gameEngine;
        this.boardPane = boardPane;
        this.board = gameEngine.getBoard();
        this.sidebar = sidebar;
        this.boardFactory = new ViewComponentFactory(board.getColumns(), board.getRows());
        this.backgroundPieces = boardFactory.createPieces();
        this.unitPieces = boardFactory.createPieces();
        this.selectionPieces = boardFactory.createPieces();
        this.playerTurnText = (Text) sidebar.lookup("#playerTurnText");
        this.stackPanes = new StackPane[board.getColumns()][board.getRows()];
    }

    public void moveUnit(Unit clickedUnit) {
        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                selectionPieces.getPiece(xIndex, yIndex).setVisible(false);
            }
        }

        unitPieces.getPiece(clickedUnit.getLocation()).setVisible(true);
        unitPieces.getPiece(clickedUnit.getLocation()).setFill(boardFactory.createImage(clickedUnit.getImage()));
        playerTurnText.setText("Player turn: " + gameEngine.getTurn().getPlayerName());
    }

    public void selectUnit(Unit clickedUnit) {
//        if (selectedUnit != null) {
//            selectionPieces.getPiece(selectedUnit.getLocation()).setVisible(false);
//
//            for (Piece piece : selectedUnit.getUnitBehaviour().getValidMoves(gameEngine, selectedUnit)) {
//                selectionPieces.getPiece(piece).setVisible(false);
//            }
//        }

        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                selectionPieces.getPiece(xIndex, yIndex).setVisible(false);
            }
        }

        for (Piece piece : clickedUnit.getUnitBehaviour().getValidMoves(gameEngine, clickedUnit)) {
            selectionPieces.getPiece(piece).setVisible(true);
            selectionPieces.getPiece(piece).setFill(Paint.valueOf("#00C400"));
        }

        selectionPieces.getPiece(clickedUnit.getLocation()).setFill(Paint.valueOf("#dadada"));
        selectionPieces.getPiece(clickedUnit.getLocation()).setOpacity(0.5);
    }

    public void initialize() {
        double x = 0;
        double y = 0;
        int pieceCount = 0;

//        playerTurnText.setText("Player turn: " + gameEngine.getTurn().getPlayerName());

        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                backgroundPieces.getPiece(xIndex, yIndex).setFill(boardFactory.createImage("grass"));
                backgroundPieces.getPiece(xIndex, yIndex).setStrokeWidth(2);
                backgroundPieces.getPiece(xIndex, yIndex).setStroke(Paint.valueOf("#706c1c"));

                selectionPieces.getPiece(xIndex, yIndex).setVisible(false);
                unitPieces.getPiece(xIndex, yIndex).setVisible(false);

                int finalXIndex = xIndex;
                int finalYIndex = yIndex;
                StackPane stackPane = new StackPane();
                stackPane.setOnMouseClicked(event -> handlePieceClick(event, new Piece(finalXIndex, finalYIndex)));
                stackPane.getChildren().addAll(backgroundPieces.getPiece(xIndex, yIndex), unitPieces.getPiece(xIndex, yIndex), selectionPieces.getPiece(xIndex, yIndex));
                stackPane.setLayoutX(x);
                stackPane.setLayoutY(y);

                boardPane.getChildren().add(stackPane);

                if (pieceCount % 2 == 0) {
                    y = y + boardFactory.getHalfIncrement();
                } else {
                    y = y - boardFactory.getHalfIncrement();
                }

                pieceCount++;

                x = xIndex == board.getColumns() - 1 ? 0 : x + boardFactory.getGap();
            }

            y = yIndex == board.getRows() - 1 ? 0 : y + boardFactory.getFullIncrement();
        }

        for (Player player : gameEngine.getPlayers()) {
            for (Unit unit : player.getUnits()) {
                unitPieces.getPiece(unit.getLocation()).setVisible(true);
                unitPieces.getPiece(unit.getLocation()).setFill(boardFactory.createImage(unit.getImage()));
            }
        }
    }

    private void handlePieceClick(MouseEvent event, Piece clickedPiece) {
        Unit selectedUnit = gameEngine.getSelectedUnit();
        Unit clickedUnit = gameEngine.getUnit(clickedPiece);

        if (clickedUnit != null) {
            controller.selectUnit(event, clickedUnit);
            return;
        }

        if (selectedUnit != null) {
            if (!selectedUnit.getUnitBehaviour().isValidMove(gameEngine, clickedPiece)) {
                return;
            }

            controller.moveUnit(event, selectedUnit, clickedPiece);
        }
    }
}
