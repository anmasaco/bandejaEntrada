package com.bandeja.entrada.entities;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the ESTADOTRAMITE database table.
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = "EstadoTramite.findById", query = "select object(o) from EstadoTramite o "
        + "where o.idEstadoTramite =:idEstadoTramite") })
public class EstadoTramite implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int idEstadoTramite;
    private String nombre;

    public EstadoTramite() {
    }

    public EstadoTramite(int idEstadoTramite) {
        this.idEstadoTramite = idEstadoTramite;
    }

    public int getIdEstadoTramite() {
        return this.idEstadoTramite;
    }

    public void setIdEstadoTramite(int idEstadoTramite) {
        this.idEstadoTramite = idEstadoTramite;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
