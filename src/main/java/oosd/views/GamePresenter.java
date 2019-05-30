package oosd.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import oosd.controllers.GameController;
import oosd.models.game.Engine;
import oosd.models.game.GameLogger;
import oosd.models.game.Log;
import oosd.views.components.panes.BoardPane;
import oosd.views.components.panes.PlayerInfoVBox;
import oosd.views.components.panes.SidebarPane;
import oosd.views.components.panes.ToolbarPane;
import oosd.views.components.text.PlayerTurnText;
import oosd.views.components.text.TurnCountText;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class GamePresenter implements Initializable {
    @Inject
    private ApplicationContext context;

    @FXML
    private SidebarPane sidebar;

    @FXML
    private ToolbarPane toolbar;

    @FXML
    private BoardPane board;

    @FXML
    private TurnCountText turnCount;

    @FXML
    private PlayerTurnText playerTurn;

    @FXML
    private Button undoButton;

    @FXML
    private Button defendButton;

    @FXML
    private Button forfeitButton;

    @FXML
    private Button saveGameButton;

    @FXML
    private Button restoreGameButton;

    @FXML
    private PlayerInfoVBox playerInfoVBox;

    @FXML
    private VBox gameLogVBox;

    private GameController gameController;
    private Engine engine;
    private GameLogger gameLogger;

    @Inject
    public GamePresenter(Engine engine, GameLogger gameLogger) {
        this.engine = engine;
        this.gameLogger = gameLogger;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gameController = context.getBean(GameController.class);
        gameController.start();
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
    }

    public ToolbarPane getToolbarPane() {
        return toolbar;
    }

    public SidebarPane getSidebarPane() {
        return sidebar;
    }

    public BoardPane getBoardPane() {
        return board;
    }

    public GameController getGameController() {
        return gameController;
    }

    public TurnCountText getTurnCountText() {
        return turnCount;
    }

    public Button getUndoButton() {
        return undoButton;
    }

    public Button getDefendButton() {
        return defendButton;
    }

    public Button getForfeitButton() {
        return forfeitButton;
    }

    public Button getSaveGameButton() {
        return saveGameButton;
    }

    public Button getRestoreGameButton() {
        return restoreGameButton;
    }

    public PlayerInfoVBox getPlayerInfoVBox() {
        return playerInfoVBox;
    }

    public PlayerTurnText getPlayerTurnText() {
        return playerTurn;
    }

    public VBox getGameLogVBox() {
        return gameLogVBox;
    }
}
