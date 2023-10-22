package com.example.examsplattaform.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Profesor extends Persona{
    private String nombre;
    private String apellido;
    private String colegio;
    private String cedula;
    private Cuenta cuenta;

    private List<Examen>examenesCreados;
    private List<Examen>resueltos;

    public Profesor(String nombre, String apellido, String colegio, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.colegio = colegio;
        this.cedula = cedula;
        examenesCreados= new ArrayList<>();
        resueltos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesor profesor)) return false;
        return Objects.equals(getNombre(), profesor.getNombre()) && Objects.equals(getCedula(), profesor.getCedula());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getCedula());
    }

    public List<Examen> getExamenesCreados() {
        return examenesCreados;
    }

    public void setExamenesCreados(List<Examen> examenesCreados) {
        this.examenesCreados = examenesCreados;
    }

    public List<Examen> getResueltos() {
        return resueltos;
    }

    public void setResueltos(List<Examen> resueltos) {
        this.resueltos = resueltos;
    }
}
