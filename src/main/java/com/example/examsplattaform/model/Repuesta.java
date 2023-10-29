package com.example.examsplattaform.model;

import java.io.Serializable;

public class Repuesta extends AbstractRespuesta implements Serializable {
    private final String letra;

    public Repuesta(String letra) {
        this.letra = letra;
    }

    public String getLetra() {
        return letra;
    }
}
