package com.bandeja.entrada.controllers;


import com.bandeja.entrada.entities.BandejaEntrada;
import com.bandeja.entrada.entities.GdoTraTareadoc;
import com.bandeja.entrada.services.QuejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/quejas")
public class QuejaControllers {

    @Autowired
    QuejaService service;

    @GetMapping("/list-all")
    public Page<GdoTraTareadoc> consultarQuejas(
            @RequestParam("page") int pageIndex,
            @RequestParam("size") int pageSize,
            @RequestParam("fecharadicacion") String fecharadicacion,
            @RequestParam("numeroExpediente") BigDecimal numeroExpediente,
            @RequestParam("numeroRadicado") String numeroRadicado
            ) {
        BandejaEntrada bandejaEntrada = new BandejaEntrada(fecharadicacion, numeroExpediente, numeroRadicado);
        return service.listarQuejas(PageRequest.of(pageIndex, pageSize), bandejaEntrada);
    }

    @GetMapping("/list-all/2")
    public Page<GdoTraTareadoc> consultarQuejas2(
            @RequestParam("page") int pageIndex,
            @RequestParam("size") int pageSize,
            @RequestParam("fecharadicacion") String fecharadicacion,
            @RequestParam("numeroExpediente") BigDecimal numeroExpediente,
            @RequestParam("numeroRadicado") String numeroRadicado
    ) {
        BandejaEntrada bandejaEntrada = new BandejaEntrada(fecharadicacion, numeroExpediente, numeroRadicado);
        return service.listarQuejas2(PageRequest.of(pageIndex, pageSize), bandejaEntrada);
    }
    @PostMapping("/export/{all}")
    public String exportExcel(@RequestBody BandejaEntrada bandejaEntrada, @PathVariable("all") boolean all, HttpServletResponse response) throws IOException {
       return this.service.exportExcel(bandejaEntrada, all, response);
    }
}
