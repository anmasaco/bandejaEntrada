package com.bandeja.entrada.entities;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

@Entity
public class GdoTraTareaDocView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NUMRAD", insertable = false, updatable = false)
    private String numrad;
    @Column(name="FECRAD", insertable = false, updatable = false)
    private Date fecrad;
    @Column(name = "FECTER", insertable = false, updatable = false)
    private Date fechaVencimiento;
    @Column(name = "DESTRA", insertable = false, updatable = false)  // Quejas Reclamos
    private String destra;
    @Column(name="DOC_UID", insertable = false, updatable = false)
    private String docUid;
    @Column(name = "DOCUMENTO", insertable = false, updatable = false)
    private byte[] documento;
    @Column(name = "NOMDOC", insertable = false, updatable = false)
    private String nomdoc;

    @Column(name = "ANEXOS", insertable = false, updatable = false)
    private String anexos;

    public GdoTraTareaDocView() {
    }

    public GdoTraTareaDocView(Integer id, String numrad, Date fecrad, Date fechaVencimiento, String destra, String docUid, byte[] documento, String nomdoc) {
        this.id = id;
        this.numrad = numrad;
        this.fecrad = fecrad;
        this.fechaVencimiento = fechaVencimiento;
        this.destra = destra;
        this.docUid = docUid;
        this.documento = documento;
        this.nomdoc = nomdoc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumrad() {
        return numrad;
    }

    public void setNumrad(String numrad) {
        this.numrad = numrad;
    }

    public Date getFecrad() {
        return fecrad;
    }

    public void setFecrad(Date fecrad) {
        this.fecrad = fecrad;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getDestra() {
        return destra;
    }

    public void setDestra(String destra) {
        this.destra = destra;
    }

    public String getDocUid() {
        return docUid;
    }

    public void setDocUid(String docUid) {
        this.docUid = docUid;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    public String getNomdoc() {
        return nomdoc;
    }

    public void setNomdoc(String nomdoc) {
        this.nomdoc = nomdoc;
    }

    public String getAnexos() {
        return anexos;
    }

    public void setAnexos(String anexos) {
        this.anexos = anexos;
    }

    @Override
    public String toString() {
        return "GdoTraTareaDocView{" +
                "id=" + id +
                ", numrad='" + numrad + '\'' +
                ", fecrad=" + fecrad +
                ", fechaVencimiento=" + fechaVencimiento +
                ", destra='" + destra + '\'' +
                ", docUid='" + docUid + '\'' +
                ", documento=" + Arrays.toString(documento) +
                ", nomdoc='" + nomdoc + '\'' +
                ", anexos='" + anexos + '\'' +
                '}';
    }
}
