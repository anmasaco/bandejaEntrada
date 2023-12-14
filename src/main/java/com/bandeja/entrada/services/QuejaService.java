package com.bandeja.entrada.services;

import com.bandeja.entrada.entities.BandejaEntrada;
import com.bandeja.entrada.entities.GdoTraTareadoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface QuejaService {
    Page<GdoTraTareadoc> listarQuejas(Pageable pageable, BandejaEntrada fechasQuejas);
    Page<GdoTraTareadoc> listarQuejas2(Pageable pageable, BandejaEntrada fechasQuejas);
    String exportExcel(BandejaEntrada bandejaEntrada, boolean all, HttpServletResponse contextResponse) throws IOException;
}
