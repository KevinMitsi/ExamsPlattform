package com.example.examsplattaform.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PreguntaAbierta extends Pregunta implements Serializable {
    private String titulo;
    private String enunciado;
    private int id;
    private List<String> respuestaCorrecta;


    public PreguntaAbierta(String titulo1, String enunciado1) {
        this.titulo = titulo1;
        this.enunciado = enunciado1;
        this.id = hashCode();
        this.respuestaCorrecta = new ArrayList<>();
    }

    public PreguntaAbierta() {
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

    public List<String> getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(List<String> respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreguntaAbierta that)) return false;
        return getId() == that.getId() && Objects.equals(getTitulo(), that.getTitulo()) && Objects.equals(getEnunciado(), that.getEnunciado()) && Objects.equals(getRespuestaCorrecta(), that.getRespuestaCorrecta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitulo(), getEnunciado(), getId(), getRespuestaCorrecta());
    }
    public boolean esRespuestaCorrecta(String respuestaUsuario) {
        return respuestaCorrecta.contains(respuestaUsuario);
    }

}
