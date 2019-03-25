package oosd.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import oosd.models.GameEngine;
import oosd.models.board.Hexagon;
import oosd.views.BoardView;

public class GameController extends Controller {
    @FXML
    private AnchorPane boardPane;

    GameController(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public void initialize() {
        BoardView boardView = new BoardView(this, this.gameEngine.getBoard(), boardPane);
        boardView.render();
    }

    public void handleHexagonClick(MouseEvent event, Hexagon hexagon) {
        System.out.println("column " + hexagon.getColumn());
        System.out.println("row " + hexagon.getRow());
    }
}
