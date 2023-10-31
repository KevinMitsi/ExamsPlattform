package com.example.examsplattaform.model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PreguntaMultiple extends Pregunta implements Serializable {
    private Image image;
    private String titulo;
    private String enunciado;
    private Map<String,AbstractRespuesta> respuestas;
    private int id;
    private AbstractRespuesta correcta;

    public PreguntaMultiple(Image image, String titulo, String enunciado, Image image1, String titulo1, String enunciado1, AbstractRespuesta correcta) {
        super(image, titulo, enunciado);
        this.image = image1;
        this.titulo = titulo1;
        this.enunciado = enunciado1;
        this.respuestas = new HashMap<>();
        this.id = hashCode();
        this.correcta = correcta;
    }

    public PreguntaMultiple() {
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

    public Map<String, AbstractRespuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Map<String, AbstractRespuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AbstractRespuesta getCorrecta() {
        return correcta;
    }

    public void setCorrecta(AbstractRespuesta correcta) {
        this.correcta = correcta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreguntaMultiple that)) return false;
        return getId() == that.getId() && Objects.equals(getTitulo(), that.getTitulo()) && Objects.equals(getEnunciado(), that.getEnunciado()) && Objects.equals(getRespuestas(), that.getRespuestas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitulo(), getEnunciado(), getRespuestas(), getId());
    }
}
