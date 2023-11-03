package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.Estudiante;
import com.example.examsplattaform.model.Profesor;

public class ExamZoomViewController{
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();

    Estudiante estudianteLogeado;
    Profesor profesorLogeado;

    public void setMain(ExamsApplication main, Estudiante estudianteLogeado, Profesor profesorLogeado){
        this.main=main;
        this.estudianteLogeado = estudianteLogeado;
        this.profesorLogeado = profesorLogeado;
    }

}
