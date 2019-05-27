package oosd.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import oosd.controllers.GameController;
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

    private GameController gameController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gameController = context.getBean(GameController.class);
        gameController.start();
    }

    public ToolbarPane getToolbar() {
        return toolbar;
    }

    public SidebarPane getSidebar() {
        return sidebar;
    }

    public BoardPane getBoardPane() {
        return board;
    }

    public GameController getGameController() {
        return gameController;
    }

    public TurnCountText getTurnCount() {
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

    public PlayerTurnText getPlayerTurn() {
        return playerTurn;
    }
}
