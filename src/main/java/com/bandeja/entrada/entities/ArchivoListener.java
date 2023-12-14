package com.bandeja.entrada.entities;


import com.bandeja.entrada.exception.ArchivoValidationException;
import com.bandeja.entrada.exception.FileOperationException;

import javax.persistence.*;
import java.io.File;

/**
 * Clase encargada de manejar los diferentes callbacks para la clase archivo
 * @author UTSunGemini-Zabala II
 *
 */
public class ArchivoListener {

    private  IntegrationLogger log=IntegrationLogger.getInstance();

    /**
     * Carga la ruta del archivo automaticamente segun informacion traida
     * de la entidad
     * @param archivo
     * @throws Exception
     */
    @PostLoad
    public void postLoad(Archivo archivo) throws Exception{
        try{
            archivo.setDbPath(FileHandlerTransaction.returnPathDb(archivo));
        }catch (Exception e) {
            archivo.setDbPath(null);
        }
    }

    /**
     * Valida que al persistir el archivo exista en el sistema de archivos antes de salvarlo
     * @param archivo
     * @throws ArchivoValidationException
     */
    @PrePersist
    public void prePersist(Archivo archivo)throws ArchivoValidationException {
        try{
            String path = FileHandlerTransaction.returnPathDb(archivo);
            File dbFile = new File(path);
            if(!dbFile.exists())
                throw new ArchivoValidationException("el archivo con nombre " + archivo.getDbPath() + " no fue encontrado en la ruta especificada :" + archivo.getNombreArchivoCliente());
            if( archivo.getState().equals(FileTransaction.State.Unsynchronized)){
                throw new ArchivoValidationException(" El archivo fue actualizado en la entidad pero no fue movido fisicamente a " +  dbFile.getAbsolutePath()  + " recuerde mover los archivos ");
            }
        }catch(FileOperationException e){
            throw new ArchivoValidationException("el archivo con nombre " + archivo.getDbPath() + " no fue encontrado en la ruta especificada :" + archivo.getNombreArchivoCliente());
        }
    }

    /**
     * Valida que al actualizar el archivo exista en el sistema de archivos antes de actualizarlo
     * @param archivo
     * @throws ArchivoValidationException
     */
    @PreUpdate
    public void preUpdate(Archivo archivo)throws ArchivoValidationException{
        try {
            if(!archivo.getState().equals(FileTransaction.State.Deleted)){
                File dbFileNew = new File(FileHandlerTransaction.returnPathDb(archivo));
                if(!dbFileNew.exists())
                    throw new ArchivoValidationException("el archivo con ruta " + dbFileNew.getAbsolutePath() + " no fue encontrado en la ruta especificada");
                if( archivo.getState().equals(FileTransaction.State.Unsynchronized)){
                    throw new ArchivoValidationException(" El archivo fue actualizado en la entidad pero no fue movido fisicamente a " +  dbFileNew.getAbsolutePath()  + " recuerde mover los archivos ");
                }
            }
        } catch (FileOperationException e) {
            throw new ArchivoValidationException(e);
        }
    }

    /**
     * Valida que al remover el archivo tambien haya sido removido del sistema de archivos
     * @param archivo
     * @throws ArchivoValidationException
     */
    @PreRemove
    public void preRemove(Archivo archivo) throws ArchivoValidationException{
        archivo.setState(FileTransaction.State.Deleted);
    }


    /**
     * Metodo que valida  que el valor del path  quede  borrado  luego de que se han realizado las operaciones  de  insert or  update
     * Creado por UT SunGemini-Zabala II 3/08/2010
     * @param archivo
     * @throws SiriException
     */
    @PostPersist
    @PostUpdate
    public void postPersist(Archivo archivo) throws SiriException{
        archivo.setPath(null);
    }

}
