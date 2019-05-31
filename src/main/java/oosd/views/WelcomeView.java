package oosd.views;

import oosd.views.components.panes.WelcomeWindowPane;

/**
 * SOLID:  Single Responsibility Principle
 * The view should only be responsible for managing the user interface (e.g. interacting with the JavaFX library)
 */
public class WelcomeView implements View {
    public void welcome() {
        new WelcomeWindowPane();
    }

    public void welcome(String title, String description) {
        new WelcomeWindowPane(title, description);
    }
}
