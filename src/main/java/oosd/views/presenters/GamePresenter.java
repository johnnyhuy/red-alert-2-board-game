package oosd.views.presenters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import oosd.controllers.GameController;
import oosd.views.components.panes.BoardPane;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class GamePresenter implements Initializable {
    private GameController gameController;
    private ApplicationContext context;

    @FXML
    private BoardPane board;

    @Inject
    public GamePresenter(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gameController = context.getBean(GameController.class);
        gameController.start();
    }

    public BoardPane getBoardPane() {
        return board;
    }

    public GameController getGameController() {
        return gameController;
    }
}
