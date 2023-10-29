package com.example.examsplattaform.model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Objects;

public class PreguntaTF extends Pregunta implements Serializable {
    private Image image;
    private String titulo;
    private String enunciado;
    private int id;
    private final boolean value;

    public PreguntaTF(Image image, String titulo, String enunciado, Image image1, String titulo1, String enunciado1, boolean value) {
        super(image, titulo, enunciado);
        this.image = image1;
        this.titulo = titulo1;
        this.enunciado = enunciado1;
        this.id = hashCode();
        this.value = value;
    }

    public Image getImage() {
        return image;
    }


    public void setImage(Image image) {
        this.image = image;
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
}
