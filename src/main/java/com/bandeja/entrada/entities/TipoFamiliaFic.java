package com.bandeja.entrada.entities;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name="TipoFamiliaFic.todos", query = "select object(o) from TipoFamiliaFic o"),
        @NamedQuery(name="TipoFamiliaFic.porId", query = "select object(o) from TipoFamiliaFic o where o.idTipoFamiliaFic = :idTipoFamiliaFic")
})
public class TipoFamiliaFic implements Serializable {

    private static final long serialVersionUID = 201915153601L;

    @Id
    private Integer idTipoFamiliaFic;
    private String nombre;
    public Integer getIdTipoFamiliaFic() {
        return idTipoFamiliaFic;
    }
    public void setIdTipoFamiliaFic(Integer idTipoFamiliaFic) {
        this.idTipoFamiliaFic = idTipoFamiliaFic;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
