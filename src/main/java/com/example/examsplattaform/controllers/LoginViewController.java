package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
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
        main.abrirRegistrarUsuario();
    }

    public void loginButtonAction(ActionEvent event) {
    }

    public void setMain(ExamsApplication examsApplication) {
        main=examsApplication;
    }
}
