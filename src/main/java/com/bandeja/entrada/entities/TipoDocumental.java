package com.bandeja.entrada.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the GDO_DOCUMENTAL database table.
 *
 */

@Entity
@NamedQueries({
        @NamedQuery(
                name = "TipoDocumental.listarTodos",
                query = "select object(o) from TipoDocumental o where o.codigo in ('28','36','46','151', '153', '154') order by o.codigo"),
        @NamedQuery(
                name = "TipoDocumental.listar506509",
                query = "select object(o) from TipoDocumental o where o.codigo in ('26','28','32','36','571', '645', '646', '647', '649', '650') order by o.codigo"),
        @NamedQuery(
                name = "TipoDocumental.listarResponderPolizas",
                query = "select object(o) from TipoDocumental o where o.codigo in ('36','46') order by o.codigo")
})


@Table(name = "GDO_DOCUMENTAL")
public class TipoDocumental implements Serializable {

    public TipoDocumental(){

    }

    public TipoDocumental(String codigo){
        this.codigo = codigo;
    }

    private static final long serialVersionUID = 1L;

    @Id
    private String codigo;

    @Column(name="detido")
    private String nombre;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
