package com.example.examsplattaform.model;

import com.example.examsplattaform.exceptions.PreguntaException;
import javafx.scene.layout.VBox;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Examen implements Serializable {
    private int id;
    private String titulo;
    private String subtitulo;
    private String materia;
    private String clave;
    private Map<String, Pregunta>preguntas;
    private int numeroPresentados;
    private float totalPuntos;
    private float puntoXPregunta;

    VBox container;

    public Examen(String titulo, String subtitulo, String materia,float totalPuntos) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.totalPuntos = totalPuntos;
        this.materia=materia;
        preguntas = new HashMap<>();
        this.id=hashCode();
    }

    public Examen() {
        preguntas= new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Map<String, Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Map<String, Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public float getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(float totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    public float getPuntoXPregunta() {
        return puntoXPregunta;
    }

    public void setPuntoXPregunta(float puntoXPregunta) {
        this.puntoXPregunta = puntoXPregunta;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getNumeroPresentados() {
        return numeroPresentados;
    }

    public void setNumeroPresentados(int numeroPresentados) {
        this.numeroPresentados = numeroPresentados;
    }

    public VBox getContainer() {
        return container;
    }

    public void setContainer(VBox container) {
        this.container = container;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Examen examen)) return false;
        return getId() == examen.getId() && Objects.equals(getTitulo(), examen.getTitulo()) && Objects.equals(getClave(), examen.getClave()) && Objects.equals(getPreguntas(), examen.getPreguntas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitulo(), getClave(), getPreguntas());
    }

    public void agregarPregunta(Pregunta pregunta) throws PreguntaException {
        if (preguntas.containsValue(pregunta)){
            throw new PreguntaException("Esta pregunta ya está creada");
        }
        else{
            preguntas.put(String.valueOf(preguntas.size()),pregunta);
        }
    }
}
