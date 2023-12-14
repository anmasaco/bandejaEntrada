package com.bandeja.entrada.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
        @NamedQuery(name = "TipoSolicitante.listarTodos", query = "select object(o) from TipoSolicitante o where o.idTipoSolicitante > 3 order by o.idTipoSolicitante"),
        @NamedQuery(name = "TipoSolicitante.buscarPorNombre", query = "select object(o) from TipoSolicitante o where o.nombre=:nom"),
        @NamedQuery(name = "TipoSolicitante.casillero", query = "select object(o) from TipoSolicitante o where o.idRolFederalizacion =:idRolFederalizacion")})

@Table(name = "TIPOSOLICITANTE")
public class TipoSolicitante implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idTipoSolicitante;
    private String nombre;
    private Integer idRolFederalizacion;

    public TipoSolicitante() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false, precision = 22)
    public int getIdTipoSolicitante() {
        return this.idTipoSolicitante;
    }

    public void setIdTipoSolicitante(int idTipoSolicitante) {
        this.idTipoSolicitante = idTipoSolicitante;
    }

    @Column(length = 50)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return this.nombre;
    }

    public Integer getIdRolFederalizacion() {
        return idRolFederalizacion;
    }

    public void setIdRolFederalizacion(Integer idRolFederalizacion) {
        this.idRolFederalizacion = idRolFederalizacion;
    }
}
