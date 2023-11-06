package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.Estudiante;
import com.example.examsplattaform.model.Examen;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class VisualizarExamenStudentViewController {
    public Label lblTitulo;
    public Label lblNota;
    public Button btnRegresar;
    ExamsApplication main;
    Examen examenSeleccionado;
    Estudiante estudianteLogeado;
    public void setMain(ExamsApplication examsApplication, Estudiante estudianteLogeado, Examen examenSeleccionado) {
        main = examsApplication;
        this.estudianteLogeado = estudianteLogeado;
        this.examenSeleccionado = examenSeleccionado;
        lblTitulo.setText(examenSeleccionado.getTitulo());
        lblNota.setText(String.valueOf(examenSeleccionado.getNotaEstudiante()));
    }

    public void onRregresarClick(ActionEvent event) throws IOException {
        main.abrirPanelEstudiante(estudianteLogeado);
    }
}
