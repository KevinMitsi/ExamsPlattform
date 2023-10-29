package com.example.examsplattaform.persistencia;

import com.example.examsplattaform.model.Plataforma;


public class Persistencia {
    public static final String RUTA_ARCHIVO_LOG = "src/main/java/com/example/examsplattaform/archivo/Log.log";
    public static final String RUTA_ARCHIVO_MODELO_TALLER_BINARIO = "src/main/java/com/example/examsplattaform/archivo/model.dat";
    public static final String RUTA_ARCHIVO_MODELO_TALLER_XML = "src/main/java/com/example/examsplattaform/archivo/model.xml";


    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {

        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }


    //------------------------------------SERIALIZACI�N  y XML


    public static Plataforma cargarRecursoHostalBinario() {

        Plataforma plataforma = null;

        try {
            plataforma = (Plataforma) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_TALLER_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return plataforma;
    }

    public static void guardarRecursoDomainBinario(Plataforma plataforma) {

        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_TALLER_BINARIO, plataforma);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static Plataforma cargarRecursoDomainXML() {

        Plataforma plataforma = null;

        try {
            plataforma = (Plataforma) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_TALLER_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return plataforma;
    }



    public static void guardarRecursoDomainXML(Plataforma plataforma) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_TALLER_XML, plataforma);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}