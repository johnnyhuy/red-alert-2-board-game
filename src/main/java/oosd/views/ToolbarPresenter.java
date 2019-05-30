package oosd.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ToolbarPresenter implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
}
