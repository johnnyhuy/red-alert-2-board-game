package oosd.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.views.BoardView;

public class GameController extends Controller {
    @FXML
    private AnchorPane tilePane;

    @Override
    public void initialize() {
        Board board = new Board(6, 6);
        BoardView boardView = new BoardView(this, board, tilePane);
        boardView.render();
    }

    public void handleHexagonClick(MouseEvent event, Hexagon hexagon) {
        System.out.println("column " + hexagon.getColumn());
        System.out.println("row " + hexagon.getRow());
    }
}
