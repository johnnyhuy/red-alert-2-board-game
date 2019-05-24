package oosd.views.components.panes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oosd.controllers.Controller;
import oosd.views.View;
import oosd.views.components.alerts.ErrorAlert;

import java.util.Objects;

public class WelcomeWindowPane extends VBox {
    private String title;
    private String description;

    public WelcomeWindowPane(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public WelcomeWindowPane() {
        final String windowTitle = "Battle control initialised!";
        final String windowIcon = "allied.png";
        final String styles = "style/main.css";

        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("welcome.fxml"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (Exception exception) {
            ErrorAlert errorAlert = new ErrorAlert();
            errorAlert.exit();
        }

        Scene content = new Scene(this, 400, 320);
        content.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource(styles)).toString());

        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setScene(content);
        stage.setTitle(windowTitle);
        stage.setResizable(false);
        stage.getIcons().add(new Image(View.class.getResource(windowIcon).toString()));
        stage.show();

        Button startButton = (Button) this.lookup("#welcomeStartButton");
        startButton.setOnMouseClicked((event) -> stage.close());
    }
}
