package com.example.examsplattaform;

import com.example.examsplattaform.controllers.*;
import com.example.examsplattaform.model.Estudiante;
import com.example.examsplattaform.model.Examen;
import com.example.examsplattaform.model.Persona;
import com.example.examsplattaform.model.Profesor;
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

    public void abrirSelector(boolean isLogin, Persona persona) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("selection-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        SelectionViewController controller = fxmlLoader.getController();
        controller.setMain(this, isLogin, persona);
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
        stage.setTitle("Registro Profesor");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirRegistrarUsuario() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("registerStudent-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        RegisterStudentViewController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Registro Student");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirPanelProfesor(Profesor profesorLogeado) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("profesorPanel-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        ProfesorPanelViewController controller = fxmlLoader.getController();
        controller.setMain(this, profesorLogeado);
        stage.setTitle("Bienvenido "+ profesorLogeado.getNombre());
        stage.setScene(scene);
        stage.show();
    }

    public void abrirPanelEstudiante(Estudiante estudianteLogeado) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("studentPanel-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        StudentPanelViewController controller = fxmlLoader.getController();
        controller.setMain(this, estudianteLogeado);
        stage.setTitle("Bienvenido "+ estudianteLogeado.getNombre());
        stage.setScene(scene);
        stage.show();
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

    public void expandirExamenSeleccionado(Examen examenSeleccionado,Estudiante estudiante, Profesor profesor) {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("examZoom-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        ExamZoomViewController controller = fxmlLoader.getController();
        controller.setMain(this, estudiante, profesor);
        stage.setTitle("Ampliacion de Examen: " +examenSeleccionado.getTitulo());
        stage.setScene(scene);
        stage.show();

    }

    public void abrirCreadorPreguntasExamen(Profesor profesorLogeado, Examen examen) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("addQuestions-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        AddQuestionsViewController controller = fxmlLoader.getController();
        controller.setMain(this, profesorLogeado, examen);
        stage.setTitle("Vamos a añadir las preguntas de tu examen");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirCreadorExamenes(Profesor profesorLogeado) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("examsCreation-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        ExamsCreationViewController controller = fxmlLoader.getController();
        controller.setMain(this, profesorLogeado);
        stage.setTitle("Nuevo examen");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirAgregarPreguntaMultiple(Profesor profesorLogeado, Examen examenCreado) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("newQuestion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        NewQuestionViewController controller = fxmlLoader.getController();
        controller.setMain(this, profesorLogeado, examenCreado);
        stage.setTitle("Nueva Pregunta Múltiple");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirAgregarPreguntaTF(Profesor profesorLogeado, Examen examen) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("newTFQuestion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        NewTFQuestionViewController controller = fxmlLoader.getController();
        controller.setMain(this, profesorLogeado, examen);
        stage.setTitle("Nueva pregunta True or False");
        stage.setScene(scene);
        stage.show();

    }
    public void abrirAgregarPreguntaAbierta(Profesor profesorLogeado, Examen examen) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("newOpenQuestion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        NewOpenQuestionViewController controller = fxmlLoader.getController();
        controller.setMain(this, profesorLogeado, examen);
        stage.setTitle("Nueva Pregunta Abierta");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirVisualizarExamenProfesor(Profesor profesorLogeado, Examen examenSeleccionado) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExamsApplication.class.getResource("visualizarExamenProfesor-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(ExamsApplication.class.getResource("styles.css")).toExternalForm());
        VisualizarExamenProfesorViewController controller = fxmlLoader.getController();
        controller.setMain(this, profesorLogeado, examenSeleccionado);
        stage.setTitle("Ampliación del nuevo examen");
        stage.setScene(scene);
        stage.show();
    }
}