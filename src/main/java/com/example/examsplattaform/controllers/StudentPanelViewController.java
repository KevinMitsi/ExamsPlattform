package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.Estudiante;
import com.example.examsplattaform.model.Examen;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.HashMap;


public class StudentPanelViewController {
    ObservableMap<String, Examen>examenObservableMap;
    ObservableList<Examen>examenObservableList;
    ExamsApplication main;
   Estudiante estudianteLogeado;
    Examen examenSeleccionado = null;
    public TableView<Examen> tableExams;
    public Button btnNewExam;
    public TableColumn<Examen, String> colTirulo;
    public TableColumn<Examen, String> colMateria;
    public TableColumn<Examen, Float> colNota;

    public void setMain(ExamsApplication examsApplication, Estudiante estudianteLogeado) {
        this.main = examsApplication;
        this.estudianteLogeado = estudianteLogeado;
        fillObservableList();
    }


    @FXML
    public void onNewExamClick(ActionEvent event) throws IOException {
        main.mostrarIngresarNuevoExamen(estudianteLogeado);
    }
    @FXML
    public void onDragSelecttedItem(MouseEvent mouseEvent) throws IOException {
            main.abrirVisualizarExamenEstudiante(estudianteLogeado, examenSeleccionado);
    }

    @FXML
    void initialize(){
        colTirulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colMateria.setCellValueFactory((cellData -> new SimpleStringProperty(cellData.getValue().getMateria())));
        colNota.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotalPuntos()));
        tableExams.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            examenSeleccionado = newSelection;
        });
    }

    private void fillObservableList() {
        if (estudianteLogeado.getExamenesRealizados()==null){
            estudianteLogeado.setExamenesRealizados(new HashMap<>());
        }
        examenObservableMap = FXCollections.observableMap(estudianteLogeado.getExamenesRealizados());
        examenObservableList = FXCollections.observableArrayList();
        examenObservableMap.putAll(estudianteLogeado.getExamenesRealizados());
        examenObservableList.addAll(examenObservableMap.values());
        tableExams.getItems().clear();
        tableExams.getItems().addAll(examenObservableList);
    }
}
