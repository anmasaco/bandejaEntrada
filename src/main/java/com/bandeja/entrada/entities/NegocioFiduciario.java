package com.bandeja.entrada.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

;


@Entity
@NamedQueries({
        @NamedQuery(name = "NegocioFiduciario.porTipoYCodEntidad", query = "select object(o) from NegocioFiduciario o where o.tipoEntidad = :tipoEntidad "
                + "and o.codigoEntidad = :codigoEntidad and o.idTipo = 9 and o.idSubTipo in (1,2,3,6) and o.radicado = 1 and o.cerrado = 0 order by o.codigo"),
        @NamedQuery(name = "NegocioFiduciario.porTipoYCodEntidadFCP", query = "select object(o) from NegocioFiduciario o where o.tipoEntidad = :tipoEntidad "
                + "and o.codigoEntidad = :codigoEntidad and o.idTipo = 9 and o.idSubTipo = 7 and o.radicado = 1 and o.cerrado = 0 order by o.codigo"),
        @NamedQuery(name = "NegocioFiduciario.porID", query = "select object(o) from NegocioFiduciario o where o.idNegocioFiduciario = :idNegocioFiduciario"),
        @NamedQuery(name = "NegocioFiduciario.porCodigo", query = "select object(o) from NegocioFiduciario o where o.codigo = :codNegocioFiduciario")

})
@Table(name = "CndInscripNegocioFid")
public class NegocioFiduciario implements Serializable {

    private static final long serialVersionUID = 20171025102501L;

    private static final int SIN_PADRE = 999999999;
    @Id
    @Column(name = "id")
    private Integer idNegocioFiduciario;
    @Column(name = "tipo_entidad")
    private Integer tipoEntidad;
    @Column(name = "cod_entidad")
    private Integer codigoEntidad;
    @Column(name = "cncodfideico")
    private Integer codigo;
    @Column(name = "cnnomfid")
    private String nombre;
    @Column(name = "cnradicado")
    private Integer radicado;
    @Column(name = "cncerrado")
    private Integer cerrado;
    @Column(name = "cntipneg")
    private Integer idTipo;
    @Column(name = "cnsubtipneg")
    private Integer idSubTipo;
    @Column(name = "cncodfideicopadre")
    private Integer codigoPadre;

    @Transient
    private String nombrePadre = "";

    @Transient
    private Integer numeroHijos = 0;

    @Transient
    private NegocioFiduciario negocioPadre = null;

    // Indica si es principal
    public boolean isPrincipal() {
        return codigoPadre.intValue() == SIN_PADRE;
    }

    // Indica si es hijo
    public boolean isHijo() {
        return codigoPadre.intValue() != SIN_PADRE;
    }

    // Indica si es principal sin hijos
    public boolean isPrincipalSinHijos() {
        return this.isPrincipal() && numeroHijos == 0;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public Integer getNumeroHijos() {
        return numeroHijos;
    }

    public void setNumeroHijos(Integer numeroHijos) {
        this.numeroHijos = numeroHijos;
    }

    public void incrementarNumeroHijos() {
        this.numeroHijos++;
    }

    public Integer getCodigoPadre() {
        return codigoPadre;
    }

    public void setCodigoPadre(Integer codigoPadre) {
        this.codigoPadre = codigoPadre;
    }

    public Integer getIdNegocioFiduciario() {
        return idNegocioFiduciario;
    }

    public void setIdNegocioFiduciario(Integer idNegocioFiduciario) {
        this.idNegocioFiduciario = idNegocioFiduciario;
    }

    public Integer getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(Integer tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    public Integer getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(Integer codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreEscision() {
        return nombre + (isPrincipal()?" (Es principal)":" (Compartimento de: " + this.codigoPadre + ")");
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdSubTipo() {
        return idSubTipo;
    }

    public void setIdSubTipo(Integer idSubTipo) {
        this.idSubTipo = idSubTipo;
    }

    public NegocioFiduciario getNegocioPadre() {
        return negocioPadre;
    }

    public void setNegocioPadre(NegocioFiduciario negocioPadre) {
        this.negocioPadre = negocioPadre;
    }

    public String getNombreNegocioPadre() {
        return (negocioPadre != null ? negocioPadre.getCodigo() + " - " + negocioPadre.getNombre() : "");
    }

    public String getNombreNegocioPadreEscision() {
        return (negocioPadre != null ? " (perteneciente al " + negocioPadre.getNombre() + ")" : "");
    }

    public String getNombreNegocioEscision() {
        return codigo + " - " + nombre + getNombreNegocioPadreEscision();
    }

}
