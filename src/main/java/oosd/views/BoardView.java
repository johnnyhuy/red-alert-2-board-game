package oosd.views;

import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import oosd.controllers.GameController;
import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.models.units.Unit;
import oosd.views.components.images.DefendPieceImage;
import oosd.views.components.panes.BoardPane;
import oosd.views.components.panes.SidebarPane;
import oosd.views.components.panes.ToolbarPane;
import oosd.views.components.polygons.BackgroundPiecePolygon;
import oosd.views.components.polygons.SelectionPiecePolygon;
import oosd.views.components.polygons.UnitPiecePolygon;
import oosd.views.factories.ViewComponentFactory;
import oosd.views.handlers.SelectionPieceClickHandler;
import oosd.views.handlers.SelectionPieceDragReleasedHandler;
import oosd.views.handlers.UnitPieceClickHandler;
import oosd.views.handlers.UnitPieceDragDetectedHandler;

import java.util.HashMap;

import static oosd.helpers.ObjectHelper.exists;

/**
 * SOLID:  Single Responsibility Principle
 * The view should only be responsible for managing the user interface (e.g. interacting with the JavaFX library)
 */
public class BoardView implements View {
    private final Engine engine;
    private final BoardPane boardPane;
    private final GameController gameController;
    private HashMap<Piece, SelectionPiecePolygon> selectionPieces;
    private HashMap<Piece, UnitPiecePolygon> unitPieces;
    private HashMap<Piece, DefendPieceImage> defendPieces;
    private HashMap<Piece, BackgroundPiecePolygon> backgroundPieces;
    private ViewComponentFactory boardFactory;
    private SidebarPane sidebar;
    private Text playerTurn;
    private ToolbarPane toolbar;

    public BoardView(Engine engine, GamePresenter gamePresenter, GameController gameController) {
        this.sidebar = gamePresenter.getSidebar();
        this.playerTurn = sidebar.getPlayerTurnText();
        this.boardFactory = new ViewComponentFactory(engine.getBoard());
        this.engine = engine;
        this.gameController = gameController;

        boardPane = gamePresenter.getBoardPane();
        toolbar = gamePresenter.getToolbarPane();
    }

    public void start() {
        this.unitPieces = boardFactory.createUnitPiecePolygons();
        this.selectionPieces = boardFactory.createSelectionPiecePolygons();
        this.defendPieces = boardFactory.createDefendPieceImage();
        this.backgroundPieces = boardFactory.createBackgroundPiecePolygons();

        sidebar.initialise(engine);
        boardPane.initialise(engine, gameController, sidebar, unitPieces, selectionPieces, defendPieces, backgroundPieces);
        toolbar.initialise(engine, gameController, sidebar);
    }

    public void moveUnit(Piece selectedPiece, Piece clickedPiece) {
        Board board = engine.getBoard();
        board.apply((column, row) -> {
            Piece piece = board.getPiece(column, row);
            Unit unit = piece.getUnit();

            defendPieces.get(piece).hide();
            selectionPieces.get(piece).hide();

            if (exists(unit) && unit.getDefendStatus()) {
                defendPieces.get(piece).show();
            }
        });

        selectionPieces.get(selectedPiece).hide();
        unitPieces.get(selectedPiece).hide();
        unitPieces.get(clickedPiece).show();
        unitPieces.get(clickedPiece).setFill(boardFactory.createImage(clickedPiece.getUnit().getImage()));
        playerTurn.setText("Player turn: " + engine.getTurn().getPlayerName());
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

    public void defendUnit(Piece piece) {
        Board board = engine.getBoard();
        board.apply((column, row) -> selectionPieces.get(board.getPiece(column, row)).hide());

        defendPieces.get(piece).show();
        playerTurn.setText("Player turn: " + engine.getTurn().getPlayerName());
    }

    public void attackUnit(Piece selectedPiece, Piece piece) {
        Board board = engine.getBoard();
        board.apply((column, row) -> selectionPieces.get(board.getPiece(column, row)).hide());

        unitPieces.get(selectedPiece).hide();
        unitPieces.get(piece).setFill(boardFactory.createImage(piece.getUnit().getImage()));
        playerTurn.setText("Player turn: " + engine.getTurn().getPlayerName());
    }

    public void undoMove() {
        updateBoard();
    }

    public void resetGame() {
        updateBoard();
    }

    private void updateBoard() {
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

                if (unit.getDefendStatus()) {
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
