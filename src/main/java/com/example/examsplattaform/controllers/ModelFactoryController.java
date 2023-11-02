package com.example.examsplattaform.controllers;

import com.example.examsplattaform.exceptions.AccountException;
import com.example.examsplattaform.model.Cuenta;
import com.example.examsplattaform.model.Plataforma;
import com.example.examsplattaform.persistencia.Persistencia;

import java.io.IOException;

public class ModelFactoryController {

    private Plataforma plataforma;

    //------------------------------  Singleton ------------------------------------------------
    // Clase estatica oculta. Tan solo se instanciara el singleton una vez
    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aqu� al ser protected
        private final static ModelFactoryController eINSTANCE;

        static {
            try {
                eINSTANCE = new ModelFactoryController();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Metodo para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() throws IOException {
        //Siempre se debe verificar si la raiz del recurso es null
        guardarResourceBinario();
        cargarResourceXML();

        if (plataforma == null) {
            System.out.println("es null");
            inicializarDatos();
            guardarResourceXML();
            guardarResourceBinario();
        }


        //Registrar la accion de incio de sesi�n
        Persistencia.guardaRegistroLog("Inicio de sesion del usuario:pedro", 1, "inicioSesion");


    }


    public void guardarResourceBinario() {

        Persistencia.guardarRecursoDomainBinario(plataforma);
    }


    public void cargarResourceXML() {

        plataforma = Persistencia.cargarRecursoDomainXML();
    }


    public void guardarResourceXML() {

        Persistencia.guardarRecursoDomainXML(plataforma);
    }

    private void inicializarDatos() throws IOException {
        plataforma = new Plataforma();
        System.out.println("Domain inicializado " + plataforma);
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }
}