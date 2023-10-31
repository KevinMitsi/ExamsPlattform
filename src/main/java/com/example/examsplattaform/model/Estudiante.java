package com.example.examsplattaform.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Estudiante extends Persona implements Serializable {
    private String nombre;
    private String apellido;
    private String cedula;
    private Map<String, Examen>examenesRealizados;
    private Cuenta cuenta;

    public Estudiante(String nombre, String apellido, String cedula, Cuenta cuenta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.examenesRealizados = new HashMap<>();
        this.cuenta=cuenta;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Map<String, Examen> getExamenesRealizados() {
        return examenesRealizados;
    }

    public void setExamenesRealizados(Map<String, Examen> examenesRealizados) {
        this.examenesRealizados = examenesRealizados;
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
        if (!(o instanceof Estudiante that)) return false;
        return Objects.equals(getCedula(), that.getCedula());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCedula());
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cedula='" + cedula + '\'' +
                '}';
    }
}
