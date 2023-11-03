package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.exceptions.AccountException;
import com.example.examsplattaform.exceptions.AlreadyRegisteredUserException;
import com.example.examsplattaform.model.Cuenta;
import com.example.examsplattaform.model.Estudiante;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterStudentViewController {
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    public TextField tfName;
    public TextField tfId;
    public TextField tfApellido;
    public TextField tfUser;
    public PasswordField pfPassword;
    public Hyperlink hlBack;
    public Button btnRegister;

    public void setMain(ExamsApplication examsApplication) {
        this.main = examsApplication;
    }

    public void onBackLinkClick(ActionEvent event) throws IOException {
        main.inicilizarLogin();
    }

    public void onRegisterButtonClick(ActionEvent event) {
        if (verificarCampos()){
            try{
                Cuenta cuenta = new Cuenta(tfUser.getText(), pfPassword.getText());
                Estudiante estudiante = new Estudiante(tfName.getText(), tfApellido.getText(),tfId.getText(),cuenta);
                cuenta.setPersonaAsociada(estudiante);
                singleton.getPlataforma().registrarEstudiante(estudiante);
                singleton.guardarResourceXML();
                singleton.guardarResourceBinario();
                Alerta.saltarAlertaInformacion("Se ha registrado exitosamente en la aplicación");
                main.inicilizarLogin();
            } catch (AccountException | AlreadyRegisteredUserException e) {
                Alerta.saltarAlertaError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            Alerta.saltarAlertaError("Verifique que hay campos vacíos");
        }
    }

    private boolean verificarCampos() {
        return !tfName.getText().isBlank() && !tfApellido.getText().isBlank() && !tfId.getText().isBlank() && !tfUser.getText().isBlank() && !pfPassword.getText().isBlank();
    }


}

