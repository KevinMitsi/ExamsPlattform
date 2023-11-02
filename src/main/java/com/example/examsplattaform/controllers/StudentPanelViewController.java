package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.Estudiante;

public class StudentPanelViewController {
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Estudiante estudianteLogeado;
    public void setMain(ExamsApplication examsApplication, Estudiante estudianteLogeado) {
        this.main = examsApplication;
        this.estudianteLogeado = estudianteLogeado;
    }
}
