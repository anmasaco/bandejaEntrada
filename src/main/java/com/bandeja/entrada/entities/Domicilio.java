package com.bandeja.entrada.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

/**
 * Domicilio Generico, del cual pueden heredar para datos de domicilio mas complejos
 *
 * @author UT SunGemini-Zabala II
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Domicilio.listarTodos", query = "select object(o) from Domicilio o") })
// @Table(name = "DOMICILIO", schema = "SIRIDEVELOP")
public class Domicilio implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 209768492362914431L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_DOMICILIO")
    @SequenceGenerator(name = "SEC_DOMICILIO", sequenceName = "DOMICILIO_SEQ", allocationSize = 1)
    private Long idDomicilio;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TELEFONO")
    private String telefono;

    // @ManyToOne
    // @JoinColumn(name="IDCIUDAD")
    @Transient
    private Ciudad ciudad;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "DISCDOMICILIO")
    private String discDomicilio;

    /**
     * @return Retorna el valor de idDomicilio
     */

    public Long getIdDomicilio() {
        return idDomicilio;
    }

    /**
     * Reemplaza el valor de idDomicilio para este objeto por el enviado en el parametro idDomicilio.
     */
    public void setIdDomicilio(Long idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    /**
     * @return Retorna el valor de direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Reemplaza el valor de direccion para este objeto por el enviado en el parametro direccion.
     */

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return Retorna el valor de telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Reemplaza el valor de telefono para este objeto por el enviado en el parametro telefono.
     */

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return Retorna el valor de ciudad
     */
    public Ciudad getCiudad() {
        return ciudad;
    }

    /**
     * Reemplaza el valor de ciudad para este objeto por el enviado en el parametro ciudad.
     */

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return Retorna el valor de discDomicilio
     */
    public String getDiscDomicilio() {
        return discDomicilio;
    }

    /**
     * Reemplaza el valor de discDomicilio para este objeto por el enviado en el parametro discDomicilio.
     */
    public void setDiscDomicilio(String discDomicilio) {
        this.discDomicilio = discDomicilio;
    }
}
