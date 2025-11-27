package Controller;

import entity.DetalleReclamo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class ReporteExcelGenerator {

    public static String generarExcel(String nombreArchivo, int totalReclamos, double horasPromedio, double porcentajePrimerContacto, List<DetalleReclamo> detalle) throws Exception {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reporte Reclamos");

            // Estilo de header
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);

            // Cabecera de KPIs (fila 0..2)
            Row r0 = sheet.createRow(0);
            r0.createCell(0).setCellValue("Total Reclamos:");
            r0.createCell(1).setCellValue(totalReclamos);

            Row r1 = sheet.createRow(1);
            r1.createCell(0).setCellValue("Promedio Tiempo (horas):");
            r1.createCell(1).setCellValue(horasPromedio);

            Row r2 = sheet.createRow(2);
            r2.createCell(0).setCellValue("% Primer Contacto:");
            r2.createCell(1).setCellValue(porcentajePrimerContacto);

            // Salto antes de la tabla
            int startRow = 4;
            Row header = sheet.createRow(startRow);
            String[] cols = {"ID Reclamo", "Fecha Registro", "Tipo", "Estado", "Fecha Resolución", "Horas Resolución", "1er Contacto"};
            for (int i = 0; i < cols.length; i++) {
                Cell c = header.createCell(i);
                c.setCellValue(cols[i]);
                c.setCellStyle(headerStyle);
            }

            int rownum = startRow + 1;
            CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd hh:mm"));

            for (DetalleReclamo d : detalle) {
                Row row = sheet.createRow(rownum++);
                row.createCell(0).setCellValue(d.getIdReclamo());
                Cell c1 = row.createCell(1);
                if (d.getFechaRegistro() != null) {
                    c1.setCellValue(d.getFechaRegistro());
                    c1.setCellStyle(dateStyle);
                } else c1.setCellValue("");
                row.createCell(2).setCellValue(d.getTipo());
                row.createCell(3).setCellValue(d.getEstado());
                Cell c2 = row.createCell(4);
                if (d.getFechaResolucion() != null) {
                    c2.setCellValue(d.getFechaResolucion());
                    c2.setCellStyle(dateStyle);
                } else c2.setCellValue("");
                if (d.getTiempoResolucionHoras() != null) row.createCell(5).setCellValue(d.getTiempoResolucionHoras());
                else row.createCell(5).setCellValue("");
                row.createCell(6).setCellValue(d.isPrimerContacto() ? "SI" : "NO");
            }

            // Autoajustar columnas
            for (int i = 0; i < cols.length; i++) sheet.autoSizeColumn(i);

            // Guardar archivo
            String ruta = nombreArchivo;
            try (FileOutputStream out = new FileOutputStream(ruta)) {
                workbook.write(out);
            }
            return ruta;
        }
    }
}
