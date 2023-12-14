package com.bandeja.entrada.entities;

import com.bandeja.entrada.exception.FileOperationException;
import com.bandeja.entrada.utils.FileUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;


/**
 * Esta clase tiene la responsabilidad de mantener el estado de los archivos
 * que se quieren manejar dentro de una transaccion
 * @author UT SunGemini-Zabala II
 *
 */

public class FileHandlerTransaction
{

    /**
     * La lista de archivos a eliminar en el commit
     */
    private List<Archivo> archivosEliminarCommit = new ArrayList<Archivo>();

    /**
     * El directorio base sobre el cual se quiere trabajar
     */
    private static File directoryBase = null;

    /**
     * La lista de archivos que se manejaron dentro de la transaccion
     */
    private List<Archivo> transactionFiles = new ArrayList<Archivo>();

    /**
     * Logger
     * Campo
     * @author UT SunGemini-Zabala II 3/08/2010 15:38:29
     */
    private static IntegrationLogger log= IntegrationLogger.getInstance();

    /*
     * Incializacion de directoryBase con base en un archivo de propiedades
     * para facilitar el manejo de archivos porque siempre debe ser el mismo
     * para todas las instancias
     */
    static{
		/*try {
		    /*URL url = Thread.currentThread().getContextClassLoader().getResource("META-INF/fileHandler.properties");
		    Properties properties = new Properties();
		    properties.load(url.openStream());
		    directoryBase = new File((String) properties.get("directoryBasePath"));
		} catch (IOException e) {
		    throw new RuntimeException(e);
		}*/

        if( System.getProperty("directoryBasePathSiri") == null ){
            try {
                throw new SiriException("Directior base no encontrado ");
            } catch (SiriException e) {
                e.printStackTrace();
            }
        }else{
            directoryBase = new File( System.getProperty("directoryBasePathSiri"));
        }
    }

    /**
     * @return the directoryBase
     */
    public static File getDirectoryBase() {
        return directoryBase;
    }

    /**
     * Constructor por defecto
     */
    public FileHandlerTransaction(){
    }

    /**
     * Verifica que el directorio base exista antes de comenzar cualquier tipo de operacion
     * @param directoryBase
     * @throws FileOperationException
     */
    private static void verifyDirectoryBase(File directoryBase) throws FileOperationException {
        if(!directoryBase.exists() || !directoryBase.isDirectory() || !directoryBase.canWrite()){
            throw new FileOperationException("La ruta " + directoryBase.getAbsolutePath() + " no existe o no puede ser escrita");
        }
    }


    /**
     * Retorna el path en file system de un archivo que fue persistido en base de datos
     * @param archivo
     * @return la ruta en el sistema de archivos de un archivo que fue o sera persistido en la base de datos
     * @throws FileOperationException
     */
    public static String returnPathDb(Archivo archivo) throws FileOperationException{
        //	log.debug("FHT:returnPathDb "+archivo.getIdArchivo());
        if(archivo.getYear() == null){
            log.error("Archivo con nombre "+archivo.getNombreArchivoCliente()+" Annio Invalido!");
            throw new FileOperationException("El archivo con tiene el a�o asignado");
        }else if (archivo.getFileSystemName() == null){
            log.error("File System Name Invalid!");
            throw new FileOperationException("El archivo no tiene nombre de archivo asignado");
        }
        //	log.debug("Directory "+directoryBase.getAbsolutePath());
        //	log.debug("Year "+archivo.getYear());
        //	log.debug("Y del  tramite "+((ArchivoTramite)archivo).getTramiteSolicitado().getIdTramiteSolicitado());
        //	log.debug("Ruta Folder "+archivo.getRutaFolder());
        //	log.debug("File systemName "+archivo.getFileSystemName());
        //	log.debug("FHT:returnPathDbFin ");
        return directoryBase.getAbsolutePath() + File.separator + archivo.getYear() + File.separator + archivo.getRutaFolder() + File.separator + archivo.getFileSystemName();
    }



    /**
     * Mueve un archivo que va ser persistido por primera vez crea la informacion necesaria
     * para que en caso de error el archivo pueda ser retornado a su origen
     * @param archivo, que dentro de la transaccion sera persistido
     * @throws FileOperationException
     */
    public static void moveNewFile(Archivo archivo) throws FileOperationException{
        if(archivo.getState().equals(FileTransaction.State.Unsynchronized)){
            verifyDirectoryBase(directoryBase);

            File currentFile = new File(archivo.getPath());

            if(!currentFile.canRead()){
                throw new FileOperationException("El archivo ubicado en " + currentFile.getAbsolutePath() + " no puede ser leido...");
            }
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            archivo.setYear(year);
            File yearDir = new File(directoryBase, year.toString());

            if(!yearDir.exists()){
                yearDir.mkdir();
            }

            File moduloIdDir = new File(yearDir,archivo.getRutaFolder());
            if(!moduloIdDir.exists()){
                if(!moduloIdDir.mkdirs()){
                    throw new FileOperationException("la siguienta ruta de directorios no pudo ser creada " + moduloIdDir.getAbsolutePath());
                }
            }

            if(archivo.getNombreArchivoCliente() == null){
                throw new FileOperationException("El archivo con ruta " + archivo.getPath() + " no tiene asignado el nombre archivo cliente");
            }

            File newFile;
            newFile = new File(moduloIdDir,archivo.getNombreArchivoCliente().replace(" ", "_"));
            String[] fileNameParts = FileUtils.getFileNameParts(newFile);

            for(int i=0;newFile.exists();i++){
                newFile	= new File(moduloIdDir, fileNameParts[0] +  "-" + i + '.' + fileNameParts[1]);
            }

            if(!currentFile.renameTo(newFile)){
                throw new FileOperationException(" El archivo con ruta " + archivo.getPath() + " no pudo ser movido a. " + newFile.getAbsolutePath() );
            }

            archivo.setFileSystemName(newFile.getName());
            archivo.setDbPath(newFile.getAbsolutePath());
            archivo.getFileTransaction().setRutaArchivoOrigen(archivo.getPath());
            archivo.getFileTransaction().setRutaArchivoDestino(archivo.getDbPath());
            archivo.setState(FileTransaction.State.Synchronized);
        }
    }

    /**
     * Mueve un archivo que va ser actualizado en base de datos, crea la informacion necesaria
     * para que en caso de error el archivo pueda ser retornado a su origen y se eliminen copias innecesarias de archivo
     * @param archivo, que dentro de la transaccion sera persistido
     * @throws FileOperationException
     */
    public static void moveUpdatedFile(Archivo archivo) throws FileOperationException{
        if(archivo.getState().equals(FileTransaction.State.Unsynchronized)){
            verifyDirectoryBase(directoryBase);
            archivo.getFileTransaction().setRutaArchivoTempUpdate(archivo.getDbPath());

            File currentFile = new File(archivo.getPath());

            if(!currentFile.canRead()){
                throw new FileOperationException("El archivo ubicado en " + currentFile.getAbsolutePath() + " no puede ser leido..");
            }

            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            archivo.setYear(year);
            File yearDir = new File(directoryBase, year.toString());

            if(!yearDir.exists()){
                yearDir.mkdir();
            }

            File moduloIdDir = new File(yearDir,archivo.getRutaFolder());

            if(!moduloIdDir.exists()){
                if(!moduloIdDir.mkdirs()){
                    throw new FileOperationException("la siguienta ruta de directorios no pudo ser creada " + moduloIdDir.getAbsolutePath());
                }
            }

            if(archivo.getNombreArchivoCliente() == null){
                throw new FileOperationException("El archivo con ruta " + archivo.getPath() + " no tiene asignado el nombre archivo cliente");
            }

            File newFile;
            newFile = new File(moduloIdDir,archivo.getNombreArchivoCliente().replace(" ", "_"));
            String[] fileNameParts = FileUtils.getFileNameParts(newFile);

            for(int i=0;newFile.exists();i++){
                newFile	= new File(moduloIdDir, fileNameParts[0] +  "-" + i + '.' + fileNameParts[1]);
            }

            if(!currentFile.renameTo(newFile)){
                throw new FileOperationException(" El archivo con ruta " + archivo.getPath() + " no pudo ser movido a.. " + newFile.getAbsolutePath() );
            }

            archivo.getFileTransaction().setRutaArchivoOrigen(archivo.getPath());
            archivo.getFileTransaction().setRutaArchivoDestino(newFile.getAbsolutePath());
            archivo.setFileSystemName(newFile.getName());
            archivo.setDbPath(newFile.getAbsolutePath());
            archivo.setState(FileTransaction.State.Synchronized);
        }
    }


    /**
     * Remueve cualquier informacion generada para restaurar los archivos
     */
    public void commitArchivoTransaction(){
        System.out.println("");
        for(Archivo archivo : transactionFiles){
            if(archivo.getFileTransaction().getRutaArchivoTempUpdate() != null && archivo.getIdArchivo() != null){
                File oldFile = new File(archivo.getFileTransaction().getRutaArchivoTempUpdate());
                if(oldFile.exists()){
                    oldFile.delete();
                }
            }

            if(archivo.getState().equals(FileTransaction.State.Deleted) && archivo.getIdArchivo() != null){
                File oldFile = new File(archivo.getDbPath());
                if(oldFile.exists()){
                    oldFile.delete();
                }
            }
        }
        for (Archivo archivo : archivosEliminarCommit) {
            File oldFile = new File(archivo.getDbPath());
            if(oldFile.exists()){
                oldFile.delete();
            }
        }
    }

    /**
     * Retorna los archivos a sus origenes y remueve cualquier archivo generado para restauracion
     */
    public void rollbackArchivoTransaction(){
        for(Archivo archivo : transactionFiles){
            if(archivo.getFileTransaction() != null){
                if(archivo.getFileTransaction().getRutaArchivoOrigen() != null && archivo.getFileTransaction().getRutaArchivoDestino() != null){
                    File originFile = new File(archivo.getFileTransaction().getRutaArchivoOrigen());
                    File dbFile = new File(archivo.getFileTransaction().getRutaArchivoDestino());
                    dbFile.renameTo(originFile);
                }
            }
        }
    }




    //	public void returnFile(Archivo archivo) throws FileOperationException{
    //		File currentFile = new File(directoryBase,archivo.getYear()+ File.separator + archivo.getRutaFolder() + File.separator + archivo.getFileSystemName() );
    //		File fileDestination = new File(archivo.getPath());
    //		String[] fileNameParts = FileUtils.getFileNameParts(fileDestination);
    //		for(int i=0;fileDestination.exists();i++){
    //			fileDestination	= new File(fileNameParts[0] +  "-" + i + '.' + fileNameParts[1]);
    //		}
    //		if(!currentFile.renameTo(fileDestination)){
    //			throw new FileOperationException(" El archivo con ruta " + currentFile.getPath() + " no pudo ser movido a... " + fileDestination.getAbsolutePath() );
    //		}
    //	}





    public void removeFile(Archivo archivo) {
        if(archivo.getIdArchivo() != null){
            File oldFile = new File(archivo.getDbPath());
            File directory = oldFile.getParentFile();
            File tempDirectory = new File(directory, "temp");
            if(!tempDirectory.exists()){
                tempDirectory.mkdir();
            }

            File tempFile = new File(tempDirectory,oldFile.getName());
            if(tempFile.exists()){
                tempFile.delete();
            }
            oldFile.renameTo(tempFile);
        }
    }

    /**
     * Los objetos que seran manejados dentro de la transaccion si es una actualizacion
     * los archivos son movidos dentro del metodo.
     * @param todosArchivos
     * @throws FileOperationException
     */
    public void includeFiles(Collection<Archivo> todosArchivos) throws FileOperationException {
        for(Archivo archivo : todosArchivos){
            if(archivo != null){
                if(archivo.getIdArchivo() == null){
                    moveNewFile(archivo);
                }else{
                    moveUpdatedFile(archivo);
                }
                transactionFiles.add(archivo);
            }
        }
    }



    /**
     * Metodo que permite  buscar los  arhivos por entidad
     * Creado por UT SunGemini-Zabala II 3/08/2010
     * @param <T> Tipo de  Objeto recibido
     * @param objeto
     * @return
     */
    public static   <T>  List<Archivo> buscaArchivos(T objeto){
        List<Archivo> list= new ArrayList<Archivo>();

        for(Method m:objeto.getClass().getMethods()){
            if(m.getName().startsWith("get") && m.getReturnType().getName().equals("co.gov.superfinanciera.siri.entities.Archivo") ){
                try {
                    Archivo ret=(Archivo) m.invoke(objeto, null);
                    if(ret!=null){
                        list.add(ret);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            //Permite  buscar el metodo que  debe  cargar los archivos en cascada
            if(m.getName().equals("getListaArchivoCascada")|| m.getName().equals("getTodosArchivos") ){
                try {
                    list.addAll((Collection<? extends Archivo>) m.invoke(objeto, null));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }


    /**
     * Metodo que permite  incluir los archivos que  encuentra por  reflection
     * Creado por UT SunGemini-Zabala II 3/08/2010
     * @param <T>
     * @param objeto
     * @throws FileOperationException
     */
    public<T> void includeFilesByObject(T objeto) throws FileOperationException {
        includeFiles(buscaArchivos(objeto));
    }
}
