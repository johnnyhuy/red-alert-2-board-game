package oosd.views.components.panes;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import oosd.controllers.GameController;
import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.views.components.images.DefendPieceImage;
import oosd.views.components.polygons.BackgroundPiecePolygon;
import oosd.views.components.polygons.Hexagon;
import oosd.views.components.polygons.SelectionPiecePolygon;
import oosd.views.components.polygons.UnitPiecePolygon;
import oosd.views.handlers.SelectionPieceClickHandler;
import oosd.views.handlers.SelectionPieceDragReleasedHandler;
import oosd.views.handlers.UnitPieceClickHandler;
import oosd.views.handlers.UnitPieceDragDetectedHandler;

import java.util.HashMap;

public class BoardPane extends StackPane {
    public void initialise(Engine engine, GameController gameController, SidebarPane sidebar, HashMap<Piece, UnitPiecePolygon> unitPieces, HashMap<Piece, SelectionPiecePolygon> selectionPieces, HashMap<Piece, DefendPieceImage> defendPieces, HashMap<Piece, BackgroundPiecePolygon> backgroundPieces) {
        Board board = engine.getBoard();
        double x = 0;
        double y = 0;
        int pieceCount = 0;

        Group group = new Group();

        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {
                Piece piece = board.getPiece(column, row);
                UnitPiecePolygon unitPiecePolygon = unitPieces.get(piece);
                BackgroundPiecePolygon backgroundPiecePolygon = backgroundPieces.get(piece);
                SelectionPiecePolygon selectionPiecePolygon = selectionPieces.get(piece);
                DefendPieceImage defendPieceImage = defendPieces.get(piece);

                if (piece.getUnit() != null) {
                    unitPiecePolygon.setUnitImage(piece.getUnit());
                } else {
                    unitPiecePolygon.hide();
                }

                selectionPiecePolygon.setVisible(false);

                final AnchorPane anchor = new AnchorPane();
                anchor.getChildren().addAll(backgroundPiecePolygon, unitPiecePolygon, selectionPiecePolygon, defendPieceImage);
                anchor.setLayoutX(x);
                anchor.setLayoutY(y);

                selectionPiecePolygon.setOnMouseClicked(new SelectionPieceClickHandler(engine, gameController, piece));
                selectionPiecePolygon.setOnMouseDragReleased(new SelectionPieceDragReleasedHandler(engine, gameController, piece, sidebar));
                unitPiecePolygon.setOnMouseClicked(new UnitPieceClickHandler(engine, gameController, piece, sidebar));
                unitPiecePolygon.setOnDragDetected(new UnitPieceDragDetectedHandler(engine, gameController, piece, unitPiecePolygon));

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
