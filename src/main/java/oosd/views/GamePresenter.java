package oosd.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import oosd.controllers.GameController;
import oosd.views.components.panes.BoardPane;
import oosd.views.components.panes.SidebarPane;
import oosd.views.components.panes.ToolbarPane;
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
    private Text turnCount;

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
    private VBox playersBox;

    private GameController gameController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gameController = context.getBean(GameController.class);
        gameController.start();
    }

    public ToolbarPane getToolbarPane() {
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

    public Text getTurnCount() {
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

    public VBox getPlayersBox() {
        return playersBox;
    }
}
