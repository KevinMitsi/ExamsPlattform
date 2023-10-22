package com.example.examsplattaform.model;

public class Repuesta extends AbstractRespuesta{
    private final String letra;

    public Repuesta(String letra) {
        this.letra = letra;
    }

    public String getLetra() {
        return letra;
    }
}
