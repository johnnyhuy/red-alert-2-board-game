package oosd.views;

import oosd.controllers.Controller;
import oosd.models.GameEngine;
import oosd.views.components.windows.UndoWindow;

public class UndoView implements View {
    private final UndoWindow undoWindow;
    private Controller controller;

    public UndoView(Controller controller, GameEngine gameEngine) {
        this.controller = controller;
        this.undoWindow = new UndoWindow();
    }

    public void render() {
        undoWindow.render(controller);
    }
}
