package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.exceptions.PreguntaException;
import com.example.examsplattaform.model.Examen;
import com.example.examsplattaform.model.PreguntaMultiple;
import com.example.examsplattaform.model.Profesor;
import com.example.examsplattaform.model.Repuesta;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;

public class NewQuestionViewController {

    public TextField tfTitle;
    public TextField tfSubtitle;
    public RadioButton radioA;
    public ToggleGroup answers;
    public RadioButton radioB;
    public RadioButton radioC;
    public RadioButton radioD;
    public TextField tfRadioATeXT;
    public TextField tfRadioDTeXT;
    public TextField tfRadioBTeXT;
    public TextField tfRadioCTeXT;
    public Button btnAdd;
    public Button btnCancel;
    Profesor profesorLogeado;
    Examen examenCreado;
    ExamsApplication main;
    ModelFactoryController singleon = ModelFactoryController.getInstance();


    public void onAddClick(ActionEvent event) {
        if (verificarCampos()){
            try{
                Repuesta respuesta = createRespuesta();
                PreguntaMultiple pregunta = new PreguntaMultiple(tfTitle.getText(),tfSubtitle.getText(),respuesta);
                examenCreado.agregarPregunta(pregunta);
                main.abrirCreadorPreguntasExamen(profesorLogeado,examenCreado);
            } catch (PreguntaException e) {
                Alerta.saltarAlertaError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            Alerta.saltarAlertaError("HAY CAMPOS VACÍOS");
        }

    }

    private Repuesta createRespuesta() {
        RadioButton selection = findSelected();
        assert selection != null;
        return new Repuesta(String.valueOf(selection.getText().charAt(0)),selection.getText().substring(3));
    }

    private RadioButton findSelected() {
        if (radioA.isSelected()){
            radioA.setText("A. "+ tfRadioATeXT.getText());
            return radioA;
        }
        if (radioB.isSelected()){
            radioA.setText("B. "+ tfRadioBTeXT.getText());
            return radioB;
        }
        if (radioC.isSelected()){
            radioA.setText("C. "+ tfRadioATeXT.getText());
            return radioC;
        }
        if (radioD.isSelected()){
            radioA.setText("D. "+ tfRadioATeXT.getText());
            return radioC;
        }
        return null;
    }


    public void onCancelClick(ActionEvent event) {
    }

    public void setMain(ExamsApplication main, Profesor profesorLogeado, Examen examenCreado){
        this.main= main;
        this.profesorLogeado = profesorLogeado;
        this.examenCreado = examenCreado;
    }
    private boolean verificarCampos() {
        return !tfTitle.getText().isBlank() && !tfSubtitle.getText().isBlank() && !tfRadioATeXT.getText().isBlank() && !tfRadioBTeXT.getText().isBlank() &&
                !tfRadioCTeXT.getText().isBlank() && !tfRadioDTeXT.getText().isBlank() && (radioA.isSelected()||radioB.isSelected()||radioC.isSelected()||radioD.isSelected());
    }
}
