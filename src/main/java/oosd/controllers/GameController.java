package oosd.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import oosd.models.GameEngine;
import oosd.models.board.Hexagon;
import oosd.views.BoardView;

public class GameController extends Controller {
    private final GameEngine gameEngine;

    @FXML
    private AnchorPane boardPane;

    private BoardView boardView;

    public GameController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void initialize() {
        this.boardView = new BoardView(this, this.gameEngine, this.boardPane);
        this.boardView.render();
    }

    public void board(MouseEvent event, Hexagon hexagon) {
        Hexagon previousHexagon = gameEngine.getSelectedHexagon();
        this.gameEngine.setSelectedHexagon(hexagon);
        this.boardView.selectUnit(previousHexagon, hexagon);
    }
}
