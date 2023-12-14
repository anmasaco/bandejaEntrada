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
@NamedQueries({ @NamedQuery(name = "SituacionLegal.listarTodos", query = "select object(o) from SituacionLegal o") })
public class SituacionLegal implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6342209604903044381L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_SituacionLegal")
    @SequenceGenerator(name = "SEC_SituacionLegal", sequenceName = "SituacionLegal_SEQ", allocationSize = 1)
    private Long idSituacionLegal;

    private String nombre;

    /**
     * @return Retorna el valor de idSituacionLegal
     */
    public Long getIdSituacionLegal() {
        return idSituacionLegal;
    }

    /**
     * Reemplaza el valor de idSituacionLegal para este objeto por el enviado en el parametro.
     *
     * @param idSituacionLegal
     *            contiene el valor para reemplazar idSituacionLegal en el objeto.
     */
    public void setIdSituacionLegal(Long idSituacionLegal) {
        this.idSituacionLegal = idSituacionLegal;
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
