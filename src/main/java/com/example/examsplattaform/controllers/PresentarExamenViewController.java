package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class PresentarExamenViewController {
    public ScrollPane myScrolPane;
    public Button btnFinalizar;
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Examen examenPresentando;
    Estudiante estudianteLogeado;

    private int preguntaId = 1;
    private int opcionId = 1;


    private void setMyScrolPane() {
        VBox mainVBox = new VBox();
        mainVBox.setSpacing(10); // Espaciado entre las preguntas
        for (Pregunta pregunta : examenPresentando.getPreguntas().values()) {
            VBox preguntaVBox = new VBox();
            preguntaVBox.setSpacing(5); // Espaciado entre los elementos de la pregunta

            if (pregunta instanceof PreguntaMultiple) {
                Label titleLabel = new Label("Título: " + pregunta.getTitulo());
                Text enunciadoText = new Text("Enunciado: " + pregunta.getEnunciado());
                ToggleGroup toggleGroup = new ToggleGroup();
                preguntaVBox.getChildren().addAll(titleLabel, enunciadoText);
                for (Repuesta respuesta : ((PreguntaMultiple) pregunta).getRespuestas().values()) {
                    RadioButton radioButton = new RadioButton(respuesta.getLetra() + ". " + respuesta.getDescripcion());
                    radioButton.setId("opcion" + opcionId);
                    opcionId++;
                    radioButton.setToggleGroup(toggleGroup);
                    preguntaVBox.getChildren().add(radioButton);
                }
            } else if (pregunta instanceof PreguntaAbierta) {
                Label titleLabel = new Label("Título: " + pregunta.getTitulo());
                Text enunciadoText = new Text("Enunciado: " + pregunta.getEnunciado());
                TextArea textArea = new TextArea();
                textArea.setPromptText("Escribe tu respuesta aquí");
                textArea.setWrapText(true);
                preguntaVBox.getChildren().addAll(titleLabel, enunciadoText, textArea);
            } else if (pregunta instanceof PreguntaTF) {
                Label titleLabel = new Label("Título: " + pregunta.getTitulo());
                Text enunciadoText = new Text("Enunciado: " + pregunta.getEnunciado());
                ToggleGroup toggleGroup = new ToggleGroup();
                RadioButton trueRadioButton = new RadioButton("TRUE");
                RadioButton falseRadioButton = new RadioButton("FALSE");
                trueRadioButton.setId("opcion" + opcionId);
                opcionId++;
                falseRadioButton.setId("opcion" + opcionId);
                opcionId++;
                trueRadioButton.setToggleGroup(toggleGroup);
                falseRadioButton.setToggleGroup(toggleGroup);

                preguntaVBox.getChildren().addAll(titleLabel, enunciadoText, trueRadioButton, falseRadioButton);
            }

            preguntaVBox.setId(String.valueOf(preguntaId));
            preguntaId++;
            mainVBox.getChildren().add(preguntaVBox);
        }
        myScrolPane.setContent(mainVBox);
    }

    public void onFinalizarBUttonClick(ActionEvent event) throws IOException {
        int nCorrectas=0;
        int numeroPregunta=1;
        for (Pregunta pregunta: examenPresentando.getPreguntas().values()) {
          if (pregunta instanceof PreguntaMultiple){
              String correcta = ((PreguntaMultiple) pregunta).getCorrecta().getLetra();
              VBox pregntaVbox = (VBox) myScrolPane.getContent().lookup("#"+(numeroPregunta));
              for (Node radio: pregntaVbox.getChildren()) {
                  if (radio instanceof  RadioButton){
                      if (((RadioButton) radio).isSelected()){
                          if (((RadioButton) radio).getText().startsWith(correcta)){
                              nCorrectas=nCorrectas+1;
                              numeroPregunta=numeroPregunta+1;
                          }
                      }
                  }
              }
          } else if (pregunta instanceof  PreguntaAbierta) {
              VBox vBoxPregunta = (VBox) myScrolPane.getContent().lookup("#"+numeroPregunta);
              for (Node node: vBoxPregunta.getChildren()) {
                  if (node instanceof TextArea){
                      if (((PreguntaAbierta) pregunta).getRespuestaCorrecta().contains(((TextArea) node).getText())){
                          nCorrectas = nCorrectas+1;
                          numeroPregunta=numeroPregunta+1;
                      }
                  }
              }
          } else if (pregunta instanceof  PreguntaTF) {
              boolean res = ((PreguntaTF) pregunta).isValue();
              boolean bandera;
              VBox vBoxPregunta = (VBox) myScrolPane.getContent().lookup("#"+numeroPregunta);
              for(Node node: vBoxPregunta.getChildren()){
                  if (node instanceof RadioButton){
                      if (((RadioButton) node).getText().equals("TRUE")){
                          bandera = true;
                          if (bandera==res){
                              nCorrectas=nCorrectas+1;
                              numeroPregunta = numeroPregunta+1;
                          }
                      }
                      if (((RadioButton) node).getText().equals("FALSE")){
                          bandera=false;
                          if (bandera==res){
                              nCorrectas=nCorrectas+1;
                              numeroPregunta = numeroPregunta+1;
                          }
                      }
                  }
              }
          }
        }
        calcularNota(nCorrectas);
    }

    private void calcularNota(int correctas) throws IOException {
        float calificacion = correctas*(examenPresentando.getTotalPuntos()/examenPresentando.getPreguntas().size());
        Alerta.saltarAlertaInformacion("Usted ha sacado "+ calificacion);
        Examen studentExam = examenPresentando;
        studentExam.setNotaEstudiante(calificacion);
        estudianteLogeado.getExamenesRealizados().put(String.valueOf(estudianteLogeado.getExamenesRealizados().size()),studentExam);
        singleton.guardarResourceXML();
        singleton.guardarResourceXML();
        main.abrirPanelEstudiante(estudianteLogeado);
    }


    public void setMain(ExamsApplication examsApplication, Estudiante estudianteLogeado, Examen examenPresentar) {
        this.main = examsApplication;
        this.estudianteLogeado = estudianteLogeado;
        this.examenPresentando = examenPresentar;
        setMyScrolPane();
    }

}
