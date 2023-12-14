package com.bandeja.entrada.repository;


import com.bandeja.entrada.entities.GdoTraTareadoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Repository
@Transactional
public interface ClaseQuejaRepository extends JpaRepository<GdoTraTareadoc, Long>, QueryByExampleExecutor<GdoTraTareadoc> {

    //@Query(nativeQuery = true, value = "select o.*, d.codigo codigoDocumental, d.detido from gdo_tra_tareadocs o, gdo_documental d where o.idtido = d.idtido AND numrad IN('1997003209-010-000', '1994018856-040-000')")
    @Query(nativeQuery = true, value = "SELECT * FROM GDO_TRA_TAREADOCS t WHERE fecrad BETWEEN :desde AND :hasta",
    countQuery = "SELECT count(*) FROM GDO_TRA_TAREADOCS t WHERE fecrad BETWEEN :desde AND :hasta")
    Page<GdoTraTareadoc> findFechaRadicacion(Pageable pageable,
                                       @Param("desde") LocalDate fechaDesde,
                                       @Param("hasta") LocalDate fechaHasta);

    @Query(nativeQuery = true, value = "SELECT * FROM GDO_TRA_TAREADOCS t WHERE fecrad BETWEEN :desde AND :hasta",
            countQuery = "SELECT count(*) FROM GDO_TRA_TAREADOCS t WHERE fecrad BETWEEN :desde AND :hasta")
    List<GdoTraTareadoc> findFechaRadicacion(
                                             @Param("desde") LocalDate fechaDesde,
                                             @Param("hasta") LocalDate fechaHasta);

    @Query(nativeQuery = true, value = "SELECT * FROM GDO_TRA_TAREADOCS t WHERE numrad = :numeroRadicado AND numpro =:expediente AND fecrad BETWEEN :desde AND :hasta",
            countQuery = "SELECT count(*) FROM GDO_TRA_TAREADOCS t WHERE numrad = :numeroRadicado AND fecrad BETWEEN :desde AND :hasta ")
    Page<GdoTraTareadoc> findAll(Pageable pageable,
                                 @Param("desde") LocalDate fechaDesde,
                                 @Param("hasta") LocalDate fechaHasta,
                                 @Param("numeroRadicado") String radicado,
                                 @Param("expediente") BigDecimal expediente);

    @Query(nativeQuery = true, value = "SELECT * FROM GDO_TRA_TAREADOCS t WHERE numrad = :numeroRadicado AND numpro =:expediente AND fecrad BETWEEN :desde AND :hasta",
            countQuery = "SELECT count(*) FROM GDO_TRA_TAREADOCS t WHERE numrad = :numeroRadicado AND fecrad BETWEEN :desde AND :hasta ")
    List<GdoTraTareadoc> findAll(
                                 @Param("desde") LocalDate fechaDesde,
                                 @Param("hasta") LocalDate fechaHasta,
                                 @Param("numeroRadicado") String radicado,
                                 @Param("expediente") BigDecimal expediente);
    @Query(nativeQuery = true, value = "SELECT * FROM GDO_TRA_TAREADOCS t WHERE numrad = :numeroRadicado AND numpro =:expediente AND fecrad BETWEEN :desde AND :hasta",
            countQuery = "SELECT count(*) FROM GDO_TRA_TAREADOCS t WHERE numrad = :numeroRadicado AND fecrad BETWEEN :desde AND :hasta ")
    Page<GdoTraTareadoc> findAllDateTime(Pageable pageable,
                                 @Param("desde") LocalDate fechaDesde,
                                 @Param("hasta") LocalDate fechaHasta,
                                 @Param("numeroRadicado") String radicado,
                                 @Param("expediente") BigDecimal expediente);

    @Query(nativeQuery = true, value = "SELECT * FROM GDO_TRA_TAREADOCS t WHERE numrad = :numeroRadicado AND numpro =:expediente AND fecrad BETWEEN :desde AND :hasta",
            countQuery = "SELECT count(*) FROM GDO_TRA_TAREADOCS t WHERE numrad = :numeroRadicado AND fecrad BETWEEN :desde AND :hasta ")
    List<GdoTraTareadoc> findAllDateTime(
                                         @Param("desde") LocalDate fechaDesde,
                                         @Param("hasta") LocalDate fechaHasta,
                                         @Param("numeroRadicado") String radicado,
                                         @Param("expediente") BigDecimal expediente);

    @Query(nativeQuery = true, value = "SELECT * FROM GDO_TRA_TAREADOCS t WHERE fecrad BETWEEN :desde AND :hasta AND numpro =:expediente",
            countQuery = "SELECT count(*) FROM GDO_TRA_TAREADOCS t WHERE fecrad BETWEEN :desde AND :hasta AND numpro =:expediente")
    Page<GdoTraTareadoc> fechaExpediente(Pageable pageable,
                                         @Param("desde") LocalDate fechaDesde,
                                         @Param("hasta") LocalDate fechaHasta,
                                         @Param("expediente") BigDecimal expediente);

    @Query(nativeQuery = true, value = "SELECT * FROM GDO_TRA_TAREADOCS t WHERE fecrad BETWEEN :desde AND :hasta AND numpro =:expediente",
            countQuery = "SELECT count(*) FROM GDO_TRA_TAREADOCS t WHERE fecrad BETWEEN :desde AND :hasta AND numpro =:expediente")
    List<GdoTraTareadoc> fechaExpediente(
                                         @Param("desde") LocalDate fechaDesde,
                                         @Param("hasta") LocalDate fechaHasta,
                                         @Param("expediente") BigDecimal expediente);

    @Query(nativeQuery = true, value = "SELECT * FROM GDO_TRA_TAREADOCS t WHERE numrad = :numeroRadicado AND fecrad BETWEEN :desde AND :hasta",
            countQuery = "SELECT count(*) FROM GDO_TRA_TAREADOCS t WHERE numrad = :numeroRadicado AND fecrad BETWEEN :desde AND :hasta ")
    Page<GdoTraTareadoc> numeroRadicado(Pageable pageable,
                                         @Param("desde") LocalDate fechaDesde,
                                         @Param("hasta") LocalDate fechaHasta,
                                         @Param("numeroRadicado") String radicado);

    @Query(nativeQuery = true, value = "SELECT * FROM GDO_TRA_TAREADOCS t WHERE numrad = :numeroRadicado AND fecrad BETWEEN :desde AND :hasta",
            countQuery = "SELECT count(*) FROM GDO_TRA_TAREADOCS t WHERE numrad = :numeroRadicado AND fecrad BETWEEN :desde AND :hasta ")
    List<GdoTraTareadoc> numeroRadicado(
                                        @Param("desde") LocalDate fechaDesde,
                                        @Param("hasta") LocalDate fechaHasta,
                                        @Param("numeroRadicado") String radicado);
}