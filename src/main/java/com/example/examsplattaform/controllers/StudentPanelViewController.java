package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.Estudiante;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class StudentPanelViewController {
    public TableView tableExams;
    public Button btnNewExam;
    public TableColumn colTirulo;
    public TableColumn colMateria;
    public TableColumn colNota;
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Estudiante estudianteLogeado;
    public void setMain(ExamsApplication examsApplication, Estudiante estudianteLogeado) {
        this.main = examsApplication;
        this.estudianteLogeado = estudianteLogeado;
    }

    public void onNewExamClick(ActionEvent event) {
    }

    public void onDragSelecttedItem(MouseEvent mouseEvent) {
    }
}
