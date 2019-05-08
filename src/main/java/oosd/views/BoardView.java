package oosd.views;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.views.components.*;
import oosd.views.factories.ViewComponentFactory;

import java.util.HashMap;

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
        this.unitPieces = boardFactory.createPiecePolygons(new UnitPiecePolygon());
        this.selectionPieces = boardFactory.createPiecePolygons(new SelectionPiecePolygon());
        this.defendPieces = boardFactory.createPiecePolygons(new DefendPieceImage());
        this.backgroundPieces = boardFactory.createPiecePolygons(new BackgroundPiecePolygon());
        this.playerTurn = sidebar.getPlayerTurnText();
    }

    public void moveUnit(Piece selectedPiece, Piece clickedPiece) {
        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                selectionPieces.get(board.getPiece(xIndex, yIndex)).setVisible(false);
            }
        }

        selectionPieces.get(selectedPiece).setOpacity(1.0);
        selectionPieces.get(selectedPiece).setVisible(false);
        unitPieces.get(selectedPiece).setVisible(false);
        unitPieces.get(clickedPiece).setVisible(true);
        unitPieces.get(clickedPiece).setFill(boardFactory.createImage(clickedPiece.getUnit().getImage()));
        playerTurn.setText("Player turn: " + gameEngine.getTurn().getPlayerName());
    }

    public void selectUnit(Piece selectedPiece, Piece clickedPiece) {
        if (selectedPiece != null) {
            selectionPieces.get(selectedPiece).setVisible(false);

            for (Piece piece : selectedPiece.getUnit().getUnitBehaviour().getValidMoves(gameEngine, selectedPiece)) {
                selectionPieces.get(piece).setVisible(false);
            }
        }

        for (Piece piece : clickedPiece.getUnit().getUnitBehaviour().getValidMoves(gameEngine, clickedPiece)) {
            selectionPieces.get(piece).setVisible(true);
            selectionPieces.get(piece).setFill(Paint.valueOf("#00C400"));
        }

        selectionPieces.get(clickedPiece).setFill(Paint.valueOf("#dadada"));
        selectionPieces.get(clickedPiece).setOpacity(0.5);
    }

    public void render() {
        playerTurn.setText("Player turn: " + gameEngine.getTurn().getPlayerName());
        boardPane.createBoard(gameEngine, controller, unitPieces, selectionPieces, defendPieces, backgroundPieces);
    }

    public void defendUnit(Piece piece) {
        for (int yIndex = 0; yIndex < board.getRows(); yIndex++) {
            for (int xIndex = 0; xIndex < board.getColumns(); xIndex++) {
                selectionPieces.get(board.getPiece(xIndex, yIndex)).setVisible(false);
            }
        }

        defendPieces.get(piece).setVisible(true);
    }
}
