package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.Examen;
import com.example.examsplattaform.model.Profesor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

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
    public void setMain(ExamsApplication main, Profesor profesorLogeado){
        this.main = main;
        this.profesorLogeado = profesorLogeado;
    }

    @FXML
    public void onCrearClick(ActionEvent event) {

    }
    @FXML
    public void onBackClicked(ActionEvent event) {

    }
}
