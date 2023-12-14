package com.bandeja.entrada.entities;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author UTSunGemini-Zabala II- Camilo Cruz 24/05/2010
 *         Revision 1 : Camilo Cruz 24/05/2010 Redise�o de los tipos de documento
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name =  "TipoDocumento.listarTodos",
                query = "select object(o) from TipoDocumento o ORDER BY o.nombre ASC"),
        @NamedQuery(
                name =  "TipoDocumento.listarPorId",
                query = "select object(o) from TipoDocumento o where o.idTipoDocumento=:idTipoDocumento")
})
@Table(name = "TIPODOCUMENTO")
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = -7050720784791834334L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_TIPODOCUMENTO")
    @SequenceGenerator(name = "SEC_TIPODOCUMENTO", sequenceName = "TIPODOCUMENTO_SEQ", allocationSize = 1)
    private Integer idTipoDocumento;
    private String nombre;
    private String sigla;

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla!=null?sigla:"";
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
