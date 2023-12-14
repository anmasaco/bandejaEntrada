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
 * Entidad del tipo de entidad; Tipo de entidad es una clasificacion que la SFC le da a las entidades Vigiladas
 *
 * @author UTSunGemini-Zabala II- Camilo Cruz 7/07/2010
 *
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "TipoEntidad.listarTodos", query = "select object(o) from TipoEntidad o ORDER BY o.idTipoEntidad ASC"),
        @NamedQuery(name = "TipoEntidad.FICs", query = "select object(o) from TipoEntidad o where o.idTipoEntidad in (select t.tipoEntidad.idTipoEntidad from TipoEntidadFondo t where t.activo = 1) ORDER BY o.idTipoEntidad ASC"),
        /*
         * ,
         * @ NamedQuery ( name = "TipoEntidad.tipoEntidadGrupo" , query =
         * "select object(o) from TipoEntidad o where o.grupo =:vgrupo ORDER BY o.idTipoEntidad ASC" )
         */
        @NamedQuery(name = "TipoEntidad.tipoEntidadCategoria", query = "select object(o) from TipoEntidad o where o.categoria =:vcategoria  ORDER BY o.idTipoEntidad ASC")
})
public class TipoEntidad implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7467607499389016566L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_TipoEntidad")
    @SequenceGenerator(name = "SEC_TipoEntidad", sequenceName = "TipoEntidad_SEQ", allocationSize = 1)
    private Long idTipoEntidad;
    private String nombre;
    private String codigo;
    private Long categoria;
    private Long grupo;
    private Integer tipoEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClaseEntidad", insertable = false, updatable = false)
    private ClaseEntidad claseEntidad;

    @Column(name = "idCodigoClaseAnna")
    private Long codigoClaseAnna;

    @Column(name = "tipoRNVEI")
    private String tipoRNVEI;

    /**
     * @return Retorna el valor de idTipoEntidad
     */
    public Long getIdTipoEntidad() {
        return idTipoEntidad;
    }

    /**
     * Reemplaza el valor de idTipoEntidad para este objeto por el enviado en el parametro.
     *
     * @param idTipoEntidad
     *            contiene el valor para reemplazar idTipoEntidad en el objeto.
     */
    public void setIdTipoEntidad(Long idTipoEntidad) {
        this.idTipoEntidad = idTipoEntidad;
    }

    /**
     * @return Retorna el valor de nombre
     */
    public String getNombre() {
        return nombre;
    }

    public String getNombreLargo() {
        return codigo + " - " + nombre;
    }

    /**
     * Reemplaza el valor de nombre para este objeto por el enviado en el parametro.
     *
     * @param nombre
     *            contiene el valor para reemplazar nombre en el objeto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el valor actual codigo (getter)
     *
     * @author Camilo Cruz 28/06/2010 9:29:55
     * @return el/la codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sustituye el valor actual de codigo por el valor ingresado en codigo (setter)
     *
     * @author Camilo Cruz 28/06/2010 9:29:55
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "TipoEntidad [codigo=" + codigo + ", idTipoEntidad=" + idTipoEntidad + ", nombre=" + nombre + "]";
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    /* /**
     * Decide si el tipo de Entidad pertence a un GrupoTipoEntidad
     *
     * @param idGrupoEntidadEnum
     *            El codigo del Grupo
     * @return true si pertenece al grupo que envian por parametros. Null en otro caso.
     */
    /*private boolean isGrupoTipoEntidadById(Long idGrupoEntidadEnum) {
	if (idTipoEntidad != null && idTipoEntidad.equals(idGrupoEntidadEnum)) {
	    return true;
	}
	return false;
    }*/

    public Long getCodigoClaseAnna() {
        return codigoClaseAnna;
    }

    public void setCodigoClaseAnna(Long codigoClaseAnna) {
        this.codigoClaseAnna = codigoClaseAnna;
    }

    /**
     * @return the grupo
     */
    public Long getGrupo() {
        return grupo;
    }

    /**
     * @param grupo
     *            the grupo to set
     */
    public void setGrupo(Long grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the tipoRNVEI
     */
    public String getTipoRNVEI() {
        return tipoRNVEI;
    }

    /**
     * @param tipoRNVEI
     *            the tipoRNVEI to set
     */
    public void setTipoRNVEI(String tipoRNVEI) {
        this.tipoRNVEI = tipoRNVEI;
    }

    public Integer getTipoEntidad() {
        return tipoEntidad;
    }
}
