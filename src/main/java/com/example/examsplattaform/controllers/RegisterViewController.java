package com.example.examsplattaform.controllers;

import com.example.examsplattaform.ExamsApplication;
import com.example.examsplattaform.exceptions.AccountException;
import com.example.examsplattaform.exceptions.AlreadyRegisteredUserException;
import com.example.examsplattaform.model.Cuenta;
import com.example.examsplattaform.model.Profesor;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterViewController {
    ExamsApplication main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    public TextField tfName;
    public TextField tfId;
    public TextField tfApellido;
    public TextField tfCollege;
    public TextField tfUser;
    public PasswordField pfPassword;
    public Hyperlink hlBack;
    public Button btnRegister;

    public void setMain(ExamsApplication examsApplication) {
        this.main=examsApplication;
    }

    public void onBackLinkClick(ActionEvent event) throws IOException {
        main.inicilizarLogin();
    }

    public void onRegisterButtonClick(ActionEvent event) {
        if (verificarCampos()){
            try{
                Profesor profesor  = new Profesor(tfName.getText(), tfApellido.getText(), tfCollege.getText(),tfId.getText());
                profesor.setCuenta(new Cuenta(tfUser.getText(),pfPassword.getText()));
                singleton.getPlataforma().registrarProfesor(profesor);
                singleton.guardarResourceXML();
                singleton.guardarResourceBinario();
                main.inicilizarLogin();
            } catch (AccountException | AlreadyRegisteredUserException e) {
                Alerta.saltarAlertaError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            Alerta.saltarAlertaError("Veriique los campos vacíos");
        }
    }

    private boolean verificarCampos() {
        return !tfName.getText().isBlank() && !tfApellido.getText().isBlank() && !tfId.getText().isBlank() && !tfCollege.getText().isBlank() && !tfUser.getText().isBlank() && !pfPassword.getText().isBlank();
    }
}
