package com.example.examsplattaform.model;

import com.example.examsplattaform.exceptions.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plataforma implements Serializable {

    //----------------------------------Attributes------------------------------------------
    private String nombre;
    private String id;
    private List<Profesor> profesresRegistrados;
    private List<Estudiante> estudiantesRegistrados;
    private List<Cuenta> cuentaList;
    private Map<String,Examen> examenList;


    //-----------------------Constructors------------------------------------------------
    public Plataforma(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        profesresRegistrados = new ArrayList<>();
        estudiantesRegistrados = new ArrayList<>();
        cuentaList = new ArrayList<>();
        examenList = new HashMap<>();
    }

    public Plataforma(){
        profesresRegistrados = new ArrayList<>();
        estudiantesRegistrados = new ArrayList<>();
        cuentaList=new ArrayList<>();
        examenList = new HashMap<>();
    }

    //-----------Getters && Setters -----------------------\\

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Profesor> getProfesresRegistrados() {
        return profesresRegistrados;
    }

    public void setProfesresRegistrados(List<Profesor> profesresRegistrados) {
        this.profesresRegistrados = profesresRegistrados;
    }

    public List<Estudiante> getEstudiantesRegistrados() {
        return estudiantesRegistrados;
    }

    public void setEstudiantesRegistrados(List<Estudiante> estudiantesRegistrados) {
        this.estudiantesRegistrados = estudiantesRegistrados;
    }

    public Map<String, Examen> getExamenList() {
        return examenList;
    }

    public void setExamenList(Map<String, Examen> examenList) {
        this.examenList = examenList;
    }

    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }

    //---------- Public Methods of the App --------------------------------\\

    /**
     * This method allows users to join the application
     * @param usuario represents the user of the account that will try to join into de app
     * @param contrasena represents the password of the account
     * @return every account has an assigned person, this return will allow the application open the right window of every user*
     * @throws UserNotFoundException not found account in the app
     */

    public Persona iniciarSesion(String usuario, String contrasena) throws UserNotFoundException {
        Cuenta cuenta = new Cuenta(usuario, contrasena);
        if (!cuentaList.contains(cuenta)){
            throw new UserNotFoundException("Usuario no encontrado");
        }
        else {
            for (Cuenta account: cuentaList) {
                if (account.getUsuario().equals(usuario)&&account.getPassword().equals(contrasena)){
                    cuenta=account;
                }
            }
            return cuenta.getPersonaAsociada();
        }
    }

    /**
     * This function allows the app register the teachers into the app (profesorsRegistrados)
     * @param profesor teacher who will be added
     * @throws AlreadyRegisteredUserException the teacher is already registered
     * @throws AccountException the account of the teacher is already registered
     */

    public void registrarProfesor(Profesor profesor) throws AlreadyRegisteredUserException, AccountException {
        if (profesresRegistrados.contains(profesor)){
            throw new AlreadyRegisteredUserException("Este profesor y se encuentra registrado");
        }
        else{
            if (verificarCuenta(profesor.getCuenta())){
                profesresRegistrados.add(profesor);
                cuentaList.add(profesor.getCuenta());
            }
        }
    }

    /**
     * This method allow the app to add students
     * @param estudiante the student to add
     * @throws AlreadyRegisteredUserException the student already exist
     * @throws AccountException the accout that the student is trying to register is already taken
     */
    public void registrarEstudiante(Estudiante estudiante) throws AlreadyRegisteredUserException, AccountException {
        if (estudiantesRegistrados.contains(estudiante)){
            throw new AlreadyRegisteredUserException("Este estudiante y se encuentra registrado");
        }
        else{
            if (verificarCuenta(estudiante.getCuenta())){
                estudiantesRegistrados.add(estudiante);
                cuentaList.add(estudiante.getCuenta());
            }
        }
    }


    //----------------Private Methods for the internal functions ----------------------------------

    /**
     * private method that verifies the accounts of the users
     * @param cuenta is the account that is trying to register any user
     * @return true if the cuentaList !contains(cuenta)
     * @throws AccountException the account is already registered
     */

    private boolean verificarCuenta(Cuenta cuenta) throws AccountException {
        if(cuentaList.contains(cuenta)){
            throw new AccountException("Esta cuenta ya está registrada");
        }
        else{
            for (Cuenta acc: cuentaList) {
                if (acc.getUsuario().equals(cuenta.getUsuario())){
                    throw new AccountException("Este nombre de usuario ya estpa registrado");
                }
            }
            return true;
        }
    }


    public void crearExamen(Profesor profesorLogeado, Examen examen) throws ExamCreationException {
       if (profesorLogeado.getExamenesCreados().contains(examen)||examenList.containsValue(examen)){
           throw new ExamCreationException("Este examen ya está creado");
       }
       else{
           profesorLogeado.getExamenesCreados().add(examen);
           examenList.put(String.valueOf(examen.getClave()),examen);
       }
    }
    public Examen presentarExamen(String text) throws ExamenNotFoundException {
        if(getExamenList().containsKey(text)){
            return getExamenList().get(text);
        }
        else {
            throw new ExamenNotFoundException("Este examen no existe");
        }
    }
}
