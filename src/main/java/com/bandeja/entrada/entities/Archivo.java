package com.bandeja.entrada.entities;


import com.bandeja.entrada.anotations.LabelCampo;
import com.bandeja.entrada.anotations.NoArchivo;
import com.bandeja.entrada.exception.FileOperationException;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

/**
 * Clase entidad que representa los archivos
 *
 * @author UTSunGemini-Zabala II- Camilo Cruz 12/07/2010
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "modulo", discriminatorType = DiscriminatorType.STRING, length = 20)
@NamedQueries({ @NamedQuery(name = "Archivo.listarTodos", query = "select object(o) from Archivo o") })
@EntityListeners({ ArchivoListener.class })
public abstract class Archivo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1840941167970810906L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_Archivo")
    @SequenceGenerator(name = "SEC_Archivo", sequenceName = "ARCHIVO_SEQ", allocationSize = 1)
    private Long idArchivo;

    @ManyToOne
    @JoinColumn(name = "idRequisito")
    private Requisito requisito;

    @ManyToOne
    @JoinColumn(name = "idTramiteSolicitado")
    private TramiteSolicitado tramiteSolicitado;

    @Transient
    private String path = null;

    @Transient
    private String dbPath = null;

    private Integer year;

    private String nombreArchivoCliente;

    @Enumerated(EnumType.ORDINAL)
    private FileTransaction.State state = FileTransaction.State.Synchronized;

    private String contentType;

    /**
     * El nombre del archivo en el sistema de archivos del sistema operativo
     */
    private String fileSystemName;

    @Transient
    private FileTransaction fileTransaction = new FileTransaction();

    private Integer enviado;

    /**
     * Constructor con los datos basicos para poder persistir un archivo
     *
     * @param path
     *            la ruta donde se encuentra el archivo temporalemente
     * @param nombreArchivoCliente
     *            nombre del archivo cargado por el cliente
     * @throws SiriException
     */
    public Archivo(String path, String nombreArchivoCliente) throws SiriException {
        setPath(path);
        this.nombreArchivoCliente = nombreArchivoCliente;
    }

    public Archivo() {
    }

    /**
     * @return Retorna el nombre del archivo que fue cargado por el cliente.
     */
    public String getNombreArchivoCliente() {
        return nombreArchivoCliente;
    }

    /**
     * Reemplaza el valor del archivo que fue cargado por el cliente
     *
     * @param nombreArchivoCliente
     */
    public void setNombreArchivoCliente(String nombreArchivoCliente) {
        this.nombreArchivoCliente = nombreArchivoCliente;
    }

    /**
     * @return Retorna el valor de idArchivo
     */
    public Long getIdArchivo() {
        return idArchivo;
    }

    /**
     * Reemplaza el valor de idArchivo para este objeto por el enviado en el parametro.
     *
     * @param idArchivo
     *            contiene el valor para reemplazar idArchivo en el objeto.
     */
    public void setIdArchivo(Long idArchivo) {
        this.idArchivo = idArchivo;
    }

    /**
     * Retorna el valor actual path (getter)
     *
     * @author Camilo Cruz 12/07/2010 15:54:35
     * @return path actual
     */
    public String getPath() {
        return path;
    }

    /**
     * Sustituye el valor actual de path por el valor ingresado en path (setter)
     *
     * @author Camilo Cruz 12/07/2010 15:54:35
     * @param path
     *            valor a actualizar
     * @throws Exception
     */
    public void setPath(String path) throws SiriException {
        if (path != null) {
            File file = new File(path);
            if (!file.exists()) {
                throw new SiriException("El archivo ubicado en  " + path + " no pudo ser encontrado.");
            }
            this.path = path;
            state = FileTransaction.State.Unsynchronized;
        } else {
            this.path = path;
        }
    }

    /**
     * @return Retorna el valor de contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Reemplaza el valor de contentType para este objeto por el enviado en el parametro contentType.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Obtiene el nombre del folder en el cual se van agrupar todos los archivos que pertenecen a una instancia de un
     * modulo por ej. para tramites web debe retornar el numero del id del tramite al que pertenece
     *
     * @return
     * @throws FileOperationException
     */
    public String getRutaFolder() throws FileOperationException {

        return "TRAMITES" + File.separator + getIdParaFolder().toString();
    }

    /**
     * Obtiene el id que sera el nombre del folder por el cual se agruparan un numero de archivos para el modolo
     * tramites por ej. se utiliza el id del tramite para agrupar todos los archivos de un tramite
     */
    public abstract Long getIdParaFolder();

    /**
     * @return Retorna el valor de fileTransaction
     */
    @NoArchivo
    public FileTransaction getFileTransaction() {
        return fileTransaction;
    }

    /**
     * Reemplaza el valor de fileTransaction para este objeto por el enviado en el parametro fileTransaction.
     */
    public void setFileTransaction(FileTransaction fileTransaction) {
        this.fileTransaction = fileTransaction;
    }

    /**
     * @return Retorna el valor de dbPath
     * @throws FileOperationException
     */
    public String getDbPath() {
        return dbPath;
    }

    /**
     * Reemplaza el valor de dbPath para este objeto por el enviado en el parametro dbPath.
     */
    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }

    /**
     * @return Retorna el valor de year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Reemplaza el valor de year para este objeto por el enviado en el parametro year.
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * @return Retorna el valor de fileSystemName
     */
    @LabelCampo(label = "Nombre Archivo")
    public String getFileSystemName() {
        return fileSystemName;
    }

    /**
     * Reemplaza el valor de fileSystemName para este objeto por el enviado en el parametro fileSystemName.
     */
    public void setFileSystemName(String fileSystemName) {
        this.fileSystemName = fileSystemName;
    }

    /**
     * @return Retorna el valor de state
     */
    public FileTransaction.State getState() {
        return state;
    }

    /**
     * Reemplaza el valor de state para este objeto por el enviado en el parametro state.
     */
    public void setState(FileTransaction.State state) {
        this.state = state;
    }

    public Requisito getRequisito() {
        return requisito;
    }

    public void setRequisito(Requisito requisito) {
        this.requisito = requisito;
    }

    public TramiteSolicitado getTramiteSolicitado() {
        return tramiteSolicitado;
    }

    public void setTramiteSolicitado(TramiteSolicitado tramiteSolicitado) {
        this.tramiteSolicitado = tramiteSolicitado;
    }

    public Integer getEnviado() {
        return enviado;
    }

    public void setEnviado(Integer enviado) {
        this.enviado = enviado;
    }

}
