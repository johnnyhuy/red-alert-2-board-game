package oosd.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import oosd.models.GameEngine;
import oosd.models.board.Hexagon;
import oosd.views.BoardView;
import com.google.java.contract.Requires;

public class GameController extends Controller {
    private final GameEngine gameEngine;

    @FXML
    private AnchorPane boardPane;

    public GameController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void initialize() {
        BoardView boardView = new BoardView(this, this.gameEngine, this.boardPane);
        boardView.render();
    }

    @Requires("hexagon != null")
    public void board(MouseEvent event, GameEngine gameEngine, Hexagon hexagon) {
        System.out.println("column " + hexagon.getColumn());
        System.out.println("row " + hexagon.getRow());
    }
}
