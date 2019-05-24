package oosd.views.components.panes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oosd.controllers.Controller;
import oosd.views.View;
import oosd.views.components.alerts.ErrorAlert;

import java.util.Objects;

public class WelcomeWindowPane extends BorderPane {
    public WelcomeWindowPane() {
        final String windowTitle = "Welcome!";
        final String windowIcon = "allied.png";
        final String styles = "style/main.css";

        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("welcome.fxml"));
        loader.setController(this);

        try {
            loader.setRoot(this);
            loader.load();

            Scene content = new Scene(this, 600, 450);
            content.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource(styles)).toString());

            Stage stage = new Stage();
            stage.setScene(content);
            stage.setTitle(windowTitle);
            stage.setResizable(false);
            stage.getIcons().add(new Image(View.class.getResource(windowIcon).toString()));
            stage.show();
        } catch (Exception exception) {
            ErrorAlert errorAlert = new ErrorAlert();
            errorAlert.exit();
        }
    }
}
