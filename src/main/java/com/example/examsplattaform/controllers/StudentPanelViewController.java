package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.Estudiante;
import com.example.examsplattaform.model.Examen;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Iterator;

public class StudentPanelViewController {
    ObservableMap<String, Examen>examenObservableMap;
    ObservableList<Examen>examenObservableList;
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
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
    public void onNewExamClick(ActionEvent event) {
    }
    @FXML
    public void onDragSelecttedItem(MouseEvent mouseEvent) {
    }

    @FXML
    void initialize(){
        colTirulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colMateria.setCellValueFactory((cellData -> new SimpleStringProperty(cellData.getValue().getMateria())));
        colNota.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotalPuntos()));
        tableExams.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            examenSeleccionado = newSelection;
        });

        tableExams.setOnMouseClicked(mouseEvent -> {
           if (examenSeleccionado!=null){
               main.expandirExamenSeleccionado(examenSeleccionado,estudianteLogeado, null);
           }
           else{
               Alerta.saltarAlertaError("Seleccione un elemento para expandir");
           }
        });

    }

    private void fillObservableList() {
        examenObservableMap.putAll(estudianteLogeado.getExamenesRealizados());
        examenObservableList.addAll(examenObservableMap.values());
        tableExams.getItems().clear();
        tableExams.getItems().addAll(examenObservableList);
    }
}
