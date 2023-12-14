package com.bandeja.entrada.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;


/**
 * Entidad
 *
 * @author UT SunGemini-Zabala II
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "TipoTramiteXUsuario.listarTodos", query = "select object(o) from TipoTramiteXUsuario o"),
        @NamedQuery(name = "TipoTramiteXUsuario.findByUSer", query = "select object(o) from TipoTramiteXUsuario o where o.appUser.idAppUser=:usuarioID") })
public class TipoTramiteXUsuario implements Serializable {

    /**
     * Campo para identificar la clase en una serializacion
     * @author Camilo Cruz 4/06/2010 11:02:10
     */
    private static final long serialVersionUID = -8664221264920001304L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_TIPOTRAMITEXUSUARIO")
    @SequenceGenerator(name = "SEC_TIPOTRAMITEXUSUARIO", sequenceName = "TIPOTRAMITEXUSUARIO_SEQ", allocationSize = 1)
    private Long idTipoTramiteUsuario;

    // Atributos de Asociaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAppUser")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoTramite")
    private TipoTramite tramite;

    private Long numeroMaximoDeTramite;

    private boolean requiereRadicado;

    @Column(nullable = false, precision = 6)
    private Long idAppUserAud;

    @Column(length = 20)
    private String ipCliente;

    /**
     * Retorna el valor actual idTipoTramiteUsuario (getter)
     * @author Camilo Cruz 4/06/2010 10:38:50
     * @return el/la idTipoTramiteUsuario
     */
    public Long getIdTipoTramiteUsuario() {
        return idTipoTramiteUsuario;
    }

    /**
     * Sustituye el valor actual de idTipoTramiteUsuario por el valor ingresado en idTipoTramiteUsuario (setter)
     * @author Camilo Cruz 4/06/2010 10:38:50
     * @param idTipoTramiteUsuario
     *            nuevo valor
     */
    public void setIdTipoTramiteUsuario(Long idTipoTramiteUsuario) {
        this.idTipoTramiteUsuario = idTipoTramiteUsuario;
    }

    /**
     * Retorna el valor actual appUser (getter)
     *
     * @author Camilo Cruz 7/07/2010 17:22:15
     * @return appUser actual
     */
    public AppUser getAppUser() {
        return appUser;
    }

    /**
     * Sustituye el valor actual de appUser por el valor ingresado en appUser (setter)
     * @author Camilo Cruz 7/07/2010 17:22:15
     * @param appUser
     *            valor a actualizar
     */
    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    /**
     * Retorna el valor actual tramite (getter)
     * @author Camilo Cruz 7/07/2010 17:22:15
     * @return tramite actual
     */
    public TipoTramite getTramite() {
        return tramite;
    }

    /**
     * Sustituye el valor actual de tramite por el valor ingresado en tramite (setter)
     *
     * @author Camilo Cruz 7/07/2010 17:22:15
     * @param tramite
     *            valor a actualizar
     */
    public void setTramite(TipoTramite tramite) {
        this.tramite = tramite;
    }

    /**
     * @return the numeroMaximoDeTramite
     */
    public Long getNumeroMaximoDeTramite() {
        return numeroMaximoDeTramite;
    }

    /**
     * @param numeroMaximoDeTramite
     *            the numeroMaximoDeTramite to set
     */
    public void setNumeroMaximoDeTramite(Long numeroMaximoDeTramite) {
        this.numeroMaximoDeTramite = numeroMaximoDeTramite;
    }

    /**
     * @return the requiereRadicado
     */
    public boolean isRequiereRadicado() {
        return requiereRadicado;
    }

    /**
     * @param requiereRadicado
     *            the requiereRadicado to set
     */
    public void setRequiereRadicado(boolean requiereRadicado) {
        this.requiereRadicado = requiereRadicado;
    }

    public Long getIdAppUserAud() {
        return idAppUserAud;
    }

    public void setIdAppUserAud(Long idAppUserAud) {
        this.idAppUserAud = idAppUserAud;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if (obj != null && obj instanceof TipoTramiteXUsuario) {
            TipoTramiteXUsuario ttxu = (TipoTramiteXUsuario) obj;
            try {
                res = (appUser.getIdAppUser().equals(ttxu.getAppUser().getIdAppUser()) &&
                        tramite.getIdTipoTramite() == ttxu.getTramite().getIdTipoTramite());
            } catch (Exception e) {
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "TipoTramiteXUsuario [idTipoTramiteUsuario=" + idTipoTramiteUsuario + ", idAppUser=" + appUser.getIdAppUser()
                + ", tramite=" + tramite + ", idAppUserAud=" + idAppUserAud + ", ipCliente=" + ipCliente + "]\n";
    }

}
