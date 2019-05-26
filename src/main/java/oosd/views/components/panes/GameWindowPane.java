package oosd.views.components.panes;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import oosd.Config;
import oosd.controllers.Controller;
import oosd.views.View;
import oosd.views.components.alerts.ErrorAlert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GameWindowPane extends BorderPane {
    public GameWindowPane() {
        Image image = new Image(View.class.getResource("menu.png").toString());
        BackgroundSize backgroundSize = new BackgroundSize(this.getWidth(), this.getHeight(), true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        this.setBackground(background);

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("board.fxml"));
        loader.setRoot(this);
        loader.setControllerFactory(context::getBean);

        try {
            loader.load();
        } catch (Exception exception) {
            ErrorAlert errorAlert = new ErrorAlert();
            errorAlert.exit();
        }
    }
}
