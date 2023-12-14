package com.bandeja.entrada.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Entidad Padre de todos los tramites solicitados Contiene la informacion basica del tramite
 *
 * @author UTSunGemini-Zabala II- Camilo Cruz 7/07/2010
 *
 */
// @Cache(type=CacheType.NONE)
@Entity
@NamedQueries({
        @NamedQuery(name = "TramiteSolicitado.listarTodos", query = "select object(o) from TramiteSolicitado o order by o.idTramiteSolicitado DESC "),

        @NamedQuery(name = "TramiteSolicitado.listaElaboracion", query = "select object(o) from TramiteSolicitado o "
                + " where o.estadoTramite=:estado and o.appUser.idAppUser=:idAppUser and o.tipoTramite.codigoTramite=:codTramite order by o.idTramiteSolicitado DESC "),

        @NamedQuery(name = "TramiteSolicitado.listaPorAppUserYTipoTramite", query = "select object(o) from TramiteSolicitado o "
                + " where o.appUser.idAppUser=:idAppUser and o.tipoTramite.idTipoTramite=:idTipoTramite order by o.idTramiteSolicitado DESC"),

        @NamedQuery(name = "TramiteSolicitado.lista2Estado", query = "select object(o) from TramiteSolicitado o "
                + " where o.estadoTramite in (:estado1, :estado2)  and o.appUser.idAppUser=:idAppUser and o.tipoTramite.codigoTramite=:codTramite order by o.idTramiteSolicitado DESC"),

        @NamedQuery(name = "TramiteSolicitado.obtenerTramite", query = "select object(o) from TramiteSolicitado o where o.idTramiteSolicitado=:idTramiteSolicitado"),

        @NamedQuery(name = "TramiteSolicitado.tramiteXusuario", query = "select object(o) from TramiteSolicitado o where o.appUser.idAppUser=:idAppUser order by o.idTramiteSolicitado DESC"),

        @NamedQuery(name = "TramiteSolicitado.obtenerTramiteByClaveFed", query = "select object(o) from TramiteSolicitado o where o.appUser.login =:claveFed")

})
public class TramiteSolicitado implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 376984408193746711L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_TRAMITESOLICITADO")
    @SequenceGenerator(name = "SEC_TRAMITESOLICITADO", sequenceName = "TRAMITESOLICITADO_SEQ", allocationSize = 1)
    private Long idTramiteSolicitado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoTramite")
    private TipoTramite tipoTramite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstadoTramite")
    private EstadoTramite estadoTramite;

    @JoinColumn(name = "idEntidad")
    private Entidad entidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSeccion")
    private Seccion seccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAppUser")
    private AppUser appUser;

    @Transient
    private TipoDocumental tipoDocumental;

    @Column(name = "numeroFolios")
    private int numeroFolios;

    @Column(name = "numeroRadicado")
    private String numeroRadicado;

    @Temporal(TemporalType.DATE)
    private Date fechaRadicacion;

    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;

    @OneToMany(mappedBy = "tramiteSolicitado", /*cascade = CascadeType.ALL,*/ fetch = FetchType.LAZY)
    private Collection<Archivo> listaArchivos;

    @Transient
    private Integer seccionFinal;

    @Transient
    private NegocioFiduciario negocioCedente;

    @Transient
    private NegocioFiduciario negocioCesionario;

    @Transient
    private NegocioFiduciario negocioEscision;

    @Transient
    private String clasificacionClase;

    @Transient
    private String clasificacionTipo;

    @Transient
    private String clasificacionMotivo;

    @Transient
    private String destra; //Atributo temporal para manejar la descripci�n de los tramites de respuesta en SOLIP

    @Transient
    private BigDecimal codtra; //Atributo temporal

    //PRUEBA
    @Transient
    private boolean notifica;
    @Transient
    private Integer idtipnoti;
    @Transient
    private String desnoti;
    /*@Transient
    private String direccion;*/

    //FIN PRUEBA


    private String nombreFIC;
    private Integer idFICCedente;
    private String NombreFamiliaFic;

    private Integer idFICEscision;
    private String nombreProductoEscision;
    @ManyToOne
    @JoinColumn(name = "idEntidadCedente")
    private Entidad entidadCedente;
    private Integer idFICCesionario;
    private Boolean ficEnOperacion;
    @ManyToOne
    @JoinColumn(name = "idTipoFamiliaFic")
    private TipoFamiliaFic tipoFamiliaFic;


    /**
     * @return the fechaRadicacion
     */
    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    /**
     * @param fechaRadicacion
     *            the fechaRadicacion to set
     */
    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    /**
     * Retorna el valor actual idTramiteSolicitado (getter)
     *
     * @author Camilo Cruz 7/07/2010 17:07:37
     * @return idTramiteSolicitado actual
     */
    public Long getIdTramiteSolicitado() {
        return idTramiteSolicitado;
    }

    /**
     * Sustituye el valor actual de idTramiteSolicitado por el valor ingresado en idTramiteSolicitado (setter)
     *
     * @author Camilo Cruz 7/07/2010 17:07:37
     * @param idTramiteSolicitado
     *            valor a actualizar
     */
    public void setIdTramiteSolicitado(Long idTramiteSolicitado) {
        this.idTramiteSolicitado = idTramiteSolicitado;
    }

    /**
     * @author Camilo Cruz 3/06/2010 15:17:42
     * @return the tipoTramite
     */
    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }

    /**
     * Retorna el valor actual appUser (getter)
     *
     * @author Camilo Cruz 7/07/2010 17:07:09
     * @return appUser actual
     */
    public AppUser getAppUser() {
        return appUser;
    }

    /**
     * Sustituye el valor actual de appUser por el valor ingresado en appUser (setter)
     *
     * @author Camilo Cruz 7/07/2010 17:07:09
     * @param appUser
     *            valor a actualizar
     */
    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    /**
     * Retorna el valor actual estadoTramite (getter)
     *
     * @author Camilo Cruz 7/07/2010 17:07:09
     * @return estadoTramite actual
     */
    public EstadoTramite getEstadoTramite() {
        return estadoTramite;
    }

    /**
     * Sustituye el valor actual de estadoTramite por el valor ingresado en estadoTramite (setter)
     *
     * @author Camilo Cruz 7/07/2010 17:07:09
     * @param estadoTramite
     *            valor a actualizar
     */
    public void setEstadoTramite(EstadoTramite estadoTramite) {
        this.estadoTramite = estadoTramite;
    }

    /**
     * @author Camilo Cruz 3/06/2010 15:17:43
     * @param tipoTramite
     *            the tipoTramite to set
     */
    public void setTipoTramite(TipoTramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    /**
     * @return the fechamodificacion
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechamodificacion
     *            the fechamodificacion to set
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * @return el id del tr�mite como string para validar
     */
    public String getStringIdTramite() {
        return getIdTramiteSolicitado().toString();
    }

    public Entidad getEntidad() {
        return entidad;
    }

    /**
     * @param entidad
     *            the entidad to set
     */
    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    /**
     * @return the numeroFolios
     */
    public int getNumeroFolios() {
        return numeroFolios;
    }

    /**
     * @param numeroFolios
     *            the numeroFolios to set
     */
    public void setNumeroFolios(int numeroFolios) {
        this.numeroFolios = numeroFolios;
    }

    /**
     * @return the numeroRadicado
     */
    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    /**
     * @param numeroRadicado
     *            the numeroRadicado to set
     */
    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public Collection<Archivo> getListaArchivos() {
        return listaArchivos;
    }

    public void setListaArchivos(Collection<Archivo> listaArchivos) {
        this.listaArchivos = listaArchivos;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Integer getSeccionFinal() {
        return seccionFinal;
    }

    public void setSeccionFinal(Integer seccionFinal) {
        this.seccionFinal = seccionFinal;
    }

    public TipoDocumental getTipoDocumental() {
        return tipoDocumental;
    }

    public void setTipoDocumental(TipoDocumental tipoDocumental) {
        this.tipoDocumental = tipoDocumental;
    }

    public String getDestra() {
        return destra;
    }

    public void setDestra(String destra) {
        this.destra = destra;
    }

    public String getNombreFIC() {
        return nombreFIC;
    }

    public void setNombreFIC(String nombreFIC) {
        this.nombreFIC = nombreFIC;
    }

    public Integer getIdFICCedente() {
        return idFICCedente;
    }

    public void setIdFICCedente(Integer idFICCedente) {
        this.idFICCedente = idFICCedente;
    }

    public Integer getIdFICCesionario() {
        return idFICCesionario;
    }

    public void setIdFICCesionario(Integer idFICCesionario) {
        this.idFICCesionario = idFICCesionario;
    }

    public Entidad getEntidadCedente() {
        return entidadCedente;
    }

    public void setEntidadCedente(Entidad entidadCedente) {
        this.entidadCedente = entidadCedente;
    }

    public NegocioFiduciario getNegocioCesionario() {
        return negocioCesionario;
    }

    public void setNegocioCesionario(NegocioFiduciario negocioCesionario) {
        this.negocioCesionario = negocioCesionario;
    }

    public NegocioFiduciario getNegocioCedente() {
        return negocioCedente;
    }

    public void setNegocioCedente(NegocioFiduciario negocioCedente) {
        this.negocioCedente = negocioCedente;
    }

    public NegocioFiduciario getNegocioEscision() {
        return negocioEscision;
    }

    public void setNegocioEscision(NegocioFiduciario negocioEscision) {
        this.negocioEscision = negocioEscision;
    }

    public Boolean getFicEnOperacion() {
        return ficEnOperacion;
    }

    public void setFicEnOperacion(Boolean ficEnOperacion) {
        this.ficEnOperacion = ficEnOperacion;
    }

    public TipoFamiliaFic getTipoFamiliaFic() {
        return tipoFamiliaFic;
    }

    public void setTipoFamiliaFic(TipoFamiliaFic tipoFamiliaFic) {
        this.tipoFamiliaFic = tipoFamiliaFic;
    }

    public String getNombreFamiliaFic() {
        return NombreFamiliaFic;
    }

    public void setNombreFamiliaFic(String nombreFamiliaFic) {
        NombreFamiliaFic = nombreFamiliaFic;
    }

    public String getClasificacionClase() {
        return clasificacionClase;
    }

    public void setClasificacionClase(String clasificacionClase) {
        this.clasificacionClase = clasificacionClase;
    }

    public String getClasificacionTipo() {
        return clasificacionTipo;
    }

    public void setClasificacionTipo(String clasificacionTipo) {
        this.clasificacionTipo = clasificacionTipo;
    }

    public String getClasificacionMotivo() {
        return clasificacionMotivo;
    }

    public void setClasificacionMotivo(String clasificacionMotivo) {
        this.clasificacionMotivo = clasificacionMotivo;
    }

    public BigDecimal getCodtra() {
        return codtra;
    }

    public void setCodtra(BigDecimal codtra) {
        this.codtra = codtra;
    }

    public Integer getIdFICEscision() {
        return idFICEscision;
    }

    public void setIdFICEscision(Integer idFICEscision) {
        this.idFICEscision = idFICEscision;
    }

    public String getNombreProductoEscision() {
        return nombreProductoEscision;
    }

    public void setNombreProductoEscision(String nombreProductoEscision) {
        this.nombreProductoEscision = nombreProductoEscision;
    }

    public Integer getIdtipnoti() {
        return idtipnoti;
    }

    public void setIdtipnoti(Integer idtipnoti) {
        this.idtipnoti = idtipnoti;
    }

    public String getDesnoti() {
        return desnoti;
    }

    public void setDesnoti(String desnoti) {
        this.desnoti = desnoti;
    }

    public boolean isNotifica() {
        return notifica;
    }

    public void setNotifica(boolean notifica) {
        this.notifica = notifica;
    }

}
