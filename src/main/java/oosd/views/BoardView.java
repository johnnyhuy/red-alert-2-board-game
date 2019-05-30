package oosd.views;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import oosd.controllers.GameController;
import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.models.units.Unit;
import oosd.views.components.images.DefendPieceImage;
import oosd.views.components.panes.BoardPane;
import oosd.views.components.polygons.*;
import oosd.views.handlers.SelectionPieceClickHandler;
import oosd.views.handlers.SelectionPieceDragReleasedHandler;
import oosd.views.handlers.UnitPieceClickHandler;
import oosd.views.handlers.UnitPieceDragDetectedHandler;
import oosd.views.presenters.GamePresenter;
import oosd.views.presenters.SidebarPresenter;
import oosd.views.presenters.ToolbarPresenter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static oosd.helpers.ObjectHelper.exists;

/**
 * SOLID:  Single Responsibility Principle
 * The view should only be responsible for managing the user interface (e.g. interacting with the JavaFX library)
 */
@Component
public class BoardView implements View {
    private final GamePresenter gamePresenter;
    private final Engine engine;
    private BoardPolygonMap boardPolygonMap;
    private ToolbarPresenter toolbarPresenter;
    private SidebarPresenter sidebarPresenter;
    private GameController gameController;

    @Inject
    private ApplicationContext context;

    @Inject
    public BoardView(Engine engine, GamePresenter gamePresenter, ToolbarPresenter toolbarPresenter, SidebarPresenter sidebarPresenter) {
        this.gamePresenter = gamePresenter;
        this.engine = engine;
        this.toolbarPresenter = toolbarPresenter;
        this.sidebarPresenter = sidebarPresenter;
    }

    public void start() {
        this.boardPolygonMap = context.getBean(BoardPolygonMap.class);
        this.gameController = context.getBean(GameController.class);
        BoardPane boardPane = gamePresenter.getBoardPane();

        Board board = engine.getBoard();
        double x = 0;
        double y = 0;
        int pieceCount = 0;

        Group group = new Group();

        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {
                Piece piece = board.getPiece(column, row);
                UnitPiecePolygon unitPiecePolygon = boardPolygonMap.getUnitPiece(piece);
                BackgroundPiecePolygon backgroundPiecePolygon = boardPolygonMap.getBackgroundPiece(piece);
                SelectionPiecePolygon selectionPiecePolygon = boardPolygonMap.getSelectionPiece(piece);
                DefendPieceImage defendPieceImage = boardPolygonMap.getDefendPiece(piece);

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
                selectionPiecePolygon.setOnMouseDragReleased(new SelectionPieceDragReleasedHandler(engine, gameController, piece));
                unitPiecePolygon.setOnMouseClicked(new UnitPieceClickHandler(engine, gameController, piece));
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

        boardPane.getChildren().add(group);
    }

    public void selectUnit(Piece selectedPiece, Piece clickedPiece) {
        if (exists(selectedPiece)) {
            boardPolygonMap.getSelectionPiece(selectedPiece).hide();

            Unit unit = selectedPiece.getUnit();
            if (exists(unit)) {
                for (Piece piece : unit.getUnitBehaviour().getValidMoves(engine, selectedPiece)) {
                    boardPolygonMap.getSelectionPiece(piece).hide();
                }
            }
        }

        for (Piece piece : clickedPiece.getUnit().getUnitBehaviour().getValidMoves(engine, clickedPiece)) {
            boardPolygonMap.getSelectionPiece(piece).show();

            Unit unit = piece.getUnit();
            if (exists(unit) && !unit.getPlayer().equals(engine.getTurn())) {
                boardPolygonMap.getSelectionPiece(piece).fillRed();
            } else {
                boardPolygonMap.getSelectionPiece(piece).fillGreen();
            }
        }

        sidebarPresenter.update();
    }

    public void updateBoard() {
        Board board = engine.getBoard();

        board.apply((column, row) -> {
            Piece piece = board.getPiece(column, row);
            UnitPiecePolygon unitPiecePolygon = boardPolygonMap.getUnitPiece(piece);
            SelectionPiecePolygon selectionPiecePolygon = boardPolygonMap.getSelectionPiece(piece);
            Unit unit = piece.getUnit();

            selectionPiecePolygon.setOnMouseClicked(new SelectionPieceClickHandler(engine, gameController, piece));
            selectionPiecePolygon.setOnMouseDragReleased(new SelectionPieceDragReleasedHandler(engine, gameController, piece));
            unitPiecePolygon.setOnMouseClicked(new UnitPieceClickHandler(engine, gameController, piece));
            unitPiecePolygon.setOnDragDetected(new UnitPieceDragDetectedHandler(engine, gameController, piece, unitPiecePolygon));

            if (exists(unit)) {
                unitPiecePolygon.setUnitImage(unit);

                if (unit.canDefend(piece)) {
                    boardPolygonMap.getDefendPiece(piece).show();
                } else {
                    boardPolygonMap.getDefendPiece(piece).hide();
                }
            } else {
                unitPiecePolygon.hide();
            }

            boardPolygonMap.getSelectionPiece(piece).hide();
        });

        sidebarPresenter.update();
    }
}
