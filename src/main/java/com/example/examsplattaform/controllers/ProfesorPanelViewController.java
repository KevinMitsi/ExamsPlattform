package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.Profesor;

public class ProfesorPanelViewController {
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Profesor profesorLogeado;
    public void setMain(ExamsApplication examsApplication, Profesor profesorLogeado) {
        this.main = examsApplication;
        this.profesorLogeado = profesorLogeado;
    }
}
