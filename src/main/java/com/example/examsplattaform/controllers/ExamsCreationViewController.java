package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.exceptions.ExamCreationException;
import com.example.examsplattaform.model.Examen;
import com.example.examsplattaform.model.Profesor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ExamsCreationViewController {
    @FXML
    public TextField tfTittle;
    @FXML
    public TextField tfSubtittle;
    @FXML
    public TextField tfSubject;
    @FXML
    public TextField tfMaxGrade;
    @FXML
    public Button btnCreate;
    @FXML
    public Hyperlink hlBack;
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Profesor profesorLogeado;


    @FXML
    public void onCrearClick(ActionEvent event) throws IOException {
        if(verificarCampos()){
            Examen examen = new Examen(tfTittle.getText(),tfSubtittle.getText(),tfSubject.getText(),Float.parseFloat(tfMaxGrade.getText()));
            String clave = String.valueOf((int) (Math.random() * 500) +examen.getId());
            examen.setClave(clave);
             try{
             singleton.getPlataforma().crearExamen(profesorLogeado, examen);
             singleton.guardarResourceXML();
             singleton.guardarResourceBinario();
             main.abrirCreadorPreguntasExamen(profesorLogeado,examen);
             } catch (ExamCreationException e) {
                Alerta.saltarAlertaError(e.getMessage());
            }
        }
        else{
            Alerta.saltarAlertaError("Hay campos vacíos o está intentando colocar una nota muy baja");
        }
    }



    @FXML
    public void onBackClicked(ActionEvent event) throws IOException {
        main.abrirPanelProfesor(profesorLogeado);
    }

    public void setMain(ExamsApplication main, Profesor profesorLogeado){
        this.main = main;
        this.profesorLogeado = profesorLogeado;
    }

    private boolean verificarCampos() {
        return !tfTittle.getText().isBlank() && !tfSubtittle.getText().isBlank() && !tfSubject.getText().isBlank() && !tfMaxGrade.getText().isBlank() && (Integer.parseInt(tfMaxGrade.getText())>=5);
    }
}
