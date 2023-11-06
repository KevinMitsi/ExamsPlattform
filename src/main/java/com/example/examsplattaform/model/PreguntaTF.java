package com.example.examsplattaform.model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Objects;

public class PreguntaTF extends Pregunta implements Serializable {
    private String titulo;
    private String enunciado;
    private int id;
    private boolean value;

    public PreguntaTF(String titulo, String enunciado, boolean value) {
        this.titulo =titulo;
        this.enunciado=enunciado;
        this.id = hashCode();
        this.value = value;
    }

    public PreguntaTF() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreguntaTF that)) return false;
        return getId() == that.getId() && isValue() == that.isValue() && Objects.equals(getTitulo(), that.getTitulo()) && Objects.equals(getEnunciado(), that.getEnunciado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitulo(), getEnunciado(), getId(), isValue());
    }
    public boolean esRespuestaCorrecta(boolean respuestaUsuario) {
        return value == respuestaUsuario;
    }

}
