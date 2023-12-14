package com.bandeja.entrada.entities;
import java.io.Serializable;

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
import javax.persistence.Transient;


/**
 * Entidad donde se listan los tipos de tramite
 *
 * @author UTSunGemini-Zabala II- Camilo Cruz 7/07/2010
 *
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "TipoTramite.listarTodos", query = "select object(o) from TipoTramite o WHERE o.visible=1  ORDER BY o.codigoTramite ASC"),
        @NamedQuery(name = "TipoTramite.findByCodTramite", query = "select object(o) from TipoTramite o where o.codigoTramite=:cod ORDER BY o.codigoTramite ASC"),
        @NamedQuery(name = "TipoTramite.findById", query = "select object(o) from TipoTramite o where o.idTipoTramite=:idTipoTramite"),
        @NamedQuery(name = "TipoTramite.findByTipoSol", query = "select object(o) from TipoTramite o where o.visible=:tipo ORDER BY o.codigoTramite ASC") })
public class TipoTramite implements Serializable {

    private static final long serialVersionUID = -4285571011028297471L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_TipoTramite")
    @SequenceGenerator(name = "SEC_TipoTramite", sequenceName = "TipoTramite_SEQ", allocationSize = 1)
    private int idTipoTramite;
    private String nombre;
    private String codigoTramite;
    private boolean seccionOpcional;
    private boolean visible;
    private boolean generico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClaseTramite")
    private ClaseTramite claseTramite;

    @Transient
    private boolean check;

    @Transient
    private boolean requiereRadicado;

    @Transient
    private TipoTramiteXUsuario tipoTramiteXUsuario;

    public TipoTramiteXUsuario getTipoTramiteXUsuario() {
        return tipoTramiteXUsuario;
    }

    public void setTipoTramiteXUsuario(TipoTramiteXUsuario tipoTramiteXUsuario) {
        this.tipoTramiteXUsuario = tipoTramiteXUsuario;
    }

    /**
     * Creado por Camilo Cruz 7/07/2010
     *
     * @return el valor a retornar
     */
    public int getIdTipoTramite() {
        return idTipoTramite;
    }

    /**
     * Creado por Camilo Cruz 7/07/2010
     *
     * @param idTipoTramite
     *            el valor a actualziar
     */
    public void setIdTipoTramite(int idTipoTramite) {
        this.idTipoTramite = idTipoTramite;
    }

    /**
     * Creado por Camilo Cruz 7/07/2010
     *
     * @return el valor a retornar
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Creado por Camilo Cruz 7/07/2010
     *
     * @param nombre
     *            el valor a actualizar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el valor actual codigoTramite (getter)
     *
     * @author Camilo Cruz 4/06/2010 10:27:08
     * @return el/la codigoTramite
     */
    public String getCodigoTramite() {
        return codigoTramite;
    }

    /**
     * Sustituye el valor actual de codigoTramite por el valor ingresado en codigoTramite (setter)
     *
     * @author Camilo Cruz 4/06/2010 10:27:08
     * @param codigoTramite
     *            nuevo valor para codigoTramite
     */
    public void setCodigoTramite(String codigoTramite) {
        this.codigoTramite = codigoTramite;
    }

    /**
     * Retorna el valor actual check (getter)
     *
     * @author Camilo Cruz 4/06/2010 10:27:08
     * @return el/la check
     */
    public boolean isCheck() {
        return check;
    }

    /**
     * Sustituye el valor actual de check por el valor ingresado en check (setter)
     *
     * @author Camilo Cruz 4/06/2010 10:27:08
     * @param check
     *            nuevo valor para check
     */
    public void setCheck(boolean check) {
        this.check = check;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return codigoTramite + " - " + nombre;
    }

    public boolean isSeccionOpcional() {
        return seccionOpcional;
    }

    public void setSeccionOpcional(boolean seccionOpcional) {
        this.seccionOpcional = seccionOpcional;
    }

    public ClaseTramite getClaseTramite() {
        return claseTramite;
    }

    public void setClaseTramite(ClaseTramite claseTramite) {
        this.claseTramite = claseTramite;
    }

    public boolean isGenerico() {
        return generico;
    }

    public void setGenerico(boolean generico) {
        this.generico = generico;
    }

}
