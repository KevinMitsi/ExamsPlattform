package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.exceptions.NonAnswerSelectedException;
import com.example.examsplattaform.exceptions.PreguntaException;
import com.example.examsplattaform.model.Examen;
import com.example.examsplattaform.model.PreguntaTF;
import com.example.examsplattaform.model.Profesor;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class NewTFQuestionViewController {
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Profesor profesorLogeado;
    Examen examenCreado;
    public ToggleGroup answers;
    public TextField tfTitle;
    public TextField tfSubtitle;
    public RadioButton radioT;
    public RadioButton radioF;
    public Button btnAdd;
    public Button btnCancel;

    public void onAddClick(ActionEvent event) {
        if (verificarCampos()){
            try{
                PreguntaTF preguntaTF = new PreguntaTF(tfTitle.getText(),tfSubtitle.getText(),getAnswerValue());
                examenCreado.agregarPregunta(preguntaTF);
                singleton.guardarResourceXML();
                singleton.guardarResourceBinario();
                main.abrirCreadorPreguntasExamen(profesorLogeado,examenCreado);
            } catch (PreguntaException | NonAnswerSelectedException | IOException e) {
                Alerta.saltarAlertaError(e.getMessage());
            }
        }
        else{
            Alerta.saltarAlertaError("Hay campos vacíos o no ha seleccionado la respuesta correcta");
        }
    }

    private boolean getAnswerValue() throws NonAnswerSelectedException {
        if (radioT.isSelected()){
            return  true;
        }
        if (radioF.isSelected()){
            return  false;
        }
        throw new NonAnswerSelectedException("No se ha seleccionado una opción");
    }

    public void onCancelClick(ActionEvent event) throws IOException {
        main.abrirCreadorPreguntasExamen(profesorLogeado, examenCreado);
    }

    public void setMain(ExamsApplication examsApplication, Profesor profesorLogeado, Examen examen) {
        this.main = examsApplication;
        this.examenCreado = examen;
        this.profesorLogeado=profesorLogeado;
    }

    private boolean verificarCampos(){
        return !tfTitle.getText().isBlank() && !tfSubtitle.getText().isBlank() && (radioT.isSelected()||radioF.isSelected());
    }
}
