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
@NamedQueries({ @NamedQuery(name = "ClaseEntidad.listarTodos", query = "select object(o) from ClaseEntidad o ORDER BY o.nombre ASC") })
public class ClaseEntidad implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3124763949205918792L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_ClaseEntidad")
    @SequenceGenerator(name = "SEC_ClaseEntidad", sequenceName = "ClaseEntidad_SEQ", allocationSize = 1)
    private Long idClaseEntidad;

    private String nombre;

    /**
     * @return Retorna el valor de idClaseEntidad
     */
    public Long getIdClaseEntidad() {
        return idClaseEntidad;
    }

    /**
     * Reemplaza el valor de idClaseEntidad para este objeto por el enviado en el parametro.
     *
     * @param idClaseEntidad
     *            contiene el valor para reemplazar idClaseEntidad en el objeto.
     */
    public void setIdClaseEntidad(Long idClaseEntidad) {
        this.idClaseEntidad = idClaseEntidad;
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
