package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.Examen;
import com.example.examsplattaform.model.Profesor;

public class VisualizarExamenProfesorViewController {
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Profesor profesorLogeado;
    Examen examenSeleccionado;

    public void setMain(ExamsApplication examsApplication, Profesor profesorLogeado, Examen examenSeleccionado) {
        main=examsApplication;
        this.profesorLogeado=profesorLogeado;
        this.examenSeleccionado=examenSeleccionado;
    }
}
