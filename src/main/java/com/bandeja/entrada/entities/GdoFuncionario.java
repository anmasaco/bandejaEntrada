package com.bandeja.entrada.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "gdo_funcionario")
public class GdoFuncionario implements Serializable {
    private static final long serialVersionUID = 20171023114601L;

    @Id
    private String cedula;
    private String nomFun;
    private Integer codCargo;
    private String codFun;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType") // Using BinaryType for byte[] handling
    @Column(name = "FIRMA_IMG")
    private byte[] firmaImg;

    /*@ManyToOne(fetch = FetchType.LAZY) // Assuming MpGrlCargo is an entity class
    @JoinColumn(name = "CODCARGO", insertable = false, updatable = false)
    private MpGrlCargo mpGrlCargo;*/

    public String getCedula() {
        return cedula;
    }

    public String getNomFun() {
        return nomFun;
    }

    public byte[] getFirmaImg() {
        return firmaImg;
    }

    public Integer getCodCargo() {
        return codCargo;
    }

    public String getCodFun() {
        return codFun;
    }

    /*public MpGrlCargo getMpGrlCargo() {
        return mpGrlCargo;
    }

    public void setMpGrlCargo(MpGrlCargo mpGrlCargo) {
        this.mpGrlCargo = mpGrlCargo;
    }*/
}