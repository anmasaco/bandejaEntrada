package com.bandeja.entrada.entities;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * Esta Entidad corresponde al listado de las entidades encargadas de vigilar a las sociedades en Colombia Distintas de
 * la SFC, Ejm Supersociedades
 *
 * @author UTSunGemini-Zabala II- Camilo Cruz 8/06/2010
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = "EntidadVigila.listarTodos", query = "select object(o) from EntidadVigila o") })
public class EntidadVigila implements Serializable {

    /**
     * Campo para identificar la clase durante la serializacion
     *
     * @author Camilo Cruz 8/06/2010 11:01:05
     */
    private static final long serialVersionUID = 3717375831219877540L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_ENTIDADVIGILA")
    @SequenceGenerator(name = "SEC_ENTIDADVIGILA", sequenceName = "ENTIDADVIGILA_SEQ", allocationSize = 1)
    private Long idEntidadVigila;
    private String nombre;
    private String numeroIdentificacion;
    private String direccion;
    private String telefono;
    private String codigoRNVEI;

    /**
     * @return Retorna el valor de nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Reemplaza el valor de nombre para este objeto por el enviado en el parametro.
     *
     * @param nombre
     *            contiene el valor para reemplazar nombre en el objeto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el valor actual idEntidadVigila (getter)
     *
     * @author Camilo Cruz 8/06/2010 10:51:55
     * @return el/la idEntidadVigila
     */
    public Long getIdEntidadVigila() {
        return idEntidadVigila;
    }

    /**
     * Sustituye el valor actual de idEntidadVigila por el valor ingresado en idEntidadVigila (setter)
     *
     * @author Camilo Cruz 8/06/2010 10:51:55
     * @param el
     *            /la nuevo valor para idEntidadVigila
     */
    public void setIdEntidadVigila(Long idEntidadVigila) {
        this.idEntidadVigila = idEntidadVigila;
    }

    /**
     * @return the numeroIdentificacion
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * @param numeroIdentificacion
     *            the numeroIdentificacion to set
     */
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion
     *            the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono
     *            the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the codigoRNVEI
     */
    public String getCodigoRNVEI() {
        return codigoRNVEI;
    }

    /**
     * @param codigoRNVEI
     *            the codigoRNVEI to set
     */
    public void setCodigoRNVEI(String codigoRNVEI) {
        this.codigoRNVEI = codigoRNVEI;
    }
}

