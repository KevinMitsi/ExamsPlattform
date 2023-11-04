package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class AddQuestionsViewController {
    public Button btnMultiple;
    public Hyperlink hlVolver;
    public Button btnTrueFalse;
    public Button btnOpenQuestion;
    public ScrollPane myScrollPane;
    ExamsApplication main;
    Profesor profesorLogeado;
    Examen examen;
    ModelFactoryController singleton = ModelFactoryController.getInstance();



    public void setMain(ExamsApplication examsApplication, Profesor profesorLogeado, Examen examen) {
        this.main = examsApplication;
        this.profesorLogeado = profesorLogeado;
        this.examen = examen;
        fillScrollPane();
    }

    private void fillScrollPane() {
        VBox vBox = new VBox();
        for (Pregunta pregunta : examen.getPreguntas().values()) {
            if (pregunta instanceof PreguntaMultiple) {
                Label label = new Label(pregunta.getTitulo());
                Text text = new Text(pregunta.getEnunciado());
                ToggleGroup toggleGroup = new ToggleGroup();
               vBox.getChildren().addAll(label,text);
               for (Repuesta respuesta : ((PreguntaMultiple) pregunta).getRespuestas().values()) {
                    RadioButton radioButton = new RadioButton(respuesta.getLetra()+" "+respuesta.getDescripcion());
                    radioButton.setId("opcion"+respuesta.getLetra());
                    radioButton.setToggleGroup(toggleGroup);
                    vBox.getChildren().add(radioButton);
                }

            }
            if (pregunta instanceof PreguntaAbierta){
                Label label = new Label(pregunta.getTitulo());
                Text text = new Text(pregunta.getEnunciado());
                TextArea textArea = new TextArea();
                textArea.setPromptText("Diigete su respuesta");
                vBox.getChildren().addAll(label,text,textArea);
            }
            if (pregunta instanceof PreguntaTF){
                ToggleGroup toggleGroup = new ToggleGroup();
                Label label = new Label(pregunta.getTitulo());
                Text text = new Text(pregunta.getEnunciado());
                RadioButton t = new RadioButton("TRUE");
                RadioButton f = new RadioButton("FALSE");
                t.setToggleGroup(toggleGroup);
                f.setToggleGroup(toggleGroup);
                vBox.getChildren().addAll(label,text,t,f);
            }
        }
    }

    @FXML
    public void onBtnMultipleClick(ActionEvent event) throws IOException {
        main.abrirAgregarPreguntaMultiple(profesorLogeado,examen);
    }
    @FXML
    public void onVolverClicked(ActionEvent event) throws IOException {
        main.abrirPanelProfesor(profesorLogeado);
    }
    @FXML
    public void onTrueFalseClick(ActionEvent event) {
        main.abrirAgregarPreguntaTF(profesorLogeado,examen);
    }
    @FXML
    public void onOpenQuestionClick(ActionEvent event) {
        main.abrirAgregarPreguntaAbierta(profesorLogeado,examen);
    }
}
