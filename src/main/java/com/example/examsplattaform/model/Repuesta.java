package com.example.examsplattaform.model;

import java.io.Serializable;

public class Repuesta extends AbstractRespuesta implements Serializable {
    private String letra;

    public Repuesta(String letra) {
        this.letra = letra;
    }

    public Repuesta() {
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
}
