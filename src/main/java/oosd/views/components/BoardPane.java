package oosd.views.components;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.views.handlers.*;

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

                final UnitPieceClickHandler mouseClickHandler = new UnitPieceClickHandler(gameEngine, gameController, piece);
                backgroundPieces.get(piece).setOnMouseClicked(mouseClickHandler);
                unitPieces.get(piece).setOnMouseClicked(mouseClickHandler);
                selectionPieces.get(piece).setOnMouseClicked(new SelectionPieceClickHandler(gameEngine, gameController, piece));
                defendPieces.get(piece).setOnMouseClicked(mouseClickHandler);
                unitPieces.get(piece).setOnMousePressed(new UnitPiecePressedHandler(gameEngine, gameController, piece));
                unitPieces.get(piece).setOnDragDetected(new UnitPieceDragDetectedHandler(unitPieces.get(piece)));
                selectionPieces.get(piece).setOnMouseDragReleased(new SelectionPieceDragReleasedHandler(gameEngine, gameController, piece));
                unitPieces.get(piece).setOnMouseDragReleased(new UnitPieceDragReleasedHandler(gameEngine, gameController, piece));

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
