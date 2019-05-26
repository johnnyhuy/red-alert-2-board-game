package oosd.views;

import oosd.views.components.panes.WelcomeWindowPane;

import javax.inject.Inject;

/**
 * SOLID:  Single Responsibility Principle
 * The view should only be responsible for managing the user interface (e.g. interacting with the JavaFX library)
 */
public class WelcomeView implements View {
    private WelcomeWindowPane welcomeWindowPane;

    @Inject
    public WelcomeView() {

    }

    public void welcome() {
        welcomeWindowPane = new WelcomeWindowPane();
    }

    public void welcome(String title, String description) {
        welcomeWindowPane = new WelcomeWindowPane(title, description);
    }
}
