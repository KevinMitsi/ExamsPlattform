package com.example.examsplattaform.model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PreguntaMultiple extends Pregunta implements Serializable {
   private String titulo;
    private String enunciado;
    private Map<String,Repuesta> respuestas;
    private int id;
    private Repuesta correcta;

    public PreguntaMultiple(String titulo, String enunciado, Repuesta correcta) {
        super(titulo, enunciado);
       this.respuestas = new HashMap<>();
        this.id = hashCode();
        this.correcta = correcta;
    }

    public PreguntaMultiple() {
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

    public Map<String, Repuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Map<String, Repuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Repuesta getCorrecta() {
        return correcta;
    }

    public void setCorrecta(Repuesta correcta) {
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
