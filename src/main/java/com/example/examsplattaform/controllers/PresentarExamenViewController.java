package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

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

            preguntaVBox.setId("pregunta" + preguntaId);
            preguntaId++;

            mainVBox.getChildren().add(preguntaVBox);
        }
        myScrolPane.setContent(mainVBox);
    }

    public void onFinalizarBUttonClick(ActionEvent event) {
        Map<String, String> respuestasUsuario = new HashMap<>();
         for (Node node : myScrolPane.getContent().lookupAll(".VBox")) {
            if (node instanceof VBox) {
                VBox preguntaVBox = (VBox) node;
                String preguntaId = preguntaVBox.getId();

                // Verifica si la pregunta es de tipo PreguntaAbierta
                if (preguntas.containsKey(preguntaId) && preguntas.get(preguntaId) instanceof PreguntaAbierta) {
                    TextArea textArea = preguntaVBox.lookup(".TextArea");
                    String respuestaUsuario = textArea.getText();
                    respuestasUsuario.put(preguntaId, respuestaUsuario);
                }
                // Verifica si la pregunta es de tipo PreguntaMultiple
                else if (preguntas.containsKey(preguntaId) && preguntas.get(preguntaId) instanceof PreguntaMultiple) {
                    ToggleGroup toggleGroup = preguntaVBox.lookup(".ToggleGroup");
                    RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
                    if (selectedRadioButton != null) {
                        String opcionSeleccionada = selectedRadioButton.getId();
                        respuestasUsuario.put(preguntaId, opcionSeleccionada);
                    }
                }
                // Verifica si la pregunta es de tipo PreguntaTF
                else if (preguntas.containsKey(preguntaId) && preguntas.get(preguntaId) instanceof PreguntaTF) {
                    ToggleGroup toggleGroup = preguntaVBox.lookup(".ToggleGroup");
                    RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
                    if (selectedRadioButton != null) {
                        boolean respuestaUsuario = Boolean.parseBoolean(selectedRadioButton.getText());
                        respuestasUsuario.put(preguntaId, String.valueOf(respuestaUsuario));
                    }
                }
            }
        }

        // Llama al método de la clase Examen para evaluar las respuestas del usuario
        float notaEstudiante = examenPresentando.evaluarRespuestas(respuestasUsuario);

        // Haz lo que desees con la nota del estudiante (por ejemplo, mostrarla en un mensaje)
        System.out.println("Nota del estudiante: " + notaEstudiante);
    }



    public void setMain(ExamsApplication examsApplication, Estudiante estudianteLogeado, Examen examenPresentar) {
        this.main = examsApplication;
        this.estudianteLogeado = estudianteLogeado;
        this.examenPresentando = examenPresentar;
        setMyScrolPane();
    }
}
