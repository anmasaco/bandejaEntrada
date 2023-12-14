package com.bandeja.entrada.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name ="EntidadExcepcionFondo.porEntidad", query = "select object(o) from EntidadExcepcionFondo o where o.entidad.idEntidad = :idEntidad and o.activo = 1 ")
})
public class EntidadExcepcionFondo implements Serializable{

    private static final long serialVersionUID = 20171013090101L;

    @Id
    private Integer idEntidadExcepcionFondo;
    private boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad",insertable=false, updatable=false)
    private Entidad entidad;

    public Integer getIdEntidadExcepcionFondo() {
        return idEntidadExcepcionFondo;
    }

    public void setIdEntidadExcepcionFondo(Integer idEntidadExcepcionFondo) {
        this.idEntidadExcepcionFondo = idEntidadExcepcionFondo;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
