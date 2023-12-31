package com.example.examsplattaform.model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Objects;

public abstract class Pregunta implements Serializable {
    private  String titulo;
    private  String enunciado;

    public Pregunta(String titulo, String enunciado) {
        this.titulo = titulo;
        this.enunciado = enunciado;
    }

    public Pregunta() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pregunta pregunta)) return false;
        return Objects.equals(getTitulo(), pregunta.getTitulo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitulo());
    }
}
