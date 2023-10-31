package com.example.examsplattaform;

import com.example.examsplattaform.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ExamsApplication extends Application {
    private Stage stage;
    private final Image icon = new Image("icon.png");
    @Override
    public void start(Stage stage) throws IOException {
       this.stage = stage;
       this.stage.getIcons().add(icon);
       this.stage.setResizable(false);
       this.stage.setOnCloseRequest(event -> {
           event.consume();
           intentarCerrar();
       });
       inicilizarLogin();
    }

    public void inicilizarLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        LoginViewController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirSelector(boolean isLogin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("selection-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        SelectionViewController controller = fxmlLoader.getController();
        controller.setMain(this, isLogin);
        stage.setTitle("Selector");
        stage.setScene(scene);
        stage.show();
    }
    public void abrirRegistrarProfesor() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("registerTeacher-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        RegisterViewController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirRegistrarUsuario() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("registerStudent-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        RegisterStudentViewController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirPanelProfesor() {
    }

    public void abrirPanelEstudiante() {
    }
    private void intentarCerrar(){
        if(Alerta.saltarAlertaConfirmacion("ESTA APUNTO DE CERRAR","Usted está a punto de salir") == ButtonType.OK){
            System.out.println("Ha cerrado la aplicación");
            stage.close();
        }
    }
    public static void main(String[] args) {
        launch();
    }

}