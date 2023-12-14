package com.bandeja.entrada.entities;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "CIIU.listarTodos", query = "select object(o) from CIIU o ORDER BY o.idCIIU ASC"),
        @NamedQuery(name = "CIIU.listaPorIdCIIU", query = "select o from CIIU o where o.idCIIU =:idCIIU") })
public class CIIU implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -675962235300694278L;

    @Id
    private String idCIIU;
    private String descripcionCIIU;
    private String codigoCiuuAnna;

    /**
     * Retorna el valor actual idCIIU (getter)
     *
     * @author Camilo Cruz UT SunGemini-Zabala II 22/10/2010 15:48:29
     * @return idCIIU actual
     */
    public String getIdCIIU() {
        return idCIIU;
    }

    /**
     * Sustituye el valor actual de idCIIU por el valor ingresado en idCIIU (setter)
     *
     * @author Camilo Cruz UT SunGemini-Zabala II 22/10/2010 15:48:29
     * @param idCIIU
     *            valor a actualizar
     */
    public void setIdCIIU(String idCIIU) {
        this.idCIIU = idCIIU;
    }

    /**
     * @return Retorna el valor de la descripcion
     */
    public String getDescripcionCIIU() {
        return descripcionCIIU;
    }

    /**
     * Reemplaza el valor de nombre para este objeto por el enviado en el parametro.
     *
     * @param nombre
     *            contiene el valor para reemplazar nombre en el objeto.
     */
    public void setDescripcionCIIU(String descripcionCIIU) {
        this.descripcionCIIU = descripcionCIIU.toUpperCase();
    }

    /**
     * @return the codigoCiuuAnna
     */
    public String getCodigoCiuuAnna() {
        return codigoCiuuAnna;
    }

    /**
     * @param idCiiuAnna
     *            the codigoCiuuAnna to set
     */
    public void setCodigoCiuuAnna(String codigoCiuuAnna) {
        this.codigoCiuuAnna = codigoCiuuAnna;
    }
}
