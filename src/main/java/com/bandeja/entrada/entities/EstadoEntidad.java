package com.bandeja.entrada.entities;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries({ @NamedQuery(name = "EstadoEntidad.listarTodos", query = "select object(o) from EstadoEntidad o") })
public class EstadoEntidad implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3906638257775147592L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_EstadoEntidad")
    @SequenceGenerator(name = "SEC_EstadoEntidad", sequenceName = "EstadoEntidad_SEQ", allocationSize = 1)
    private Long idEstadoEntidad;

    private String nombre;

    /**
     * @return Retorna el valor de idEstadoEntidad
     */
    public Long getIdEstadoEntidad() {
        return idEstadoEntidad;
    }

    /**
     * Reemplaza el valor de idEstadoEntidad para este objeto por el enviado en el parametro.
     *
     * @param idEstadoEntidad
     *            contiene el valor para reemplazar idEstadoEntidad en el objeto.
     */
    public void setIdEstadoEntidad(Long idEstadoEntidad) {
        this.idEstadoEntidad = idEstadoEntidad;
    }

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
}