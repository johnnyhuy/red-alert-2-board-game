package oosd.views;

import oosd.views.components.panes.WelcomeWindowPane;

/**
 * SOLID:  Single Responsibility Principle
 * The view should only be responsible for managing the user interface (e.g. interacting with the JavaFX library)
 */
public class WelcomeView implements View {
    private WelcomeWindowPane welcomeWindowPane;

    public void welcome() {
        welcomeWindowPane = new WelcomeWindowPane();
    }
}
