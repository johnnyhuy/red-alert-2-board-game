package oosd.views.presenters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import oosd.models.game.Engine;
import oosd.models.game.GameLogger;
import oosd.models.game.Log;
import oosd.views.components.panes.PlayerInfoVBox;
import oosd.views.components.text.PlayerTurnText;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class SidebarPresenter implements Initializable {
    private final Engine engine;
    private final GameLogger gameLogger;

    @FXML
    private PlayerTurnText playerTurnText;

    @FXML
    private PlayerInfoVBox playerInfoVBox;

    @FXML
    private ScrollPane gameLogScrollPane;

    @FXML
    private VBox gameLogVBox;

    @Inject
    public SidebarPresenter(Engine engine, GameLogger gameLogger) {
        this.engine = engine;
        this.gameLogger = gameLogger;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        update();
    }

    public void update() {
        getGameLogVBox().getChildren().clear();

        for (Log log : gameLogger.getLogs()) {
            Text text = new Text();
            text.setText(log.getText());
            text.setFill(log.getColor());
            text.getStyleClass().add("game-text");
            text.setWrappingWidth(150);
            getGameLogVBox().getChildren().add(text);
        }

        getPlayerTurnText().updateTurn(engine);
        getPlayerInfoVBox().update(engine);
        getGameLogScrollPane().setVvalue(1.0);
    }

    private PlayerInfoVBox getPlayerInfoVBox() {
        return playerInfoVBox;
    }

    private PlayerTurnText getPlayerTurnText() {
        return playerTurnText;
    }

    private ScrollPane getGameLogScrollPane() {
        return gameLogScrollPane;
    }

    private VBox getGameLogVBox() {
        return gameLogVBox;
    }
}
