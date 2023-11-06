package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.Examen;
import com.example.examsplattaform.model.Profesor;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
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
        myScrollPane.setContent(examenSeleccionado.getContainer());
    }

    public void onVolverButtonClick(ActionEvent event) throws IOException {
        main.abrirPanelProfesor(profesorLogeado);
    }

    public void onCompartirButtonClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        main.mostrarClave(examenSeleccionado);
        main.abrirPanelProfesor(profesorLogeado);
    }

}
