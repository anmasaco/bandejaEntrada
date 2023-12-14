package com.bandeja.entrada.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;


@Entity
@NamedQueries({
        @NamedQuery(name = "TipoEntidadFondo.activasPorTipoEntidad", query="select object(o) from TipoEntidadFondo o where o.tipoEntidad.idTipoEntidad = :idTipoEntidad and o.activo = true")
}
)
public class TipoEntidadFondo implements Serializable{

    private static final long serialVersionUID = 20171012144301L;
    @Id
    private Integer idTipoEntidadFondo;
    private String cargoFuncionario;
    private boolean activo;
    private String cedula;
    @Transient
    private GdoFuncionario gdoFuncionario;

    @ManyToOne
    @JoinColumn(name ="idTipoEntidad", insertable = false, updatable = false)
    private TipoEntidad tipoEntidad;

    public TipoEntidad getTipoEntidad() {
        return tipoEntidad;
    }
    public void setTipoEntidad(TipoEntidad tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }
    public Integer getIdTipoEntidadFondo() {
        return idTipoEntidadFondo;
    }
    public void setIdTipoEntidadFondo(Integer idTipoEntidadFondo) {
        this.idTipoEntidadFondo = idTipoEntidadFondo;
    }
    public String getCargoFuncionario() {
        return cargoFuncionario;
    }
    public void setCargoFuncionario(String cargoFuncionario) {
        this.cargoFuncionario = cargoFuncionario;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public GdoFuncionario getGdoFuncionario() {
        return gdoFuncionario;
    }
    public void setGdoFuncionario(GdoFuncionario gdoFuncionario) {
        this.gdoFuncionario = gdoFuncionario;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

}
