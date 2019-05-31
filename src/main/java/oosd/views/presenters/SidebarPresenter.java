package oosd.views.presenters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import oosd.models.game.Engine;
import oosd.models.game.Log;
import oosd.models.game.Logger;
import oosd.views.components.panes.PlayerInfoVBox;
import oosd.views.components.text.PlayerTurnText;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class SidebarPresenter implements Initializable {
    private final Engine engine;
    private final Logger logger;

    @FXML
    private PlayerTurnText playerTurnText;

    @FXML
    private PlayerInfoVBox playerInfoVBox;

    @FXML
    private ScrollPane gameLogScrollPane;

    @FXML
    private VBox gameLogVBox;

    @Inject
    public SidebarPresenter(Engine engine, Logger logger) {
        this.engine = engine;
        this.logger = logger;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        update();
    }

    public void update() {
        getGameLogVBox().getChildren().clear();

        for (Log log : logger.getLogs()) {
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
