module com.example.examsplattaform {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.examsplattaform to javafx.fxml;
    exports com.example.examsplattaform;
    opens com.example.examsplattaform.controllers to javafx.fxml;
    exports com.example.examsplattaform.controllers;
    exports com.example.examsplattaform.model;

}