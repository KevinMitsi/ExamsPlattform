package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.exceptions.AccountException;
import com.example.examsplattaform.exceptions.UserNotFoundException;
import com.example.examsplattaform.model.Cuenta;
import com.example.examsplattaform.model.Persona;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginViewController {
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    public PasswordField passwordField;
    public TextField usernameField;


    public void recuperarContrasena(ActionEvent event) {
    }

    public void registerButtonClick(ActionEvent event) throws IOException {
        main.abrirSelector(false,null);
    }

    public void loginButtonAction(ActionEvent event) throws IOException {
        if (verificarCampos(usernameField.getText(), passwordField.getText())){
            Cuenta cuenta = new Cuenta(usernameField.getText(), passwordField.getText());
            try{
                Persona persona = singleton.getPlataforma().iniciarSesion(cuenta);
                main.abrirSelector(true, persona);
            } catch (UserNotFoundException e) {
                Alerta.saltarAlertaError(e.getMessage());
            }
        }
        else{
            Alerta.saltarAlertaError("Hay campos vacíos");
        }
    }

    private boolean verificarCampos(String user, String pass) {
        return !user.isBlank() && !pass.isBlank();
    }

    public void setMain(ExamsApplication examsApplication) {
        main=examsApplication;
    }
}
