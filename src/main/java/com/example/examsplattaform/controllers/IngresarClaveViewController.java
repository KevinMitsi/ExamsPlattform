package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.exceptions.ExamenNotFoundException;
import com.example.examsplattaform.model.Estudiante;
import com.example.examsplattaform.model.Examen;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class IngresarClaveViewController {
    ExamsApplication main;
    Estudiante estudianteLogeado;
    public Button btnGo;
    public TextField tfCode;
    public Button btnBack;

    public void onGoButtonClick(ActionEvent event) {
        if(!tfCode.getText().isBlank()){
            try{
                Examen examenPresentar=estudianteLogeado.presentarExamen(tfCode.getText());
                main.abrirPresentarExamen(estudianteLogeado,examenPresentar);
            } catch (ExamenNotFoundException e) {
                Alerta.saltarAlertaError(e.getMessage());
            }
        }
        else{
            Alerta.saltarAlertaError("No ha ingresad ningún código");
        }
    }

    public void onBackButtonClick(ActionEvent event) throws IOException {
        main.abrirPanelEstudiante(estudianteLogeado);
    }

    public void setMain(ExamsApplication examsApplication, Estudiante estudianteLogeado) {
        main = examsApplication;
        this.estudianteLogeado = estudianteLogeado;
    }
}
