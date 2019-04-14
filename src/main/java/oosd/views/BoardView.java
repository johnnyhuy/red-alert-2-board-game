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
    }

    public void moveUnit(Piece selectedPiece, Piece clickedPiece) {
        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                selectionPieces.getPiece(xIndex, yIndex).setVisible(false);
            }
        }

        selectionPieces.getPiece(selectedPiece).setOpacity(1.0);
        selectionPieces.getPiece(selectedPiece).setVisible(false);
        unitPieces.getPiece(selectedPiece).setVisible(false);
        unitPieces.getPiece(clickedPiece).setVisible(true);
        unitPieces.getPiece(clickedPiece).setFill(boardFactory.createImage(clickedPiece.getUnit().getImage()));
        playerTurnText.setText("Player turn: " + gameEngine.getTurn().getPlayerName());
    }

    public void selectUnit(Unit selectedUnit, Piece clickedPiece) {
        if (selectedUnit != null) {
            selectionPieces.getPiece(selectedUnit.getLocation()).setVisible(false);

            for (Piece piece : selectedUnit.getUnitBehaviour().getValidMoves(gameEngine, selectedUnit.getLocation())) {
                selectionPieces.getPiece(piece).setVisible(false);
            }
        }

        for (Piece piece : clickedPiece.getUnit().getUnitBehaviour().getValidMoves(gameEngine, clickedPiece)) {
            selectionPieces.getPiece(piece).setVisible(true);
            selectionPieces.getPiece(piece).setFill(Paint.valueOf("#00C400"));
        }

        selectionPieces.getPiece(clickedPiece).setFill(Paint.valueOf("#dadada"));
        selectionPieces.getPiece(clickedPiece).setOpacity(0.5);
    }

    public void initialize() {
        double x = 0;
        double y = 0;
        int pieceCount = 0;
        Player playerTurn = gameEngine.getTurn();

        playerTurnText.setText("Player turn: " + gameEngine.getTurn().getPlayerName());

        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                Unit unit = playerTurn.getUnit(xIndex, yIndex);
                Piece piece = unit.getLocation();

                unitPieces.getPiece(xIndex, yIndex).setFill(boardFactory.createImage(unit.getImage()));

                backgroundPieces.getPiece(xIndex, yIndex).setFill(boardFactory.createImage("grass"));
                backgroundPieces.getPiece(xIndex, yIndex).setStrokeWidth(2);
                backgroundPieces.getPiece(xIndex, yIndex).setStroke(Paint.valueOf("#706c1c"));

                selectionPieces.getPiece(xIndex, yIndex).setVisible(false);

                final StackPane stack = new StackPane();
                stack.setOnMouseClicked(event -> handlePieceClick(event, unit, piece));
                stack.getChildren().addAll(backgroundPieces.getPiece(xIndex, yIndex), unitPieces.getPiece(xIndex, yIndex), selectionPieces.getPiece(xIndex, yIndex));
                stack.setLayoutX(x);
                stack.setLayoutY(y);

                boardPane.getChildren().add(stack);

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
    }

    private void handlePieceClick(MouseEvent event, Unit unit, Piece piece) {
        Unit selectedUnit = gameEngine.getSelectedUnit();

        if (unit != null) {
            if (!unit.getPlayer().equals(gameEngine.getTurn())) {
                return;
            }

            controller.selectUnit(event, selectedUnit, piece);

            return;
        }

        if (selectedUnit != null) {
            if (!selectedUnit.getUnit().getUnitBehaviour().isValidMove(gameEngine, piece)) {
                return;
            }

            controller.moveUnit(event, selectedUnit, piece);
        }
    }
}
