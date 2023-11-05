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
import java.util.ArrayList;

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
    ModelFactoryController singleton = ModelFactoryController.getInstance();


    public void onAddClick(ActionEvent event) {
        if (verificarCampos()){
            radioA.setText("A. "+ tfRadioATeXT.getText());
            radioB.setText("B. "+ tfRadioBTeXT.getText());
            radioC.setText("C. "+ tfRadioCTeXT.getText());
            radioD.setText("D. "+ tfRadioDTeXT.getText());
            try{
                Repuesta respuestaCorrecta = createRespuesta();
                PreguntaMultiple pregunta = new PreguntaMultiple(tfTitle.getText(),tfSubtitle.getText(),respuestaCorrecta);
                fillRepuestas(pregunta);
                examenCreado.agregarPregunta(pregunta);
                singleton.guardarResourceXML();
                singleton.guardarResourceBinario();
                main.abrirCreadorPreguntasExamen(profesorLogeado,examenCreado);
            } catch (PreguntaException e) {
                Alerta.saltarAlertaError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            Alerta.saltarAlertaError("HAY CAMPOS VACÍOS O NO HA SELECCIONADO LA RESPUESTA CORRECTA");
        }

    }

    private void fillRepuestas(PreguntaMultiple preguntaMultiple) {
        RadioButton[]radioButtons = {radioA,radioB,radioC,radioD};
        for (int i = 0; i <4; i++) {
            RadioButton myRadio = radioButtons[i];
            preguntaMultiple.getRespuestas().put(String.valueOf(i),new Repuesta(String.valueOf(myRadio.getText().charAt(0)),myRadio.getText().substring(3)));
        }
    }

    private Repuesta createRespuesta() {
        RadioButton selection = findSelected();
        assert selection != null;
        return new Repuesta(String.valueOf(selection.getText().charAt(0)),selection.getText().substring(3));
    }

    private RadioButton findSelected() {
        if (radioA.isSelected()){
            return radioA;
        }
        if (radioB.isSelected()){
            return radioB;
        }
        if (radioC.isSelected()){
            return radioC;
        }
        if (radioD.isSelected()){
            return radioC;
        }
        return null;
    }


    public void onCancelClick(ActionEvent event) throws IOException {
        main.abrirCreadorPreguntasExamen(profesorLogeado, examenCreado);
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
