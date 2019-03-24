package oosd.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import oosd.models.board.Board;
import oosd.views.BoardView;

public class GameController extends Controller {
    @FXML
    private AnchorPane tilePane;

    @Override
    public void initialize() {
        Board board = new Board(6, 6);
        BoardView boardView = new BoardView(board, tilePane);
        boardView.render();
    }
}
