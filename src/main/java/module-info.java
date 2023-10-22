module com.example.examsplattaform {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.examsplattaform to javafx.fxml;
    exports com.example.examsplattaform;
}