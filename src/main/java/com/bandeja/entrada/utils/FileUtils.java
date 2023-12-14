package com.bandeja.entrada.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.bandeja.entrada.entities.IntegrationLogger;
import com.bandeja.entrada.exception.FileOperationException;

/**
 * Clase  Utilitaria para  el procesamiento de archivos, paths  y otros
 * @author UTSunGemini-Zabala II- Camilo Cruz 13/07/2010
 *
 */
public class FileUtils {

    static IntegrationLogger log= IntegrationLogger.getInstance();

    public static String nombreArchivo(String  fileName){
        return fileName.substring(fileName.lastIndexOf("\\")+1);
    }

    /**
     * Verifica  la  ruta  del  archivo  y crea las  carpetas  necesarias
     * @param file el archivo con la  ruta  que requiere ser creado
     * @throws IOException si  el archivo ya existe la  creacion de directorios  falla
     *
     */
    private static void mkdirs(File file) throws IOException {
        log.debug("Creando el  archivo "+file.getAbsolutePath());
        if (file.exists() && !file.isFile()) {
            throw new IOException("File " + file.getPath() + " is actually not a file.");
        }
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("Creating directories " + parentFile.getPath() + " failed.");
        }
    }


    /**
     * Metodo que permite  escribir  desde un input stream  creado
     * Creado por Camilo Cruz 13/07/2010
     * @param file
     * @param input
     * @param append
     * @throws IOException
     */
    public static void write(File file, InputStream input, boolean append) throws IOException {
        mkdirs(file);
        BufferedOutputStream output = null;

        try {
            output = new BufferedOutputStream(new FileOutputStream(file, append));
            int data = -1;
            while ((data = input.read()) != -1) {
                output.write(data);
            }
        } finally {
            close(input, file);
            close(output, file);
        }
    }


    /**
     * Close the given I/O resource of the given file.
     * @param resource The I/O resource to be closed.
     * @param file The I/O resource's subject.
     */
    private static void close(Closeable resource, File file) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                String message = "Closing file " + file.getPath() + " failed.";
                // Do your thing with the exception and the message. Print it, log it or mail it.
                System.err.println(message);
                e.printStackTrace();
            }
        }
    }


    /**
     * Creado por Camilo Cruz 12/07/2010
     */
    public static boolean borrarFiles(String sFilePath){
        File fileTmp = new File(sFilePath);

        if(fileTmp.isDirectory()){
            for(File f:fileTmp.listFiles()){
                borrarFiles(f.getAbsolutePath());
            }

        }
        log.debug("A borrar "+sFilePath);
        return fileTmp.delete();
    }


    public static String writeFile(InputStream inputStream, File directory, String fileName) throws FileOperationException{
        try {
            if(!directory.isDirectory()){
                throw new FileOperationException(" la ruta  " + directory.getAbsolutePath() + " no corresponde a un directorio");
            }
            if(!directory.canWrite()){
                throw new FileOperationException("el directorio en la ruta  " + directory.getAbsolutePath() + " no puede ser escrito");
            }

            String[] fileNameParts = getFileNameParts(fileName);
            File tempFile = null;
            do{
                tempFile = new File(directory, fileNameParts[0] + "-" + new Date().getTime() + "." + fileNameParts[1]);
            }
            while(tempFile.exists());

            byte[] buf = null;
            int bufLen = 25 * 1024;
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            buf = new byte[bufLen];
            BufferedOutputStream bufferedOutputStream;

            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
            while(bufferedInputStream.read(buf,0,bufLen) != -1){
                bufferedOutputStream.write(buf);
            }
            bufferedInputStream.close();
            bufferedOutputStream.close();
            return tempFile.getAbsolutePath();
        } catch (FileNotFoundException e) {
            throw new FileOperationException(e);
        } catch (IOException e) {
            throw new FileOperationException(e);
        }
    }



    public static String[] getFileNameParts(File file){
        return getFileNameParts(file.getName());
    }


    public static String[] getFileNameParts(String fileName){
        int i = fileName.lastIndexOf('.');
        String[] fileNameParts = new String[2];
        if (i > 0 &&  i < fileName.length() - 1) {
            fileNameParts[0] = fileName.substring(0,i).toLowerCase();
            fileNameParts[1] = fileName.substring(i+1).toLowerCase();
        }
        return fileNameParts;
    }


}
