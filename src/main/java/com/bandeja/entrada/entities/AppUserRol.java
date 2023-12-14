package com.bandeja.entrada.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * Esta Entidad se crea por la necesidad de modificar el sistema para que ajuste a manejo de multiples roles
 * @author eamosquera
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name =  "AppUserRol.findByUSer",
                query = "select object(o) "
                        + "  from AppUserRol o "
                        + " where o.appUser.idAppUser = :usuarioID")
})
public class AppUserRol implements Serializable {

    /** Obligatorio por el manejo de la interface Serializable */
    private static final long serialVersionUID = 3329132078567379632L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_APPUSERROL")
    @SequenceGenerator(name = "SEC_APPUSERROL", sequenceName = "APPUSERROL_SEQ", allocationSize = 1)
    private Long idAppUserRol;

    @ManyToOne
    @JoinColumn(name = "idAppUser")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;

    @Column(nullable = false, precision = 6)
    private Long idAppUserAud;

    @Column(length = 20)
    private String ipCliente;

    public Long getIdAppUserRol() {
        return idAppUserRol;
    }

    public void setIdAppUserRol(Long idAppUserRol) {
        this.idAppUserRol = idAppUserRol;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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

    /**
     * Evalua que dos relaciones de rol y usuario sean iguales validando el id del usuario y el id del rol
     */
    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if (obj != null && obj instanceof AppUserRol) {
            AppUserRol ur = (AppUserRol) obj;
            try {
                res = ((appUser.getIdAppUser().equals(ur.getAppUser().getIdAppUser())) &&
                        (rol.getIdRol() == ur.getRol().getIdRol()));
            } catch (Exception e) {
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "AppUserRol [idAppUserRol=" + idAppUserRol + ", idAppUser=" + appUser.getIdAppUser() + ", rol=" + rol.getIdRol() + ", idAppUserAud="
                + idAppUserAud + ", ipCliente=" + ipCliente + "]\n";
    }

}
