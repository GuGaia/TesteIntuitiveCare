package main.java.DataTransforming;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class CsvCreate {

    private File xlsxFile = null;
    private String dirCsv = "Resources";

    public CsvCreate(File xslxFile){
        this.xlsxFile = xslxFile;
    }

    public File createCSV() throws Exception {
        File pasta = new File(dirCsv);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
        File csvFile = new File(pasta, "Anexo_I_Rol_2021RN_465.2021_RN627L.2024.csv");
        try (
                Workbook workbook = new XSSFWorkbook(new FileInputStream(xlsxFile));
                PrintWriter writer = new PrintWriter(csvFile)
        ) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                for (Row row : sheet) {
                    StringBuilder line = new StringBuilder();
                    for (Cell cell : row) {
                        cell.setCellType(CellType.STRING);
                        String valor = cell.getStringCellValue().trim();
                        valor = valor.replace("OD", "Seg. Odontológica");
                        valor = valor.replace("AMB", "Seg. Ambulatorial");
                        line.append("\"").append(valor).append("\",");
                    }
                    if (line.length() > 0) {
                        writer.println(line.substring(0, line.length() - 1));
                    }
                }
            }
        }
        return csvFile;
    }
}
