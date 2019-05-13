package oosd.views.components;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.views.handlers.PieceClickHandler;

import java.util.HashMap;

public class BoardPane extends StackPane {
    public void createBoard(GameEngine gameEngine, GameController gameController, HashMap<Piece, UnitPiecePolygon> unitPieces, HashMap<Piece, SelectionPiecePolygon> selectionPieces, HashMap<Piece, DefendPieceImage> defendPieces, HashMap<Piece, BackgroundPiecePolygon> backgroundPieces) {
        Board board = gameEngine.getBoard();
        double x = 0;
        double y = 0;
        int pieceCount = 0;

        Group group = new Group();

        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {
                Piece piece = board.getPiece(column, row);

                if (piece.getUnit() != null) {
                    unitPieces.get(piece).setUnitImage(piece.getUnit());
                } else {
                    unitPieces.get(piece).resetUnitImage();
                }

                selectionPieces.get(piece).setVisible(false);

                final AnchorPane anchor = new AnchorPane();
                anchor.getChildren().addAll(backgroundPieces.get(piece), unitPieces.get(piece), selectionPieces.get(piece), defendPieces.get(piece));
                anchor.setLayoutX(x);
                anchor.setLayoutY(y);

                PieceClickHandler mouseClickHandler = new PieceClickHandler(gameEngine, gameController, piece);
                backgroundPieces.get(piece).setOnMouseClicked(mouseClickHandler);
                unitPieces.get(piece).setOnMouseClicked(mouseClickHandler);
                selectionPieces.get(piece).setOnMouseClicked(mouseClickHandler);
                defendPieces.get(piece).setOnMouseClicked(mouseClickHandler);

//                PieceDragEnteredHandler mouseDragEnteredHandler = new PieceDragEnteredHandler(gameEngine, gameController, piece);
//                backgroundPieces.get(piece).setOnMouseDragEntered(mouseDragEnteredHandler);
//                unitPieces.get(piece).setOnMouseDragEntered(mouseDragEnteredHandler);
//                selectionPieces.get(piece).setOnMouseDragEntered(mouseDragEnteredHandler);
//                defendPieces.get(piece).setOnMouseDragEntered(mouseDragEnteredHandler);

//                PieceDraggedHandler mouseDraggedHandler = new PieceDraggedHandler(gameEngine, gameController, piece);
//                unitPieces.get(piece).setOnMouseDragged(mouseDraggedHandler);

//                PieceDragExitedHandler mouseDragExitedHandler = new PieceDragExitedHandler(gameEngine, gameController, piece);
//                backgroundPieces.get(piece).setOnMouseDragExited(mouseDragExitedHandler);
//                unitPieces.get(piece).setOnMouseDragExited(mouseDragExitedHandler);

//                unitPieces.get(piece).setOnDragDetected(event -> {
//                    gameController.selectUnit(event, gameEngine.getSelectedPiece(), piece);
//                    event.consume();
//                });
                unitPieces.get(piece).setOnMousePressed(event -> {
                    unitPieces.get(piece).setMouseTransparent(true);
                    gameController.selectUnit(event, gameEngine.getSelectedPiece(), piece);
                    event.setDragDetect(true);
                });
                unitPieces.get(piece).setOnMouseDragged(event -> {
                    event.setDragDetect(false);
                    System.out.println("dragged");
                });
                unitPieces.get(piece).setOnDragDetected(event -> {
                    unitPieces.get(piece).startFullDrag();
                });
//                backgroundPieces.get(piece).setOnMouseDragReleased(event -> {
//                    gameController.moveUnit(event, gameEngine.getSelectedPiece(), piece);
//                });
                backgroundPieces.get(piece).setOnMouseDragExited(event -> System.out.println("Event on Target: mouse drag exited"));
//                backgroundPieces.get(piece).setOnMouseDragOver(event -> System.out.println("Event on Target: mouse drag over"));
                group.getChildren().add(anchor);

                if (pieceCount % 2 == 0) {
                    y = y + Hexagon.HALF_INCREMENT;
                } else {
                    y = y - Hexagon.HALF_INCREMENT;
                }

                pieceCount++;

                x = column == board.getColumns() - 1 ? 0 : x + Hexagon.GAP;
            }

            y = row == board.getRows() - 1 ? 0 : y + Hexagon.FULL_INCREMENT;
        }

        getChildren().add(group);
    }
}
