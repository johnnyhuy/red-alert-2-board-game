package oosd.views.components;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.views.handler.PieceHandler;

import java.util.HashMap;

public class BoardPane extends StackPane {
    public void createBoard(GameEngine gameEngine, GameController gameController, HashMap<Piece, UnitPiecePolygon> unitPieces, HashMap<Piece, SelectionPiecePolygon> selectionPieces, HashMap<Piece, DefendPieceImage> defendPieces, HashMap<Piece, BackgroundPiecePolygon> backgroundPieces) {
        Board board = gameEngine.getBoard();
        double x = 0;
        double y = 0;
        int pieceCount = 0;

        Group group = new Group();

        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                Piece piece = board.getPiece(xIndex, yIndex);

                if (piece.getUnit() != null) {
                    unitPieces.get(piece).setUnitImage(piece.getUnit());
                } else {
                    unitPieces.get(piece).resetUnitImage();
                }

                selectionPieces.get(piece).setVisible(false);

                final AnchorPane anchor = new AnchorPane();
                anchor.setOnMouseClicked(new PieceHandler(gameEngine, gameController, piece));
                anchor.getChildren().addAll(backgroundPieces.get(piece), unitPieces.get(piece), selectionPieces.get(piece), defendPieces.get(piece));
                anchor.setLayoutX(x);
                anchor.setLayoutY(y);

                group.getChildren().add(anchor);

                if (pieceCount % 2 == 0) {
                    y = y + Hexagon.HALF_INCREMENT;
                } else {
                    y = y - Hexagon.HALF_INCREMENT;
                }

                pieceCount++;

                x = xIndex == board.getColumns() - 1 ? 0 : x + Hexagon.GAP;
            }

            y = yIndex == board.getRows() - 1 ? 0 : y + Hexagon.FULL_INCREMENT;
        }

        getChildren().add(group);
    }
}
