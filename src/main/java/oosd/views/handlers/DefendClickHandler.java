package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.views.GamePresenter;

import static oosd.helpers.ObjectHelper.isNull;

public class DefendClickHandler implements EventHandler<MouseEvent> {
    private Engine engine;
    private GameController gameController;
    private GamePresenter gamePresenter;

    public DefendClickHandler(Engine engine, GameController gameController, GamePresenter gamePresenter) {
        this.engine = engine;
        this.gameController = gameController;
        this.gamePresenter = gamePresenter;
    }

    @Override
    public void handle(MouseEvent event) {
        Piece selectedPiece = engine.getSelected();

        if (isNull(selectedPiece)) {
            return;
        }

        gameController.defend(selectedPiece);
        gamePresenter.getTurnCount().updateTurn(engine);

        if (engine.getRemainingTurns() == 0) {
            gameController.endGame();
        }
    }
}
