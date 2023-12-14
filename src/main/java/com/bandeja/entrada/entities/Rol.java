package com.bandeja.entrada.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 * Company UT SUNGEMINI � ZABALA II
 * @project : Siri
 * @author : Camilo Cruz
 * @created : 11/11/2010 Entidad Que se utiliza para los posibles roles que tendra la aplicacion Deben ser fijos los
 *          siguientes Roles 100 Administrador 101 Analista Registro 102 Cordinador Registro 200 Solicitante
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name =  "Rol.listarTodos",
                query = "select object(o) from Rol o"),
        @NamedQuery(
                name =  "Rol.listarActivos",
                query = "select object(o) "
                        + "  from Rol o "
                        + " where o.activo=1 ")
})
public class Rol implements Serializable, Comparable<Rol> {

    /**
     * Campo
     * @author Camilo Cruz for UT SunGemini-Zabala II 11/11/2010 11:08:44
     */
    private static final long serialVersionUID = -4511751715254533193L;

    @Id
    private int idRol;

    @Column(length = 50)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "IDTIPOROL", nullable = false)
    private TipoRol tipoRol;

    @Transient
    private boolean seleccionado;

    private Integer idRolFederalizacion;

    public Rol getRol() {
        return this;
    }

    /**
     * Retorna el valor actual idRol (getter)
     * @author Camilo Cruz UT SunGemini-Zabala II 11/11/2010 11:09:41
     * @return idRol actual
     */
    public int getIdRol() {
        return idRol;
    }

    /**
     * Sustituye el valor actual de idRol por el valor ingresado en idRol (setter)
     *
     * @author Camilo Cruz UT SunGemini-Zabala II 11/11/2010 11:09:41
     * @param idRol
     *            valor a actualizar
     */
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    /**
     * Retorna el valor actual nombre (getter)
     *
     * @author Camilo Cruz UT SunGemini-Zabala II 11/11/2010 11:09:41
     * @return nombre actual
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sustituye el valor actual de nombre por el valor ingresado en nombre (setter)
     *
     * @author Camilo Cruz UT SunGemini-Zabala II 11/11/2010 11:09:41
     * @param nombre
     *            valor a actualizar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int compareTo(Rol rol) {
        int res = this.idRol - rol.getIdRol();
        return res;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    @Override
    public String toString() {
        return "Rol [idRol=" + idRol + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo
                + ", seleccionado=" + seleccionado + "]";
    }

    public Integer getIdRolFederalizacion() {
        return idRolFederalizacion;
    }

    public void setIdRolFederalizacion(Integer idRolFederalizacion) {
        this.idRolFederalizacion = idRolFederalizacion;
    }

}
