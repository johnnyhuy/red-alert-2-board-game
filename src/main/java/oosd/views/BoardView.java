package oosd.views;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.models.units.Unit;
import oosd.views.components.*;
import oosd.views.factories.ViewComponentFactory;

import java.util.HashMap;

import static oosd.helpers.ObjectHelper.exists;

/**
 * SOLID:  Single Responsibility Principle
 * The view should only be responsible for managing the user interface (e.g. interacting with the JavaFX library)
 */
public class BoardView implements View {
    private final Board board;
    private final HashMap<Piece, SelectionPiecePolygon> selectionPieces;
    private final HashMap<Piece, UnitPiecePolygon> unitPieces;
    private final HashMap<Piece, DefendPieceImage> defendPieces;
    private final HashMap<Piece, BackgroundPiecePolygon> backgroundPieces;
    private Pane windowGridPane;
    private final BoardPane boardPane;
    private final GameController controller;
    private final ViewComponentFactory boardFactory;
    private GameEngine gameEngine;
    private SidebarPane sidebar;
    private ToolbarPane toolbar;
    private Text playerTurn;

    public BoardView(GameController controller, GameEngine gameEngine, WindowGridPane windowGridPane, BoardPane boardPane, SidebarPane sidebar, ToolbarPane toolbar) {
        this.controller = controller;
        this.gameEngine = gameEngine;
        this.windowGridPane = windowGridPane;
        this.boardPane = boardPane;
        this.sidebar = sidebar;
        this.toolbar = toolbar;
        this.board = gameEngine.getBoard();
        this.boardFactory = new ViewComponentFactory(board);
        this.unitPieces = boardFactory.createUnitPiecePolygons();
        this.selectionPieces = boardFactory.createSelectionPiecePolygons();
        this.defendPieces = boardFactory.createDefendPieceImage();
        this.backgroundPieces = boardFactory.createBackgroundPiecePolygons();
        this.playerTurn = sidebar.getPlayerTurnText();
    }

    public void render() {
        playerTurn.setText("Player turn: " + gameEngine.getTurn().getPlayerName());
        boardPane.createBoard(gameEngine, controller, unitPieces, selectionPieces, defendPieces, backgroundPieces);
    }

    public void moveUnit(Piece selectedPiece, Piece clickedPiece) {
        board.apply((column, row) -> {
            Piece piece = board.getPiece(column, row);
            Unit unit = piece.getUnit();

            defendPieces.get(piece).setVisible(false);
            selectionPieces.get(piece).setVisible(false);

            if (exists(unit) && unit.getDefendStatus()) {
                defendPieces.get(piece).setVisible(true);
            }
        });

        selectionPieces.get(selectedPiece).setVisible(false);
        unitPieces.get(selectedPiece).setVisible(false);
        unitPieces.get(selectedPiece).setFill(null);
        unitPieces.get(clickedPiece).setVisible(true);
        unitPieces.get(clickedPiece).setFill(boardFactory.createImage(clickedPiece.getUnit().getImage()));
        playerTurn.setText("Player turn: " + gameEngine.getTurn().getPlayerName());
    }

    public void selectUnit(Piece selectedPiece, Piece clickedPiece) {
        if (exists(selectedPiece)) {
            selectionPieces.get(selectedPiece).setVisible(false);

            Unit unit = selectedPiece.getUnit();
            if (exists(unit)) {
                for (Piece piece : unit.getUnitBehaviour().getValidMoves(gameEngine, selectedPiece)) {
                    selectionPieces.get(piece).setVisible(false);
                }
            }
        }

        for (Piece piece : clickedPiece.getUnit().getUnitBehaviour().getValidMoves(gameEngine, clickedPiece)) {
            selectionPieces.get(piece).setVisible(true);

            Unit unit = piece.getUnit();
            if (exists(unit) && !unit.getPlayer().equals(gameEngine.getTurn())) {
                selectionPieces.get(piece).setFill(Paint.valueOf("#FF0000"));
            } else {
                selectionPieces.get(piece).setFill(Paint.valueOf("#00C400"));
            }
        }

        selectionPieces.get(clickedPiece).setFill(Paint.valueOf("#dadada"));
    }

    public void defendUnit(Piece piece) {
        board.apply((column, row) -> selectionPieces.get(board.getPiece(column, row)).setVisible(false));

        defendPieces.get(piece).setVisible(true);
        playerTurn.setText("Player turn: " + gameEngine.getTurn().getPlayerName());
    }

    public void attackUnit(Piece selectedPiece, Piece piece) {
        board.apply((column, row) -> {
            selectionPieces.get(board.getPiece(column, row)).setFill(null);
            selectionPieces.get(board.getPiece(column, row)).setVisible(false);
        });

        unitPieces.get(selectedPiece).setFill(null);
        unitPieces.get(piece).setFill(boardFactory.createImage(piece.getUnit().getImage()));
        playerTurn.setText("Player turn: " + gameEngine.getTurn().getPlayerName());
    }
}
