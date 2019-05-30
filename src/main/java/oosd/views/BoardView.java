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
import oosd.views.components.polygons.BackgroundPiecePolygon;
import oosd.views.components.polygons.Hexagon;
import oosd.views.components.polygons.SelectionPiecePolygon;
import oosd.views.components.polygons.UnitPiecePolygon;
import oosd.views.factories.ViewComponentFactory;
import oosd.views.handlers.SelectionPieceClickHandler;
import oosd.views.handlers.SelectionPieceDragReleasedHandler;
import oosd.views.handlers.UnitPieceClickHandler;
import oosd.views.handlers.UnitPieceDragDetectedHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashMap;

import static oosd.helpers.ObjectHelper.exists;

/**
 * SOLID:  Single Responsibility Principle
 * The view should only be responsible for managing the user interface (e.g. interacting with the JavaFX library)
 */
@Component
public class BoardView implements View {
    @Inject
    private ApplicationContext context;

    private final GamePresenter gamePresenter;
    private final Engine engine;
    private ToolbarPresenter toolbarPresenter;
    private SidebarPresenter sidebarPresenter;
    private GameController gameController;
    private HashMap<Piece, SelectionPiecePolygon> selectionPieces;
    private HashMap<Piece, UnitPiecePolygon> unitPieces;
    private HashMap<Piece, DefendPieceImage> defendPieces;

    @Inject
    public BoardView(Engine engine, GamePresenter gamePresenter, ToolbarPresenter toolbarPresenter, SidebarPresenter sidebarPresenter) {
        this.gamePresenter = gamePresenter;
        this.engine = engine;
        this.toolbarPresenter = toolbarPresenter;
        this.sidebarPresenter = sidebarPresenter;
    }

    public void start() {
        this.gameController = gamePresenter.getGameController();
        BoardPane boardPane = gamePresenter.getBoardPane();
        ViewComponentFactory boardFactory = context.getBean(ViewComponentFactory.class);
        this.unitPieces = boardFactory.createUnitPiecePolygons();
        this.selectionPieces = boardFactory.createSelectionPiecePolygons();
        this.defendPieces = boardFactory.createDefendPieceImage();
        HashMap<Piece, BackgroundPiecePolygon> backgroundPieces = boardFactory.createBackgroundPiecePolygons();

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
            selectionPieces.get(selectedPiece).hide();

            Unit unit = selectedPiece.getUnit();
            if (exists(unit)) {
                for (Piece piece : unit.getUnitBehaviour().getValidMoves(engine, selectedPiece)) {
                    selectionPieces.get(piece).hide();
                }
            }
        }

        for (Piece piece : clickedPiece.getUnit().getUnitBehaviour().getValidMoves(engine, clickedPiece)) {
            selectionPieces.get(piece).show();

            Unit unit = piece.getUnit();
            if (exists(unit) && !unit.getPlayer().equals(engine.getTurn())) {
                selectionPieces.get(piece).fillRed();
            } else {
                selectionPieces.get(piece).fillGreen();
            }
        }

        sidebarPresenter.update();
    }

    public void updateBoard() {
        Board board = engine.getBoard();
        board.apply((column, row) -> {
            Piece piece = board.getPiece(column, row);
            UnitPiecePolygon unitPiecePolygon = unitPieces.get(piece);
            SelectionPiecePolygon selectionPiecePolygon = selectionPieces.get(piece);
            Unit unit = piece.getUnit();

            selectionPiecePolygon.setOnMouseClicked(new SelectionPieceClickHandler(engine, gameController, piece));
            selectionPiecePolygon.setOnMouseDragReleased(new SelectionPieceDragReleasedHandler(engine, gameController, piece));
            unitPiecePolygon.setOnMouseClicked(new UnitPieceClickHandler(engine, gameController, piece));
            unitPiecePolygon.setOnDragDetected(new UnitPieceDragDetectedHandler(engine, gameController, piece, unitPiecePolygon));

            if (exists(unit)) {
                unitPiecePolygon.setUnitImage(unit);

                if (unit.canDefend(piece)) {
                    defendPieces.get(piece).show();
                } else {
                    defendPieces.get(piece).hide();
                }
            } else {
                unitPiecePolygon.hide();
            }

            selectionPieces.get(piece).hide();
        });

        sidebarPresenter.update();
    }
}
