package com.bandeja.entrada.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLASEQUEJATPENTIDAD")
public class    ClaseQueja {

    @Id
    @Column(name = "IDCLASEQUEJATPENTIDAD")
    private Long idClaseQueja;

    @Column(name = "CODIGOQUEJASOLIP")
    private String codigo;

    public Long getIdClaseQueja() {
        return idClaseQueja;
    }

    public void setIdClaseQueja(Long idClaseQueja) {
        this.idClaseQueja = idClaseQueja;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
