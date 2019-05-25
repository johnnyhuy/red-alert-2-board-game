package oosd.views.components.panes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oosd.controllers.Controller;
import oosd.views.View;
import oosd.views.components.alerts.ErrorAlert;

import java.util.Objects;

public class WelcomeWindowPane extends BorderPane {
    public WelcomeWindowPane(String heading, String description) {
        createWindow(heading, description);
    }

    public WelcomeWindowPane() {
        this("Welcome commander!", "Start the game below when you're ready.");
    }

    private Text getWelcomeHeading() {
        return (Text) this.lookup("#welcomeHeading");
    }

    private Text getWelcomeDescription() {
        return (Text) this.lookup("#welcomeDescription");
    }

    private void createWindow(String heading, String description) {
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

        getWelcomeHeading().setText(heading);
        getWelcomeDescription().setText(description);

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
