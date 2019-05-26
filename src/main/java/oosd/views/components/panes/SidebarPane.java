package oosd.views.components.panes;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

@Component
public class SidebarPane extends AnchorPane {
    public Text getPlayerTurnText() {
        return (Text) this.lookup("#playerTurn");
    }

    public Text getTurnCountText() {
        return (Text) this.lookup("#turnCount");
    }
}
