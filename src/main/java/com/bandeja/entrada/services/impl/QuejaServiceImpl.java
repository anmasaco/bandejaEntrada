package com.bandeja.entrada.services.impl;

import com.bandeja.entrada.entities.BandejaEntrada;
import com.bandeja.entrada.entities.GdoTraTareadoc;
import com.bandeja.entrada.repository.ClaseQuejaRepository;
import com.bandeja.entrada.services.QuejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.DayOfWeek;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;

@Service
public class QuejaServiceImpl implements QuejaService {

    @Autowired
    ClaseQuejaRepository repository;
    public Page<GdoTraTareadoc> listarQuejas(Pageable pageable, BandejaEntrada bandejaEntrada){

        Map<String, LocalDate> fechas = new HashMap<>();
        fechas = obtenerFechas(bandejaEntrada);
        if (bandejaEntrada.getNumeroExpediente() == null && bandejaEntrada.getNumeroRadicado().isEmpty()){
            return repository.findFechaRadicacion(pageable, fechas.get("fechaDesde"), fechas.get("fechaHasta"));
        }else if (bandejaEntrada.getNumeroExpediente() != null && bandejaEntrada.getNumeroRadicado().isEmpty()){
            return repository.fechaExpediente(pageable, fechas.get("fechaDesde"),fechas.get("fechaHasta"), bandejaEntrada.getNumeroExpediente());
        }else if (bandejaEntrada.getNumeroExpediente() == null && !bandejaEntrada.getNumeroRadicado().isEmpty()){
            return repository.numeroRadicado(pageable, fechas.get("fechaDesde"),fechas.get("fechaHasta"), bandejaEntrada.getNumeroRadicado());
        }
        return repository.findAll(pageable,
                fechas.get("fechaDesde"),
                fechas.get("fechaHasta"),
                bandejaEntrada.getNumeroRadicado(),
                bandejaEntrada.getNumeroExpediente());
    }

    @Override
    public Page<GdoTraTareadoc> listarQuejas2(Pageable pageable, BandejaEntrada fechasQuejas) {

        LocalDate inicio = LocalDate.now();
        LocalDate fin = restarDiasHabiles(inicio, 2);

        return repository.findAllDateTime(pageable,
                inicio,
                fin,
                fechasQuejas.getNumeroRadicado(),
                fechasQuejas.getNumeroExpediente());
    }

    @Override
    public String exportExcel(BandejaEntrada bandejaEntrada, boolean all, HttpServletResponse contextResponse) throws IOException {
        List<GdoTraTareadoc> listGdoTraTareadoc = all ? listarQuejasNoPage(bandejaEntrada) : listarQuejas2NoPage(bandejaEntrada);
        if(!listGdoTraTareadoc.isEmpty()) {
            HSSFWorkbook generatedExcel = generateExcel(listGdoTraTareadoc);
            writeExcelOutput(generatedExcel, contextResponse);
        }
        return null;
    }

    private List<GdoTraTareadoc> listarQuejasNoPage(BandejaEntrada bandejaEntrada){

        Map<String, LocalDate> fechas = new HashMap<>();
        fechas = obtenerFechas(bandejaEntrada);
        if (bandejaEntrada.getNumeroExpediente() == null && bandejaEntrada.getNumeroRadicado().isEmpty()){
            return repository.findFechaRadicacion(fechas.get("fechaDesde"), fechas.get("fechaHasta"));
        }else if (bandejaEntrada.getNumeroExpediente() != null && bandejaEntrada.getNumeroRadicado().isEmpty()){
            return repository.fechaExpediente(fechas.get("fechaDesde"),fechas.get("fechaHasta"), bandejaEntrada.getNumeroExpediente());
        }else if (bandejaEntrada.getNumeroExpediente() == null && !bandejaEntrada.getNumeroRadicado().isEmpty()){
            return repository.numeroRadicado(fechas.get("fechaDesde"),fechas.get("fechaHasta"), bandejaEntrada.getNumeroRadicado());
        }
        return repository.findAll(
                fechas.get("fechaDesde"),
                fechas.get("fechaHasta"),
                bandejaEntrada.getNumeroRadicado(),
                bandejaEntrada.getNumeroExpediente());
    }

    public List<GdoTraTareadoc> listarQuejas2NoPage( BandejaEntrada fechasQuejas) {

        LocalDate inicio = LocalDate.now();
        LocalDate fin = restarDiasHabiles(inicio, 2);

        return repository.findAllDateTime(
                inicio,
                fin,
                fechasQuejas.getNumeroRadicado(),
                fechasQuejas.getNumeroExpediente());
    }

    private static HSSFWorkbook generateExcel(List<GdoTraTareadoc> lista) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("lista_tramites");
        int i = 1;
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("Radicado");
        cell = row.createCell(1);
        cell.setCellValue("Fecha");
        cell = row.createCell(2);
        cell.setCellValue("Trámite");
        cell = row.createCell(3);
        cell.setCellValue("Tipo Documental");
        cell = row.createCell(4);
        cell.setCellValue("Responder");

        for(Iterator var8 = lista.iterator(); var8.hasNext(); ++i) {
            GdoTraTareadoc gdoTraTareadoc = (GdoTraTareadoc)var8.next();
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(gdoTraTareadoc.getNumrad());
            cell = row.createCell(1);
            cell.setCellValue(gdoTraTareadoc.getFecrad());
            cell = row.createCell(2);
            cell.setCellValue(gdoTraTareadoc.getDestra());
            cell = row.createCell(3);
            cell.setCellValue(gdoTraTareadoc.getCoddoc());
            cell = row.createCell(4);
            cell.setCellValue("Pendiente Respuesta");
        }

        return workbook;
    }

    private static void writeExcelOutput(HSSFWorkbook workBook, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        response.setHeader("Content-Disposition", "attachment; filename=" + format.format(new Date()) + ".xls");
        OutputStream out = response.getOutputStream();
        workBook.write(out);
        out.flush();
        out.close();
    }

    public Map<String, LocalDate> obtenerFechas(BandejaEntrada bandejaEntrada){
        Map<String, LocalDate> fechas = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fechaDesde = LocalDate.parse(bandejaEntrada.getFecharadicacion(), formatter);
        fechas.put("fechaDesde", fechaDesde);
        fechas.put("fechaHasta", LocalDate.now());
        return fechas;
    }

    public static LocalDate restarDiasHabiles(LocalDate fecha, int dias) {
        while (dias > 0) {
            fecha = fecha.minusDays(1);

            // Salta los sábados (DayOfWeek.SATURDAY) y domingos (DayOfWeek.SUNDAY)
            if (fecha.getDayOfWeek() != DayOfWeek.SATURDAY && fecha.getDayOfWeek() != DayOfWeek.SUNDAY) {
                dias--;
            }
        }
        return fecha;
    }


    public static LocalDateTime restarHorasHabiles(LocalDateTime fechaHoraActual, int horasHabilesARestar) {
        LocalDateTime resultado = fechaHoraActual;
        int horasRestadas = 0;

        while (horasRestadas < horasHabilesARestar) {
            // Retrocede una hora
            resultado = resultado.minusHours(1);

            // Verifica si la hora resultante es hábil (lunes a viernes, de 9:00 a 17:00)
            if (esHoraHabil(resultado)) {
                horasRestadas++;
            }
        }

        return resultado;
    }
    public static boolean esHoraHabil(LocalDateTime dateTime) {
        // Verifica si el dateTime corresponde a una hora laboral de lunes a viernes (9:00 a 17:00)
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        int hour = dateTime.getHour();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && hour >= 9 && hour < 17;
    }
}
