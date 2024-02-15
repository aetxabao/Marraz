module com.masanz.marraz.marraz {
    requires log4j;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.fasterxml.jackson.databind;

    opens com.masanz.marraz.controller to javafx.fxml;
    opens com.masanz.marraz.app to javafx.fxml;
    opens com.masanz.marraz.model to com.fasterxml.jackson.databind;
//    opens javafx.scene.paint to com.fasterxml.jackson.databind;
    exports com.masanz.marraz;
    exports com.masanz.marraz.app;
    exports com.masanz.marraz.controller;

}