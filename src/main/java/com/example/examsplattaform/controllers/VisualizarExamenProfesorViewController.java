package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.*;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class VisualizarExamenProfesorViewController {
    public ScrollPane myScrollPane;
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Profesor profesorLogeado;
    Examen examenSeleccionado;

    public void setMain(ExamsApplication examsApplication, Profesor profesorLogeado, Examen examenSeleccionado) {
        main=examsApplication;
        this.profesorLogeado=profesorLogeado;
        this.examenSeleccionado=examenSeleccionado;
        llenarScroll();
    }

    private void llenarScroll() {
        VBox mainVBox = new VBox();
        mainVBox.setSpacing(10); // Espaciado entre las preguntas

        for (Pregunta pregunta : examenSeleccionado.getPreguntas().values()) {
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
    }

    public void onVolverButtonClick(ActionEvent event) throws IOException {
        main.abrirPanelProfesor(profesorLogeado);
    }

    public void onCompartirButtonClick(ActionEvent event) throws IOException {
       main.mostrarClave(examenSeleccionado);
        main.abrirPanelProfesor(profesorLogeado);
    }

}
