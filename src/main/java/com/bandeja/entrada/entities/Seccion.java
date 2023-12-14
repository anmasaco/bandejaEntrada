package com.bandeja.entrada.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@NamedQueries({
        @NamedQuery(name = "Seccion.listarTodos", query = "SELECT object(o) FROM Seccion o"),
        @NamedQuery(name = "Seccion.listarByTipoTramite", query = "SELECT object(o) FROM Seccion o WHERE o.tipoTramite.idTipoTramite=:idTipoTramite order by o.nivel, o.orden ") })
@Table(name = "SECCION")
public class Seccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_SECCION")
    @SequenceGenerator(name = "SEC_SECCION", sequenceName = "SECCION_SEQ", allocationSize = 1)
    private Integer idSeccion;
    private int idSeccionPadre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoTramite")
    private TipoTramite tipoTramite;
    private int nivel;
    private int orden;
    private boolean esVisible;
    private String nombre;
    //@OneToMany(mappedBy = "seccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private Set<Requisito> listaRequisitos;
    @Transient
    private String nombreCampo;

    public Seccion() {
    }

    public Integer getIdSeccion() {
        return this.idSeccion;
    }

    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }

    public int getIdSeccionPadre() {
        return this.idSeccionPadre;
    }

    public void setIdSeccionPadre(int idSeccionPadre) {
        this.idSeccionPadre = idSeccionPadre;
    }

    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(TipoTramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public int getNivel() {
        return this.nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
    public Set<Requisito> getListaRequisitos() {
	return listaRequisitos;
    }

    public void setListaRequisitos(Set<Requisito> listaRequisitos) {
	this.listaRequisitos = listaRequisitos;
    }
    */

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getNombreCampo() {
        return "seccion_" + orden;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public boolean isEsVisible() {
        return esVisible;
    }

    public void setEsVisible(boolean esVisible) {
        this.esVisible = esVisible;
    }

}
