package com.bandeja.entrada.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@NamedQueries({
        @NamedQuery(name = "Entidad.listarTodos", query = "select object(o) from Entidad o where o.indVigilada = 1 order by o.tipoEntidad.codigo, o.codigoEntidad asc"),
        @NamedQuery(name = "Entidad.listarXTipoYEstado", query = "select object(o) from Entidad o where o.tipoEntidad.idTipoEntidad=:tipo and o.estadoEntidad.idEstadoEntidad=:estado"
                + " order by o.codigoEntidad"),
        @NamedQuery(name = "Entidad.listarXTipo", query = "select object(o) from Entidad o where o.tipoEntidad.idTipoEntidad=:tipo"
                + " order by o.codigoEntidad"),
        @NamedQuery(name = "Entidad.FICs", query = "select object(o) from Entidad o where o.tipoEntidad.idTipoEntidad=:idTipoEntidad "
                + " and o.estadoEntidad.idEstadoEntidad = 1 and o.idEntidad not in (select e.entidad.idEntidad from EntidadExcepcionFondo e where e.activo = 1)"
                + " and o.idEntidad != :idEntidad order by o.codigoEntidad"),
        @NamedQuery(name = "Entidad.FICsMismaEntidad", query = "select object(o) from Entidad o where o.tipoEntidad.idTipoEntidad=:idTipoEntidad "
                + " and o.estadoEntidad.idEstadoEntidad = 1 and o.idEntidad not in (select e.entidad.idEntidad from EntidadExcepcionFondo e where e.activo = 1)"
                + " order by o.codigoEntidad"),
        @NamedQuery(name = "Entidad.byTipoYCodigo", query = "select object(o) from Entidad o where o.tipoEntidad.codigo=:tipo and o.codigoEntidad=:codEntidad"
                + " order by o.razonSocial"),
        @NamedQuery(name = "Entidad.listaPorId", query = "select object(o) from Entidad o where o.idEntidad =:idEntidad") })

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Entidad.conRamoSeguro",
                query = "select distinct e.* "
                        + "  from entidad e, ramoseguro r "
                        + " where e.identidad = r.identidad "
                        + " order by e.sigla asc ",resultClass=Entidad.class)
})
public class Entidad implements Serializable {

    private static final long serialVersionUID = -8190462079665397111L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_ENTIDAD")
    @SequenceGenerator(name = "SEC_ENTIDAD", sequenceName = "ENTIDAD_SEQ", allocationSize = 1)
    private Long idEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoEntidad")
    private TipoEntidad tipoEntidad;

    @Column(name = "codigoEntidad")
    private Long codigoEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstadoEntidad")
    private EstadoEntidad estadoEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCIIU")
    private CIIU ciiu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClaseEntidad")
    private ClaseEntidad claseEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSituacionLegal")
    private SituacionLegal situacionLegal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoDocumento")
    private TipoDocumento tipoDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idNivelVigilancia")
    private NivelVigilancia nivelVigilancia;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "idDomicilioPpal")
    private Domicilio domicilioPPal;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "idDomicilioNot")
    private Domicilio domicilioNot;

    @Temporal(TemporalType.DATE)
    private Date fechaDesdeVigilada;

    @Temporal(TemporalType.DATE)
    private Date fechaHastaVigilada;

    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;

    @Column(name = "razonSocial")
    private String razonSocial;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "uriPaginaWeb")
    private String uriPaginaWeb;

    @Column(name = "indVigilada")
    private int indVigilada;

    @Column(name = "codigoEntidadSV")
    private Long codigoEntidadSV;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidadVigila")
    private EntidadVigila entidadVigila;

    @Column(name = "numeroIdentificacion")
    private String numeroIdentificacion;

    @Transient
    private String numeroIdentificacionSolip; //NUMTER identificador �nico en SOLIP para las entidades


    public void resetEntities() {
        if (tipoEntidad == null)
            tipoEntidad = new TipoEntidad();
        if (estadoEntidad == null)
            estadoEntidad = new EstadoEntidad();
    }

    /**
     * @return Retorna el valor de tipoEntidad
     */
    public TipoEntidad getTipoEntidad() {
        return tipoEntidad;
    }

    /**
     * Reemplaza el valor de tipoEntidad para este objeto por el enviado en el parametro.
     *
     * @param tipoEntidad
     *            contiene el valor para reemplazar tipoEntidad en el objeto.
     */
    public void setTipoEntidad(TipoEntidad tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    /**
     * @return Retorna el valor de codigoEntidad
     */
    public Long getCodigoEntidad() {
        return codigoEntidad;
    }

    /**
     * Reemplaza el valor de codigoEntidad para este objeto por el enviado en el parametro.
     *
     * @param codigoEntidad
     *            contiene el valor para reemplazar codigoEntidad en el objeto.
     */
    public void setCodigoEntidad(Long codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    /**
     * @return Retorna el valor de estadoEntidad
     */
    public EstadoEntidad getEstadoEntidad() {
        return estadoEntidad;
    }

    /**
     * Reemplaza el valor de estadoEntidad para este objeto por el enviado en el parametro.
     *
     * @param estadoEntidad
     *            contiene el valor para reemplazar estadoEntidad en el objeto.
     */
    public void setEstadoEntidad(EstadoEntidad estadoEntidad) {
        this.estadoEntidad = estadoEntidad;
    }

    /**
     * Retorna el valor actual nivelVigilancia (getter)
     *
     * @author Camilo Cruz UT SunGemini-Zabala II 3/02/2011 15:11:07
     * @return nivelVigilancia actual
     */
    public NivelVigilancia getNivelVigilancia() {
        return nivelVigilancia;
    }

    /**
     * Sustituye el valor actual de nivelVigilancia por el valor ingresado en nivelVigilancia (setter)
     *
     * @author Camilo Cruz UT SunGemini-Zabala II 3/02/2011 15:11:07
     * @param nivelVigilancia
     *            valor a actualizar
     */
    public void setNivelVigilancia(NivelVigilancia nivelVigilancia) {
        this.nivelVigilancia = nivelVigilancia;
    }

    @Override
    public String toString() {
        return (tipoEntidad!=null?tipoEntidad.getCodigo():"null") + "-" + codigoEntidad + " " + razonSocial;
    }

    public int getIndVigilada() {
        return indVigilada;
    }

    public void setIndVigilada(int indVigilada) {
        this.indVigilada = indVigilada;
    }

    /**
     * @return the idEntidad
     */
    public Long getIdEntidad() {
        return idEntidad;
    }

    /**
     * @param idEntidad
     *            the idEntidad to set
     */
    public void setIdEntidad(Long idEntidad) {
        this.idEntidad = idEntidad;
    }

    /**
     * @return the ciiu
     */
    public CIIU getCiiu() {
        return ciiu;
    }

    /**
     * @param ciiu
     *            the ciiu to set
     */
    public void setCiiu(CIIU ciiu) {
        this.ciiu = ciiu;
    }

    /**
     * @return the claseEntidad
     */
    public ClaseEntidad getClaseEntidad() {
        return claseEntidad;
    }

    /**
     * @param claseEntidad
     *            the claseEntidad to set
     */
    public void setClaseEntidad(ClaseEntidad claseEntidad) {
        this.claseEntidad = claseEntidad;
    }

    /**
     * @return the situacionLegal
     */
    public SituacionLegal getSituacionLegal() {
        return situacionLegal;
    }

    /**
     * @param situacionLegal
     *            the situacionLegal to set
     */
    public void setSituacionLegal(SituacionLegal situacionLegal) {
        this.situacionLegal = situacionLegal;
    }

    /**
     * @return the tipoDocumento
     */
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento
     *            the tipoDocumento to set
     */
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the domicilioPPal
     */
    public Domicilio getDomicilioPPal() {
        return domicilioPPal;
    }

    /**
     * @param domicilioPPal
     *            the domicilioPPal to set
     */
    public void setDomicilioPPal(Domicilio domicilioPPal) {
        this.domicilioPPal = domicilioPPal;
    }

    /**
     * @return the domicilioNot
     */
    public Domicilio getDomicilioNot() {
        return domicilioNot;
    }

    /**
     * @param domicilioNot
     *            the domicilioNot to set
     */
    public void setDomicilioNot(Domicilio domicilioNot) {
        this.domicilioNot = domicilioNot;
    }

    /**
     * @return the fechaDesdeVigilada
     */
    public Date getFechaDesdeVigilada() {
        return fechaDesdeVigilada;
    }

    /**
     * @param fechaDesdeVigilada
     *            the fechaDesdeVigilada to set
     */
    public void setFechaDesdeVigilada(Date fechaDesdeVigilada) {
        this.fechaDesdeVigilada = fechaDesdeVigilada;
    }

    /**
     * @return the fechaHastaVigilada
     */
    public Date getFechaHastaVigilada() {
        return fechaHastaVigilada;
    }

    /**
     * @param fechaHastaVigilada
     *            the fechaHastaVigilada to set
     */
    public void setFechaHastaVigilada(Date fechaHastaVigilada) {
        this.fechaHastaVigilada = fechaHastaVigilada;
    }

    /**
     * @return the fechaActualizacion
     */
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion
     *            the fechaActualizacion to set
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial
     *            the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * Retorna la sigla de la entidad si y solo si no se encuentra vacia ni contiene palabras que indican que este dato
     * no existe, en caso contrario retorna la razon social
     * @return the sigla
     */
    public String getSigla() {
        String tipoEnt = tipoEntidad.getCodigo();
        String siglaTmp = "";
        boolean errorSigla = tipoEnt.equals("095") || tipoEnt.equals("030") || tipoEnt.equals("041")
                || tipoEnt.equals("110") || tipoEnt.equals("056") || tipoEnt.equals("085") || tipoEnt.equals("502")
                || tipoEnt.equals("102") || tipoEnt.equals("043") || tipoEnt.equals("053") || tipoEnt.equals("103")
                || tipoEnt.equals("058") || tipoEnt.equals("106") || tipoEnt.equals("501") || tipoEnt.equals("080")
                || tipoEnt.equals("087") || tipoEnt.equals("401") || tipoEnt.equals("066") || tipoEnt.equals("031")
                || tipoEnt.equals("092");
        if (!errorSigla) {
            siglaTmp = (sigla == null ? "" : sigla.trim().toUpperCase().replaceAll("\\.", "").replaceAll("-", "")
                    .replaceAll(" ", "").replaceAll("/", "").replaceAll("#", "").replaceAll("�", "A")
                    .replaceAll("�", "E").replaceAll("�", "I").replaceAll("�", "O").replaceAll("�", "U")
                    .replaceAll("�", "U"));
            errorSigla = siglaTmp.isEmpty()
                    || (!siglaTmp.isEmpty() && (siglaTmp.equals("NA") || siglaTmp.equals("NO")
                    || siglaTmp.indexOf("NOAPLICA") > -1 || siglaTmp.indexOf("SINNOMBRE") > -1
                    || siglaTmp.indexOf("NONAME") > -1 || siglaTmp.indexOf("NOTIENENOMBRE") > -1
                    || siglaTmp.indexOf("NOREGISTRA") > -1 || siglaTmp.indexOf("NOREPORTA") > -1));
        }
        if (errorSigla) {
            return razonSocial;
        } else {
            return sigla;
        }
    }

    public String getNombre(){
        return tipoEntidad.getTipoEntidad() + "-" + this.codigoEntidad + " " + this.getSigla();
    }

    /**
     * @param sigla the sigla to set
     */
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    /**
     * @return the uriPaginaWeb
     */
    public String getUriPaginaWeb() {
        return uriPaginaWeb;
    }

    /**
     * @param uriPaginaWeb
     *            the uriPaginaWeb to set
     */
    public void setUriPaginaWeb(String uriPaginaWeb) {
        this.uriPaginaWeb = uriPaginaWeb;
    }

    /**
     * @return the codigoEntidadSV
     */
    public Long getCodigoEntidadSV() {
        return codigoEntidadSV;
    }

    /**
     * @param codigoEntidadSV
     *            the codigoEntidadSV to set
     */
    public void setCodigoEntidadSV(Long codigoEntidadSV) {
        this.codigoEntidadSV = codigoEntidadSV;
    }

    /**
     * @return the entidadVigila
     */
    public EntidadVigila getEntidadVigila() {
        return entidadVigila;
    }

    /**
     * @param entidadVigila
     *            the entidadVigila to set
     */
    public void setEntidadVigila(EntidadVigila entidadVigila) {
        this.entidadVigila = entidadVigila;
    }

    /**
     * @return the numeroIdentificacion
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * @param numeroIdentificacion
     *            the numeroIdentificacion to set
     */
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNumeroIdentificacionSolip() {
        return numeroIdentificacionSolip;
    }

    public void setNumeroIdentificacionSolip(String numeroIdentificacionSolip) {
        this.numeroIdentificacionSolip = numeroIdentificacionSolip;
    }

}