package com.example.examsplattaform.model;

import java.io.Serializable;
import java.util.Objects;

public class Cuenta implements Serializable {
    private String usuario;
    private String password;
    private Persona personaAsociada;

    public Cuenta(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public Cuenta(){
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Persona getPersonaAsociada() {
        return personaAsociada;
    }

    public void setPersonaAsociada(Persona personaAsociada) {
        this.personaAsociada = personaAsociada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cuenta cuenta)) return false;
        return Objects.equals(getUsuario(), cuenta.getUsuario()) && Objects.equals(getPassword(), cuenta.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsuario(), getPassword());
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", personaAsociada=" + personaAsociada +
                '}';
    }
}
