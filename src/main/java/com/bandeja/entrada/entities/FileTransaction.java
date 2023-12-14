package com.bandeja.entrada.entities;

import java.io.Serializable;


/**
 * Esta clase contiene informacion adicional del archivo
 * para que pueda ser persistido dentro de una transacci�n.
 * y se puedan realizar operaciones de callbakc y commit.
 *
 * @author UT SunGemini-Zabala II
 *
 */
public class FileTransaction implements Serializable {


    /**
     * Campo  Generado para la  serializacion
     * @author UT SunGemini-Zabala II 5/08/2010 10:28:53
     */
    private static final long serialVersionUID = 1123365662592071626L;

    public enum State{
        Synchronized,
        Unsynchronized,
        Deleted
    }

    /**
     * la ruta origen del archivo
     */
    private String rutaArchivoOrigen;

    /**
     * la ruta donde fue movido el archivo
     */
    private String rutaArchivoDestino;

    /**
     * la ruta donde fue movido el archivo que se quiere actualizar
     */
    private String rutaArchivoTempUpdate;

    /**
     * @return Retorna el valor de rutaArchivoOrigen
     */
    public String getRutaArchivoOrigen() {
        return rutaArchivoOrigen;
    }

    /**
     * Reemplaza el valor de  rutaArchivoOrigen para este objeto por el enviado en el parametro rutaArchivoOrigen.
     */
    public void setRutaArchivoOrigen(String rutaArchivoOrigen) {
        this.rutaArchivoOrigen = rutaArchivoOrigen;
    }

    /**
     * @return Retorna el valor de rutaArchivoDestino
     */
    public String getRutaArchivoDestino() {
        return rutaArchivoDestino;
    }

    /**
     * Reemplaza el valor de  rutaArchivoDestino para este objeto por el enviado en el parametro rutaArchivoDestino.
     */
    public void setRutaArchivoDestino(String rutaArchivoDestino) {
        this.rutaArchivoDestino = rutaArchivoDestino;
    }

    /**
     * @return Retorna el valor de rutaArchivoTempUpdate
     */
    public String getRutaArchivoTempUpdate() {
        return rutaArchivoTempUpdate;
    }

    /**
     * Reemplaza el valor de  rutaArchivoTempUpdate para este objeto por el enviado en el parametro rutaArchivoTempUpdate.
     */
    public void setRutaArchivoTempUpdate(String rutaArchivoTempUpdate) {
        this.rutaArchivoTempUpdate = rutaArchivoTempUpdate;
    }

}
