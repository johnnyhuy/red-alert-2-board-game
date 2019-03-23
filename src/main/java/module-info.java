module another.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    opens oosd.controllers to javafx.fxml;
    exports oosd.controllers;
}