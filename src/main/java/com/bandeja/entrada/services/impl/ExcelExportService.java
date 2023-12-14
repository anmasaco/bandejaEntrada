package com.bandeja.entrada.services.impl;

import com.bandeja.entrada.entities.GdoTraTareadoc;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.data.domain.Page;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExportService {
    public void exportToExcel(Page<GdoTraTareadoc> gdoTraTareadoc, String filePath) throws IOException {
        Workbook workbook = WorkbookFactory.create(new FileInputStream("template.xlsx")); // Puedes crear un archivo de plantilla Excel
        Sheet sheet = workbook.getSheetAt(0); // Obtén la hoja de trabajo

        int rowNumber = 1; // Comienza desde la segunda fila (la primera puede ser para encabezados)

        for (GdoTraTareadoc doTraTareadoc : gdoTraTareadoc) {
            Row row = sheet.createRow(rowNumber++);

            // Agrega datos a las celdas, por ejemplo:
            row.createCell(0).setCellValue(doTraTareadoc.getNumrad());
            row.createCell(1).setCellValue(doTraTareadoc.getFecrad());
        }

        // Guarda el archivo Excel
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }
}
