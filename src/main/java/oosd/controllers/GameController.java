package oosd.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import oosd.views.BoardView;

public class GameController extends Controller {
    @FXML
    private AnchorPane tilePane;

    @Override
    public void initialize() {
        BoardView boardView = new BoardView(tilePane);
        boardView.render();
    }
}
