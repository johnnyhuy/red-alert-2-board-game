package oosd.views.components.panes;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import oosd.views.View;

public class WindowGridPane extends BorderPane {
    private final int sceneWidth;
    private final int sceneHeight;
    private final String windowTitle;
    private final String boardFileName;
    private Scene content = null;

    public WindowGridPane() {
        this.sceneWidth = 1200;
        this.sceneHeight = 900;
        this.windowTitle = "Red Alert 2 Board Game";
        this.boardFileName = "board.fxml";
        Image image = new Image(View.class.getResource("menu.png").toString());
        BackgroundSize backgroundSize = new BackgroundSize(this.getWidth(), this.getHeight(), true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        this.setBackground(background);
    }

    public Scene getContent() {
        return content;
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
