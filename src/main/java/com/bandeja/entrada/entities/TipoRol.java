package com.bandeja.entrada.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Tipo de destino del rol al cual puede ser asignado el rol.
 * @author eamosquera
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name =  "TipoRol.listarTodos",
                query = "select object(o) from TipoRol o")
})
public class TipoRol implements Serializable {

    private static final long serialVersionUID = -4118333664630268311L;

    @Id
    private int idTipoRol;

    @Column(length = 50)
    private String nombre;

    public int getIdTipoRol() {
        return idTipoRol;
    }

    public void setIdTipoRol(int idTipoRol) {
        this.idTipoRol = idTipoRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}