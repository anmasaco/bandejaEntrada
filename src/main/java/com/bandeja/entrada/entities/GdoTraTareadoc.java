package com.bandeja.entrada.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the GDO_TRA_TAREADOCS database table.
 *
 */

@Entity
/*@NamedNativeQueries({

        @NamedNativeQuery(
                name = "GdoTraTareadoc.verificarPendiente",
                query = "select min(derivado) as derivado, numrad, coddoc, fecrad, nomdoc, contenttype from gdo_tra_tareadocs o where o.numpro=? and o.idtido in (114,169,86915,92991,103315) and o.anulada=0 and  cladoc=1 and derivado > ? and o.numter=? and rownum = 1  group by numrad,coddoc, fecrad, nomdoc, contenttype", resultClass=GdoTraTareadoc.class),
        @NamedNativeQuery(
                name = "GdoTraTareadoc.verificarPruebaEnvio",
                query = "select o.CODDOC from gdo_tra_tareadocs o where o.numter=? and o.numpro=? and o.anulada=0 and o.idtido in (139,141) and o.numrada = ?", resultClass=GdoTraTareadoc.class),
        @NamedNativeQuery(
                name = "GdoTraTareadoc.verificarDiaHabil",
                query = "select fecha from gdo_grl_festivos o where trunc(o.fecha)=?"),
        @NamedNativeQuery(
                name = "GdoTraTareadoc.obtenerNumter",
                query = "Select NUMTER from entidad_vigilada e where  e.tipo_entidad = ? and e.cod_entidad = ? and e.FK_ESTENTIDA = 1"),
        @NamedNativeQuery(
                name = "GdoTraTareadoc.verificarAdjuntos",
                query = "SELECT gtt.coddoc FROM GDO_TRA_TAREADOCS gtt, GDO_TRA_DOC_ADJUNTO ggf WHERE gtt.numrad=? and gtt.coddoc=ggf.coddoc"),
        @NamedNativeQuery(
                name = "GdoTraTareadoc.obtenerDerivadosAnexos",
                query = "SELECT CODDOC, DESANEXOS FROM GDO_TRA_TAREADOCS WHERE numrad=? ", resultClass=GdoTraTareadoc.class),
        @NamedNativeQuery(
                name = "GdoTraTareadoc.obtenerDatosArchivo",
                query = "SELECT CODDOC, NOMDOC, CONTENTTYPE FROM GDO_TRA_TAREADOCS WHERE numrad=? ", resultClass=GdoTraTareadoc.class),
        @NamedNativeQuery(
                name = "GdoTraTareadoc.listarRequerimientosPolizas",
                query = "select o.*, d.codigo codigoDocumental, d.detido from gdo_tra_tareadocs o, gdo_documental d "+
                        "where o.idtido = d.idtido and o.anulada=0 and o.cladoc=2 "+
                        "and o.tipmed not in (13,18,23) "+
                        "and o.derivado = 0 "+
                        "and o.idtido = 125 "+
                        "and o.CODTRA IN (?, ?) "+
                        "order by o.fecrad desc", resultClass=GdoTraTareadoc.class),

        @NamedNativeQuery(
                name = "GdoTraTareadoc.peObtenerListado",
                query = "select o.*, d.codigo codigoDocumental, d.detido "
                        + " from gdo_tra_tareadocs o, gdo_documental d "
                        + " where o.idtido = d.idtido "
                        + " and o.numter=? "
                        + " and trunc(o.fecrad) between ? and ? "
                        + " and o.anulada=0 "
                        + " and o.cladoc=2 "
                        + " and o.tipmed not in (13,18,23) "
                        + " order by o.fecrad desc", resultClass=GdoTraTareadoc.class),
        @NamedNativeQuery(
                name = "GdoTraTareadoc.peVerificarAcuse",
                query = "select derivado, numrad, coddoc "
                        + " from gdo_tra_tareadocs o "
                        + " where o.numter=? "
                        + " and o.numpro=? "
                        + " and o.anulada=0 "
                        + " and o.idtido in (139,141) "
                        + " and o.numrada = ? "
                        + " order by derivado", resultClass=GdoTraTareadoc.class)
})*/

@Table(name="GDO_TRA_TAREADOCS", schema = "targetSchemaName")
@Getter
@Setter
public class GdoTraTareadoc implements Serializable {
    private static final long serialVersionUID = 201508180945L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long coddoc;

    @Column
    private String numter;

    @Column
    private Long codpro;

    @Column
    private String destra;


    private String anexos;

    private BigDecimal anulada;

    private BigDecimal caso;

    private BigDecimal cladoc;

    @Column(name="CLASIFICADO_TRD")
    private BigDecimal clasificadoTrd;

    private BigDecimal coddep;

    private BigDecimal coddepdo;

    private BigDecimal coddepe;

    private BigDecimal coddepini;

    private BigDecimal codfile;

    @Column(name="CODFILE_INI")
    private BigDecimal codfileIni;

    @Column(name="CODFUN_FIRMA")
    private String codfunFirma;

    private String codfunc;

    private String codfunscan;

    private BigDecimal codmod;

    private BigDecimal codmun;

    private BigDecimal codpais;

    private BigDecimal codseg;

    private BigDecimal codtpl;

    private BigDecimal codtra;

    @Column(name="COMENT_ANULA")
    private String comentAnula;

    private String contenttype;

    private BigDecimal derivado;

    private String desanexos;


    private BigDecimal diastra;

    private String dirrem;

    @Column(name="DOC_UID")
    private String docUid;


    private BigDecimal duplicado;

    private String emailrem;

    private BigDecimal expter;

    private Timestamp feccorreo;

    private Date fecplan;

    @Column(name="FECRAD")
    private Date fecrad;
    @Column
    private Timestamp fecrefexterna;
    @Column
    private Timestamp fecter;
    @Column
    private BigDecimal idscan;
    @Column
    private BigDecimal idtido;
    @Column
    private String nomdoc;
    @Column
    private String nomrem;

    private String nomter;

    private String numdoc;

    private String numdoca;

    private BigDecimal numdocrem;

    private BigDecimal numfolios;

    private BigDecimal numplanilla;

    private BigDecimal numpro;

    private String numrad;

    private String numrada;

    private String numterrem;

    private String refexterna;

    private BigDecimal secuendiaria;

    private BigDecimal suspendida;

    private String telrem;

    @Column(name="TIP_DOC_REM")
    private BigDecimal tipDocRem;

    private BigDecimal tipdocter;

    private BigDecimal tipmed;

    private BigDecimal ucmdid;

}
