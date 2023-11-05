package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.Examen;
import com.example.examsplattaform.model.Profesor;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ProfesorPanelViewController {
    public Hyperlink hlVolver;
    public TableView<Examen> tableExams;
    public TableColumn<Examen,String> colNombreExam;
    public TableColumn<Examen,String> colMateriaExam;
    public TableColumn<Examen,Integer> colNumberPresentados;
    public Button btnNewExamen;
    ObservableList<Examen> examsObservables;
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Profesor profesorLogeado;
    Examen examenSeleccionado;
    public void setMain(ExamsApplication examsApplication, Profesor profesorLogeado) {
        this.main = examsApplication;
        this.profesorLogeado = profesorLogeado;
        fillTable();
    }

    private void fillTable() {
        examsObservables = FXCollections.observableArrayList();
        examsObservables.addAll(profesorLogeado.getExamenesCreados());
        tableExams.getItems().clear();
        tableExams.getItems().addAll(examsObservables);
    }

    public void onVolverLinkClick(ActionEvent event) throws IOException {
        main.inicilizarLogin();
    }

    public void onDragItemTable(MouseEvent mouseEvent) throws IOException {
        if(examenSeleccionado!=null){
            main.abrirVisualizarExamenProfesor(profesorLogeado,examenSeleccionado);
        }
    }

    public void onNewExamenClick(ActionEvent event) throws IOException {
        main.abrirCreadorExamenes(profesorLogeado);
    }

    @FXML
    void initialize(){
        colNombreExam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colMateriaExam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMateria()));
        colNumberPresentados.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNumeroPresentados()));
        tableExams.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            examenSeleccionado = newSelection;
        });
    }



}
