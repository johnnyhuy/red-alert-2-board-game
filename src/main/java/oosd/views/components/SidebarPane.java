package oosd.views.components;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SidebarPane extends Pane {
    public Text getPlayerTurnText() {
        return (Text) this.lookup("#playerTurn");
    }
}
