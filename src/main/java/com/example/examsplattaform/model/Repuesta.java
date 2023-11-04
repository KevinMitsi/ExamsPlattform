package com.example.examsplattaform.model;

import java.io.Serializable;

public class Repuesta implements Serializable {
    private String letra;
    private String descripcion;

    public Repuesta(String letra, String descripcion) {
        this.letra = letra;
        this.descripcion = descripcion;
    }

    public Repuesta() {
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
