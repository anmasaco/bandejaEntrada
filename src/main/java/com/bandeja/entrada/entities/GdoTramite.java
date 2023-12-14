package com.bandeja.entrada.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
        @NamedQuery(name="GdoTramite.tramite", query="select q.idGdoTramite from GdoTramite q where q.codigoTramite = :codigoTram")
})
@Table(name="GDO_TRAMITE")
public class GdoTramite implements Serializable{

    private static final long serialVersionUID = 20190402150001L;

    @Id
    @Column(name="CODTRA")
    private Integer idGdoTramite;

    @Column(name="CODIGO")
    private String codigoTramite;

    public Integer getIdGdoTramite() {
        return idGdoTramite;
    }

    public void setIdGdoTramite(Integer idGdoTramite) {
        this.idGdoTramite = idGdoTramite;
    }

    public String getCodigoTramite() {
        return codigoTramite;
    }

    public void setCodigoTramite(String codigoTramite) {
        this.codigoTramite = codigoTramite;
    }

}
