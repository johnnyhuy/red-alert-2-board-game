module board.javafx {
    requires javafx.controls;
    requires javafx.fxml;
	requires cofoja;

    opens oosd.controllers to javafx.fxml;
    opens oosd to javafx.fxml;
    opens oosd.models;

    exports oosd;
}