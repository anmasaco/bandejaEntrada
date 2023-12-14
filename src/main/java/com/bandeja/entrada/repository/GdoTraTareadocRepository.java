package com.bandeja.entrada.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bandeja.entrada.entities.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class GdoTraTareadocRepository {

    @PersistenceContext
    private EntityManager em;

    public List<GdoTraTareadoc> listarRadicadosRangoFechas2(String numter, String fecha_ini, String fecha_fin, AppUser appUser, int claseDocumento) throws SiriException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GdoTraTareadoc> query = cb.createQuery(GdoTraTareadoc.class);
        Root<GdoTraTareadoc> tareadoc = query.from(GdoTraTareadoc.class);
        //Join<GdoTraTareadoc, TipoDocumental> documental = tareadoc.join("idtido");

        // Condiciones
        Predicate conditions = cb.conjunction();
        conditions = cb.and(conditions, cb.equal(tareadoc.get("numter"), numter));
        conditions = cb.and(conditions, cb.between(tareadoc.get("fecrad"), fecha_ini, fecha_fin));
        conditions = cb.and(conditions, cb.equal(tareadoc.get("anulada"), 0));
        conditions = cb.and(conditions, cb.equal(tareadoc.get("cladoc"), claseDocumento));
        conditions = cb.and(conditions, cb.not(tareadoc.get("tipmed").in(13, 18, 23)));
        // TODO: Adicionar la subconsulta con GDO_TRAMITE

        query.select(tareadoc).where(conditions).orderBy(cb.desc(tareadoc.get("fecrad")));

        TypedQuery<GdoTraTareadoc> finalQuery = em.createQuery(query);
        List<GdoTraTareadoc> listado = finalQuery.getResultList();
        this.verificarDatos(listado, appUser);
        return listado;
    }

    @Transactional
    public List<GdoTraTareadoc> listarRadicadosRangoFechas(String numter, String fecha_ini, String fecha_fin, AppUser appUser, int claseDocumento) throws SiriException {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GdoTraTareadoc> cq = cb.createQuery(GdoTraTareadoc.class);

        Root<GdoTraTareadoc> tareadoc = cq.from(GdoTraTareadoc.class);
        //Join<GdoTraTareadoc, TipoDocumental> documental = tareadoc.join("tipoDocumental");
        Join<GdoTraTareadoc, GdoTramite> tramite = tareadoc.join("tramite");

        Predicate predicateForNumter = cb.equal(tareadoc.get("numter"), numter);
        Predicate predicateForFecrad = cb.between(tareadoc.get("fecrad").as(LocalDate.class), LocalDate.parse(fecha_ini), LocalDate.parse(fecha_fin));
        Predicate predicateForAnulada = cb.equal(tareadoc.get("anulada"), 0);
        Predicate predicateForCladoc = cb.equal(tareadoc.get("cladoc"), claseDocumento);
        Predicate predicateForTipmed = tareadoc.get("tipmed").in(13, 18, 23).not();

        List<String> tramites = tramitesPorSolicitante(appUser);
        Predicate predicateForTramites = tramite.get("codigo").in(tramites);

        cq.where(predicateForNumter, predicateForFecrad, predicateForAnulada, predicateForCladoc, predicateForTipmed, predicateForTramites);
        cq.orderBy(cb.desc(tareadoc.get("fecrad")));

        List<GdoTraTareadoc> listado = em.createQuery(cq).getResultList();

        this.verificarDatos(listado, appUser);
        return listado;
    }

    private List<String> tramitesPorSolicitante(AppUser appUser) {
        return List.of();
    }

    private void verificarDatos(List<GdoTraTareadoc> listado, AppUser appUser) {
        // tu lógica para verificar los datos
    }
}

