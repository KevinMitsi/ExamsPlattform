package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.exceptions.PreguntaException;
import com.example.examsplattaform.model.Examen;
import com.example.examsplattaform.model.PreguntaAbierta;
import com.example.examsplattaform.model.Profesor;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class NewOpenQuestionViewController {
    public TextField tfWords;
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Profesor profesorLogeado;
    Examen examen;
    private PreguntaAbierta createdPregunta;
    public TextField tfTitle;
    public TextField tfSubtitle;
    public Button btnAdd;
    public Button btnCancel;
    public Button btnEnd;

    public void onAddClick(ActionEvent event) {
        if (!tfWords.getText().isBlank()){
            createdPregunta.getRespuestaCorrecta().add(tfWords.getText());
        }
        else {
            Alerta.saltarAlertaError("Agregue algo al campo de texto");
        }
    }

    public void onCancelClick(ActionEvent event) throws IOException {
        main.abrirCreadorPreguntasExamen(profesorLogeado, examen);
    }

    public void onEndButtonCLick(ActionEvent event) {
        if (verificarCampos()){
            createdPregunta.setTitulo(tfTitle.getText());
            createdPregunta.setEnunciado(tfSubtitle.getText());
            createdPregunta.setId(createdPregunta.hashCode());
            try{
                examen.agregarPregunta(createdPregunta);
                singleton.guardarResourceBinario();
                singleton.guardarResourceXML();
                main.abrirCreadorPreguntasExamen(profesorLogeado,examen);
            } catch (PreguntaException e) {
                Alerta.saltarAlertaError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            Alerta.saltarAlertaError("Hay campos vacíos o no ha agregado ninguna palabra clave");
        }
    }

    public void setMain(ExamsApplication examsApplication, Profesor profesorLogeado, Examen examen) {
        this.main = examsApplication;
        this.profesorLogeado = profesorLogeado;
        this.examen = examen;
        createdPregunta = new PreguntaAbierta();
        createdPregunta.setRespuestaCorrecta(new ArrayList<>());
    }

    private boolean verificarCampos(){
        return !tfTitle.getText().isBlank() && !tfSubtitle.getText().isBlank() && !createdPregunta.getRespuestaCorrecta().isEmpty();
    }
}
