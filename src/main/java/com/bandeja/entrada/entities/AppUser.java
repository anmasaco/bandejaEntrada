package com.bandeja.entrada.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Esta entidad representa los usuarios que pueden iniciar sesion dentro del sistema
 * Esta clase ha sido actualizada por Elvar A. Mosquera. (SFC)
 * @author UT SunGemini-Zabala II
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "AppUser.listarTodosNotRol", query = "select object(o) from AppUser o where o.login like :loginFiltro order by o.login"),
        @NamedQuery(name = "AppUser.listarTodosByRol", query = "select object(o) from AppUser o order by o.activo, o.login"),
        @NamedQuery(name = "AppUser.listarTodos", query = "select object(o) from AppUser o order by o.login"),
        @NamedQuery(name = "AppUser.findByLogin", query = "select object(o) from AppUser o where o.login=:log"),
        @NamedQuery(name = "AppUser.findByIdAppUser", query = "select object(o) from AppUser o "
                + "where o.idAppUser =:idAppUser")

})
public class AppUser implements Serializable {


    private static final long serialVersionUID = 5603394118177262340L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_APPUSER")
    @SequenceGenerator(name = "SEC_APPUSER", sequenceName = "APPUSER_SEQ", allocationSize = 1)
    private Long idAppUser;
    private String login;
    private boolean activo;
    private Integer intentos;

    @Column(nullable = false, precision = 6)
    private Long idAppUserAud;

    @Column(length = 20)
    private String ipCliente;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appUser")
    private List<AppUserRol> appUserRols;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoSolicitante")
    private TipoSolicitante tipoSolicitante;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
    private Collection<TramiteSolicitado> listaTramiteSolicitado;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<TipoTramiteXUsuario> listaTramiteXUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad")
    private Entidad entidad;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Transient
    private List<TipoSolicitante> tiposSolicitante;

    /**
     * @return Retorna el valor de idAppUser
     */
    public Long getIdAppUser() {
        return idAppUser;
    }

    /**
     * Reemplaza el valor de idAppUser para este objeto por el enviado en el parametro.
     *
     * @param idAppUser
     *            contiene el valor para reemplazar idAppUser en el objeto.
     */
    public void setIdAppUser(Long idAppUser) {
        this.idAppUser = idAppUser;
    }

    /**
     * @return Retorna el valor de login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Reemplaza el valor de login para este objeto por el enviado en el parametro.
     *
     * @param login
     *            contiene el valor para reemplazar login en el objeto.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retorna el valor actual listaTramiteSolicitado (getter)
     *
     * @author Camilo Cruz 4/06/2010 11:11:50
     * @return el/la listaTramiteSolicitado
     */
    public Collection<TramiteSolicitado> getListaTramiteSolicitado() {
        return listaTramiteSolicitado;
    }

    /**
     * Sustituye el valor actual de listaTramiteSolicitado por el valor ingresado en listaTramiteSolicitado (setter)
     *
     * @author Camilo Cruz 4/06/2010 11:11:50
     * @param listaTramiteSolicitado
     *            el/la nuevo valor para listaTramiteSolicitado
     */
    public void setListaTramiteSolicitado(Collection<TramiteSolicitado> listaTramiteSolicitado) {
        this.listaTramiteSolicitado = listaTramiteSolicitado;
    }

    /**
     * Retorna el valor actual tipoSolicitante (getter)
     *
     * @author Camilo Cruz 4/06/2010 11:21:55
     * @return el/la tipoSolicitante
     */
    public TipoSolicitante getTipoSolicitante() {
        return tipoSolicitante;
    }

    /**
     * Sustituye el valor actual de tipoSolicitante por el valor ingresado en tipoSolicitante (setter)
     *
     * @author Camilo Cruz 4/06/2010 11:21:55
     * @param tipoSolicitante
     *            nuevo valor para tipoSolicitante
     */
    public void setTipoSolicitante(TipoSolicitante tipoSolicitante) {
        this.tipoSolicitante = tipoSolicitante;
    }

    @Override
    public String toString() {
        return "AppUser [idAppUser=" + idAppUser + ", listaTramiteSolicitado=" + listaTramiteSolicitado + ", login="
                + login + ", tipoSolicitante=" + tipoSolicitante + "]";
    }

    public String toStringLog() {
        return "AppUser [idAppUser=" + idAppUser + ", login=" + login + ", activo=" + activo + ", idAppUserAud="
                + idAppUserAud + ", ipCliente=" + ipCliente + ", tipoSolicitante=" + tipoSolicitante + ", entidad="
                + (entidad == null ? "null" : entidad) + "\n\nRoles de la BD:\n" + ", appUserRols=" + appUserRols
                + "\n\nTramites de la BD:\n listaTramiteXUsuario=" + listaTramiteXUsuario + "]";
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo
     *            the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Retorna el valor actual listaTramiteXUsuario (getter)
     *
     * @author Camilo Cruz 4/06/2010 11:06:57
     * @return el/la listaTramiteXUsuario
     */
    public Collection<TipoTramiteXUsuario> getListaTramiteXUsuario() {
        return listaTramiteXUsuario;
    }

    /**
     * Sustituye el valor actual de listaTramiteXUsuario por el valor ingresado en listaTramiteXUsuario (setter)
     *
     * @author Camilo Cruz 4/06/2010 11:06:57
     * @param listaTramiteXUsuario
     *            nuevo valor para listaTramiteXUsuario
     */
    public void setListaTramiteXUsuario(Collection<TipoTramiteXUsuario> listaTramiteXUsuario) {
        this.listaTramiteXUsuario = listaTramiteXUsuario;
    }

    /**
     * @return the entidad
     */
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

    public List<AppUserRol> getAppUserRols() {
        return appUserRols;
    }

    /**
     * Retorna el listado de roles tomados de la lista de relaciones
     * @return
     */
    public List<Rol> getRoles() {
        List<Rol> roles = new ArrayList<Rol>();
        List<AppUserRol> lista = getAppUserRols();
        for (AppUserRol userRol : lista)
            roles.add(userRol.getRol());
        return roles;
    }

    public void setAppUserRols(List<AppUserRol> appUserRols) {
        this.appUserRols = appUserRols;
    }

    /**
     * Indica si un usuario es Super Administrador
     * @return verdadero si el usuario es superadministrador y falso en caso contrario
     */
    public boolean isSuperAdministrador() {
        boolean encontrado = false;
        if (getAppUserRols() != null) {
            for (AppUserRol ur : getAppUserRols()) {
                if (ur.getRol().getIdRol() == 1) {
                    encontrado = true;
                }
            }
        }
        return encontrado;
    }

    public String getNombresRoles() {
        String res = "[";
        if (getAppUserRols() != null) {
            for (AppUserRol ur : getAppUserRols()) {
                res = res + ur.getRol().getNombre() + ", ";
            }
        }
        res = res + "]";
        return res;
    }

    /**
     * Recibe un rol para crear la relacion entre el usuario actual y el rol
     * @param rol Rol que se desea adicionar
     * @return el mismo rol adcionado
     */
    public AppUserRol addRol(Rol rol) {
        AppUserRol userRol = new AppUserRol();
        userRol.setAppUser(this);
        userRol.setRol(rol);
        boolean encontrado = false;
        for (AppUserRol ur : getAppUserRols()){
            if (ur.getRol().getIdRol()==rol.getIdRol()) {
                encontrado = true;
            }
        }
        if (!encontrado) {
            getAppUserRols().add(userRol);
        }
        return userRol;
    }

    /**
     * Adiciona una nueva relaci�n a un rol a la lista de relaciones
     * @param appUserRol relacion a un rol previamente creada
     * @return Relaci�n recibida.
     */
    public AppUserRol addAppUserRol(AppUserRol appUserRol) {
        getAppUserRols().add(appUserRol);
        return appUserRol;
    }

    /**
     * Remueve una relaci�n a un rol, dada la relaci�n
     * @param appUserRol Relaci�n solicitada
     * @return la misma relaci�n.
     */
    public AppUserRol removeAppUserRol(AppUserRol appUserRol) {
        getAppUserRols().remove(appUserRol);
        return appUserRol;
    }

    /**
     * Remueve una relaci�n a un rol, dado un rol
     * @param rol rol que se desea borrar.
     * @return La relaci�n que ha sido removida
     */
    public AppUserRol removeRol(Rol rol) {
        List<AppUserRol> lista = getAppUserRols();
        AppUserRol appUserRol = null;
        for (AppUserRol userRol : lista) {
            if (userRol.getRol().getIdRol()==rol.getIdRol()) {
                appUserRol = userRol;
                break;
            }
        }
        getAppUserRols().remove(appUserRol);
        return appUserRol;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIntentos() {
        return intentos;
    }

    public void setIntentos(Integer intentos) {
        this.intentos = intentos;
    }

    public List<TipoSolicitante> getTiposSolicitante() {
        return tiposSolicitante;
    }

    public void setTiposSolicitante(List<TipoSolicitante> tiposSolicitante) {
        this.tiposSolicitante = tiposSolicitante;
    }

}
