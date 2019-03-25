package oosd.controllers;

import oosd.models.GameEngine;

abstract class Controller {
    protected final GameEngine gameEngine;

    Controller(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    abstract void initialize();
}
