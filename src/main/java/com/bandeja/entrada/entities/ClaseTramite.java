package com.bandeja.entrada.entities;
import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the CLASETRAMITE database table.
 *
 */
@Entity
@Table(name = "CLASETRAMITE")
public class ClaseTramite implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique = true, nullable = false, precision = 22)
    private Integer idClaseTramite;

    @Column(length = 200)
    private String nombre;

    public ClaseTramite() {
    }

    public Integer getIdClaseTramite() {
        return this.idClaseTramite;
    }

    public void setIdclasetramite(Integer idClaseTramite) {
        this.idClaseTramite = idClaseTramite;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
