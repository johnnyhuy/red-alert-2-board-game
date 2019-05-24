package oosd.views.components.alerts;

import javafx.scene.control.Alert;

public class ErrorAlert extends Alert {
    public ErrorAlert() {
        super(Alert.AlertType.ERROR);

        this.setTitle("Game error");
        this.setHeaderText("Whoops! Game crashed...");
        this.setContentText("I can't seem to load the game window so I'm going to die now :(");
        this.showAndWait();
    }

    public void exit() {
        System.exit(1);
    }
}
