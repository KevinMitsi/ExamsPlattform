package com.example.examsplattaform.controllers;
import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
public class AddQuestionsViewController {
    public Button btnMultiple;
    public Hyperlink hlVolver;
    public Button btnTrueFalse;
    public Button btnOpenQuestion;
    public ScrollPane myScrollPane;
    public Button btnFinalizar;
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
        VBox mainVBox = new VBox();
        mainVBox.setSpacing(10); // Espaciado entre las preguntas

        for (Pregunta pregunta : examen.getPreguntas().values()) {
            VBox preguntaVBox = new VBox();
            preguntaVBox.setSpacing(5); // Espaciado entre los elementos de la pregunta

            if (pregunta instanceof PreguntaMultiple) {
                Label titleLabel = new Label("Título: " + pregunta.getTitulo());
                Text enunciadoText = new Text("Enunciado: " + pregunta.getEnunciado());
                ToggleGroup toggleGroup = new ToggleGroup();

                preguntaVBox.getChildren().addAll(titleLabel, enunciadoText);
                for (Repuesta respuesta : ((PreguntaMultiple) pregunta).getRespuestas().values()) {
                    RadioButton radioButton = new RadioButton(respuesta.getLetra() + ". " + respuesta.getDescripcion());
                    radioButton.setId("opcion" + respuesta.getLetra());
                    radioButton.setToggleGroup(toggleGroup);
                    preguntaVBox.getChildren().add(radioButton);
                }
            } else if (pregunta instanceof PreguntaAbierta) {
                StringBuilder keys = new StringBuilder();
                Label titleLabel = new Label("Título: " + pregunta.getTitulo());
                Text enunciadoText = new Text("Enunciado: " + pregunta.getEnunciado());
                TextArea textArea = new TextArea();
                textArea.setPromptText("Escribe tu respuesta aquí");
                for (String clave:((PreguntaAbierta) pregunta).getRespuestaCorrecta()) {
                    keys.append("{").append(clave).append("},");
                }
                Text myKeys = new Text("Key words: "+keys);
                textArea.setWrapText(true);
                preguntaVBox.getChildren().addAll(titleLabel, enunciadoText, textArea, myKeys);
            } else if (pregunta instanceof PreguntaTF) {
                Label titleLabel = new Label("Título: " + pregunta.getTitulo());
                Text enunciadoText = new Text("Enunciado: " + pregunta.getEnunciado());
                ToggleGroup toggleGroup = new ToggleGroup();
                RadioButton trueRadioButton = new RadioButton("TRUE");
                RadioButton falseRadioButton = new RadioButton("FALSE");
                trueRadioButton.setToggleGroup(toggleGroup);
                falseRadioButton.setToggleGroup(toggleGroup);

                preguntaVBox.getChildren().addAll(titleLabel, enunciadoText, trueRadioButton, falseRadioButton);
            }

            mainVBox.getChildren().add(preguntaVBox);
        }
        myScrollPane.setContent(mainVBox);
        examen.setContainer(mainVBox);
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
    public void onTrueFalseClick(ActionEvent event) throws IOException {
        main.abrirAgregarPreguntaTF(profesorLogeado,examen);
    }
    @FXML
    public void onOpenQuestionClick(ActionEvent event) throws IOException{
        main.abrirAgregarPreguntaAbierta(profesorLogeado,examen);
    }

    public void onFinalizarBurronClick(ActionEvent event) throws IOException {
        if (examen.getPreguntas().size()>0){
            if(Alerta.saltarAlertaConfirmacion("ESTA APUNTO DE CREAR EL EXAMEN","Está seguro de que quiere finalizar el examen") == ButtonType.OK){
                examen.setFinished(true);

                main.abrirPanelProfesor(profesorLogeado);
            }
        }
        else {
            Alerta.saltarAlertaError("Esta intentando crear un examen vacío");
        }
    }
}
