package com.example.examsplattaform.model;

import javafx.scene.image.Image;

public abstract class Pregunta {
    private final Image image;
    private final String titulo;
    private final String enunciado;

    public Pregunta(Image image, String titulo, String enunciado) {
        this.image = image;
        this.titulo = titulo;
        this.enunciado = enunciado;
    }


}
