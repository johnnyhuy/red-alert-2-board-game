package oosd.views;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import oosd.controllers.GameController;
import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.views.components.images.DefendPieceImage;
import oosd.views.components.images.ToolbarIcon;
import oosd.views.components.panes.BoardPane;
import oosd.views.components.panes.SidebarPane;
import oosd.views.components.panes.ToolbarPane;
import oosd.views.components.polygons.BackgroundPiecePolygon;
import oosd.views.components.polygons.Hexagon;
import oosd.views.components.polygons.SelectionPiecePolygon;
import oosd.views.components.polygons.UnitPiecePolygon;
import oosd.views.factories.ViewComponentFactory;
import oosd.views.handlers.*;
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
    private final GamePresenter gamePresenter;

    private final Engine engine;
    @Inject
    private ApplicationContext context;
    private BoardPane boardPane;
    private GameController gameController;
    private HashMap<Piece, SelectionPiecePolygon> selectionPieces;
    private HashMap<Piece, UnitPiecePolygon> unitPieces;
    private HashMap<Piece, DefendPieceImage> defendPieces;
    private HashMap<Piece, BackgroundPiecePolygon> backgroundPieces;
    private ViewComponentFactory boardFactory;
    private SidebarPane sidebar;
    private Text playerTurn;
    private ToolbarPane toolbar;

    @Inject
    public BoardView(Engine engine, GamePresenter gamePresenter) {
        this.gamePresenter = gamePresenter;
        this.engine = engine;
    }

    public void start() {
        this.gameController = gamePresenter.getGameController();
        this.sidebar = gamePresenter.getSidebar();
        this.playerTurn = sidebar.getPlayerTurnText();
        this.boardPane = gamePresenter.getBoardPane();
        this.toolbar = gamePresenter.getToolbarPane();
        this.boardFactory = context.getBean(ViewComponentFactory.class);
        this.unitPieces = boardFactory.createUnitPiecePolygons();
        this.selectionPieces = boardFactory.createSelectionPiecePolygons();
        this.defendPieces = boardFactory.createDefendPieceImage();
        this.backgroundPieces = boardFactory.createBackgroundPiecePolygons();

        Player player = engine.getTurn();
        Text turnCount = gamePresenter.getTurnCount();

        if (exists(player)) {
            playerTurn.setText("Player turn: " + player.getPlayerName());
        }

        turnCount.setText("Remaining turns: " + engine.getRemainingTurns());

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

        boardPane.getChildren().add(group);

        Button undoButton = gamePresenter.getUndoButton();
        undoButton.setOnMouseClicked(new UndoClickHandler(engine, gameController, sidebar));
        undoButton.setGraphic(new ToolbarIcon("undo"));
        undoButton.setOnMousePressed(event -> undoButton.setGraphic(new ToolbarIcon("undo_active")));
        undoButton.setOnMouseEntered(event -> undoButton.setGraphic(new ToolbarIcon("undo_hover")));
        undoButton.setOnMouseExited(event -> undoButton.setGraphic(new ToolbarIcon("undo")));

        Button defendButton = gamePresenter.getDefendButton();
        defendButton.setOnMouseClicked(new DefendClickHandler(engine, gameController, sidebar));
        defendButton.setGraphic(new ToolbarIcon("shield"));
        defendButton.setOnMousePressed(event -> defendButton.setGraphic(new ToolbarIcon("shield_active")));
        defendButton.setOnMouseEntered(event -> defendButton.setGraphic(new ToolbarIcon("shield_hover")));
        defendButton.setOnMouseExited(event -> defendButton.setGraphic(new ToolbarIcon("shield")));

        Button forfeitButton = gamePresenter.getForfeitButton();
        forfeitButton.setOnMouseClicked(new ForfeitClickHandler(engine, gameController));
        forfeitButton.setGraphic(new ToolbarIcon("skull"));
        forfeitButton.setOnMousePressed(event -> forfeitButton.setGraphic(new ToolbarIcon("skull_active")));
        forfeitButton.setOnMouseEntered(event -> forfeitButton.setGraphic(new ToolbarIcon("skull_hover")));
        forfeitButton.setOnMouseExited(event -> forfeitButton.setGraphic(new ToolbarIcon("skull")));

        Button saveGameButton = gamePresenter.getSaveGameButton();
        saveGameButton.setOnMouseClicked(new SaveGameClickHandler(engine, gameController));
        saveGameButton.setGraphic(new ToolbarIcon("save"));
        saveGameButton.setOnMousePressed(event -> saveGameButton.setGraphic(new ToolbarIcon("save_active")));
        saveGameButton.setOnMouseEntered(event -> saveGameButton.setGraphic(new ToolbarIcon("save_hover")));
        saveGameButton.setOnMouseExited(event -> saveGameButton.setGraphic(new ToolbarIcon("save")));

        Button restoreGameButton = gamePresenter.getRestoreGameButton();
        restoreGameButton.setOnMouseClicked(new RestoreGameClickHandler(engine, gameController));
        restoreGameButton.setGraphic(new ToolbarIcon("restore"));
        restoreGameButton.setOnMousePressed(event -> restoreGameButton.setGraphic(new ToolbarIcon("restore_active")));
        restoreGameButton.setOnMouseEntered(event -> restoreGameButton.setGraphic(new ToolbarIcon("restore_hover")));
        restoreGameButton.setOnMouseExited(event -> restoreGameButton.setGraphic(new ToolbarIcon("restore")));
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
                selectionPieces.get(piece).setFill(Paint.valueOf("#FF0000"));
            } else {
                selectionPieces.get(piece).setFill(Paint.valueOf("#00C400"));
            }
        }

        selectionPieces.get(clickedPiece).setFill(Paint.valueOf("#dadada"));
    }

    public void updateBoard() {
        Board board = engine.getBoard();
        board.apply((column, row) -> {
            Piece piece = board.getPiece(column, row);
            UnitPiecePolygon unitPiecePolygon = unitPieces.get(piece);
            SelectionPiecePolygon selectionPiecePolygon = selectionPieces.get(piece);
            Unit unit = piece.getUnit();

            selectionPiecePolygon.setOnMouseClicked(new SelectionPieceClickHandler(engine, gameController, piece));
            selectionPiecePolygon.setOnMouseDragReleased(new SelectionPieceDragReleasedHandler(engine, gameController, piece, sidebar));
            unitPiecePolygon.setOnMouseClicked(new UnitPieceClickHandler(engine, gameController, piece, sidebar));
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
    }
}
