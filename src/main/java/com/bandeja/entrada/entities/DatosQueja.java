package com.bandeja.entrada.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DATOS_QUEJAS")
public class DatosQueja {

    @Id
    @Column(name = "ID_PARAM")
    private Long idParam;

    private Long aplica;
    // ... (resto de atributos)

    public Long getIdParam() {
        return idParam;
    }

    public void setIdParam(Long idParam) {
        this.idParam = idParam;
    }

    public Long getAplica() {
        return aplica;
    }

    public void setAplica(Long aplica) {
        this.aplica = aplica;
    }

    // ... (resto de getters y setters)

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_CAUSA")
    private Date fechaCausa;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_IMPULS")
    private Date fechaImpuls;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_LIMITE")
    private Date fechaLimite;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_OCURRE")
    private Date fechaOcurre;

    @Column(name = "FK_CLASE_QUE")
    private Integer fkClaseQue;

    @Column(name = "FK_MOTIVO")
    private Integer fkMotivo;

    @Column(name = "FK_TIPO")
    private Integer fkTipo;

    @Column(name = "GENERA_AUTO")
    private Long generaAuto;

    @Column(name = "GENERA_INSUM")
    private Long generaInsum;

    private Long infocompleta;

    @Temporal(TemporalType.DATE)
    @Column(name = "LIMITE_REQUE")
    private Date limiteReque;

    private String numeroproceso;

    @Column(name = "OBSERVACION_GENERAL")
    private String observacionGeneral;

    private String observaciones;

    private Long quejapc;

    @Column(name = "TIPO_CUANTIA")
    private Long tipoCuantia;

    private Long tramiteok;

    public DatosQueja() {
    }

}