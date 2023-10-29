module com.example.examsplattaform {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens com.example.examsplattaform to javafx.fxml;
    exports com.example.examsplattaform;
    opens com.example.examsplattaform.controllers to javafx.fxml;
    exports com.example.examsplattaform.controllers;
    exports com.example.examsplattaform.model;
    exports com.example.examsplattaform.exceptions;
}