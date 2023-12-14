package com.bandeja.entrada.controllers;

import com.bandeja.entrada.entities.BandejaEntrada;
import com.bandeja.entrada.entities.GdoTraTareadoc;
import com.bandeja.entrada.services.QuejaService;
import com.bandeja.entrada.services.impl.ExcelExportService;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/export")
public class ExportarExcel {

    /*@Autowired
    private final GdoTraTareadoc gdoTraTareadoc;*/
//    private final ExcelExportService excelExportService;

    //private final QuejaService quejaService;

//    public ExportarExcel(GdoTraTareadoc gdoTraTareadoc, ExcelExportService excelExportService, QuejaService quejaService) {
//        this.gdoTraTareadoc = gdoTraTareadoc;
//        this.excelExportService = excelExportService;
//        this.quejaService = quejaService;
//    }

//    @GetMapping ("/excel")
//    public void exportarExcel(HttpServletResponse response,
//                              @RequestParam("page") int pageIndex,
//                              @RequestParam("size") int pageSize,
//                              @RequestBody BandejaEntrada bandejaEntrada) {
//        try {
//            Page<GdoTraTareadoc> tramites = quejaService.listarQuejas(PageRequest.of(pageIndex, pageSize), bandejaEntrada);
//
//            // Define la ruta donde se guardará el archivo Excel
//            String filePath = "tramites_pendientes.xlsx";
//
//            excelExportService.exportToExcel(tramites, filePath);
//
//            // Configura la respuesta HTTP para descargar el archivo Excel
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setHeader("Content-Disposition", "attachment; filename=" + filePath);
//
//            // Envía el archivo Excel al cliente
//            try (OutputStream os = response.getOutputStream()) {
//                FileInputStream fis = new FileInputStream(filePath);
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = fis.read(buffer)) != -1) {
//                    os.write(buffer, 0, bytesRead);
//                }
//            }
//        } catch (IOException e) {
//            // Manejar la excepción apropiadamente
//        }
//    }


}
