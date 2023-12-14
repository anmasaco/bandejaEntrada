package com.bandeja.entrada.entities;

import com.bandeja.entrada.utils.Formato;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@NamedQueries({
        @NamedQuery(name = "Requisito.listarTodos", query = "select object(o) from Requisito o order by o.orden"),

        @NamedQuery(name = "Requisito.listarRequisitoXTipoTramite", query = "select object(o) from Requisito o "
                + " where o.tipoTramite.idTipoTramite=:idTipoTramite order by o.orden") })
public class Requisito implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_REQUISITO")
    @SequenceGenerator(name = "SEC_REQUISITO", sequenceName = "REQUISITO_SEQ", allocationSize = 1)
    private int idRequisito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoTramite", insertable = false, updatable = false)
    private TipoTramite tipoTramite;

    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(TipoTramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }
    @Basic(fetch= FetchType.LAZY)
    @Lob
    private String descripcion;
    private boolean esObligatorio;
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    private String nombre;
    private String observacion;
    private int orden;
    private boolean esRequisito;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSeccion")
    private Seccion seccion;
    @Transient
    private String nombreCampo;

    public Requisito() {
    }

    public int getIdRequisito() {
        return this.idRequisito;
    }

    public void setIdRequisito(int idRequisito) {
        this.idRequisito = idRequisito;
    }

    public String getDescripcion() {
        return Formato.formatoHtml(this.descripcion);
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEsObligatorio() {
        return this.esObligatorio;
    }

    public void setEsObligatorio(boolean esObligatorio) {
        this.esObligatorio = esObligatorio;
    }

    public Date getFechaActualizacion() {
        return this.fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservacion() {
        return (this.observacion!=null?this.observacion:null);
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getOrden() {
        return this.orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Seccion getSeccion() {
        return this.seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public String getNombreCampo() {
        return "requisito_" + idRequisito;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public boolean isEsRequisito() {
        return esRequisito;
    }

    public void setEsRequisito(boolean esRequisito) {
        this.esRequisito = esRequisito;
    }
}
