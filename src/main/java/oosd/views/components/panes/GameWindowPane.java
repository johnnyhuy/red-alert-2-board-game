package oosd.views.components.panes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import oosd.controllers.Controller;
import oosd.views.View;
import oosd.views.components.alerts.ErrorAlert;

import java.util.Objects;

public class GameWindowPane extends BorderPane {
    public GameWindowPane(Stage primaryStage) {
        final String windowTitle = "Red Alert 2 Board Game";
        final String windowIcon = "soviet.png";
        final String styles = "style/main.css";

        Image image = new Image(View.class.getResource("menu.png").toString());
        BackgroundSize backgroundSize = new BackgroundSize(this.getWidth(), this.getHeight(), true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        this.setBackground(background);

        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("board.fxml"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (Exception exception) {
            ErrorAlert errorAlert = new ErrorAlert();
            errorAlert.exit();
        }

        Scene content = new Scene(this, 1200, 900);
        content.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource(styles)).toString());

        primaryStage.setScene(content);
        primaryStage.setTitle(windowTitle);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(View.class.getResource(windowIcon).toString()));
        primaryStage.show();
    }

    public ToolbarPane getToolbar() {
        return (ToolbarPane) this.lookup("#toolbar");
    }

    public BoardPane getBoardPane() {
        return (BoardPane) this.lookup("#boardPane");
    }

    public SidebarPane getSidebar() {
        return (SidebarPane) this.lookup("#sidebar");
    }
}
