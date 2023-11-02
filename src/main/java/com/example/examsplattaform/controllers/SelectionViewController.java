package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.model.Estudiante;
import com.example.examsplattaform.model.Persona;
import com.example.examsplattaform.model.Profesor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import java.io.IOException;

public class SelectionViewController {
    public Text txtWelcome;
    public Text txtWelcomeRegister;
    ExamsApplication main;
    ModelFactoryController sinlgeton = ModelFactoryController.getInstance();
    Persona personaLogeada;
    public ComboBox<String> cbOptions;
    public Button btnContinue;
    public boolean isLogin;



    @FXML
    void initialize(){
        cbOptions.getItems().add("Profesor");
        cbOptions.getItems().add("Estudiante");
    }

    public void onContinueButtonClick(ActionEvent event) throws IOException {
        if(cbOptions.getValue()==null){
            Alerta.saltarAlertaError("Seleccione una opición");
        }
        else {
            if(!isLogin){
                if (cbOptions.getValue().equals("Profesor")){
                    main.abrirRegistrarProfesor();
                }
                if (cbOptions.getValue().equals("Estudiante")){
                    main.abrirRegistrarUsuario();
                }
            }
            if(isLogin){
                if (cbOptions.getValue().equals("Profesor")&&personaLogeada instanceof Profesor){
                    main.abrirPanelProfesor((Profesor) personaLogeada);
                }
                if (cbOptions.getValue().equals("Estudiante")&& personaLogeada instanceof Estudiante){
                    main.abrirPanelEstudiante((Estudiante) personaLogeada);
                }
                else{
                    Alerta.saltarAlertaError("Está intentando ingresar con un acceso diferente al suyo");
                }
            }
        }
    }

    public void setMain(ExamsApplication main, boolean isLogin, Persona personaLogeada){
        this.main=main;
        this.isLogin=isLogin;
        this.personaLogeada = personaLogeada;
        if (this.isLogin){
            txtWelcome.setVisible(true);
        }
        if (!this.isLogin){
            txtWelcomeRegister.setVisible(true);
        }
    }
}
