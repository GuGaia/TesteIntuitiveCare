package test.java.DataTransforming;

import main.java.DataTransforming.CsvCreate;
import main.java.DataTransforming.SheetXlsx;
import main.java.WebScraper.Zipper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CsvCreateTest {

    private static final String xslxFileName = "Anexo_I_Rol_2021RN_465.2021_RN627L.2024.xlsx";
    private static final String fileLink = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.xlsx";
    private final String path = "Resources/anexoCsv_compactado.zip";
    private CsvCreate csvCreate;
    private SheetXlsx sheetXlsx;
    private File csvFile;
    private Zipper zipper;
    private File zipFile;

    @Before
    public void setUp() throws Exception {
        sheetXlsx = new SheetXlsx(xslxFileName, fileLink);
        csvCreate = new CsvCreate(sheetXlsx.getXlsFile());
        csvFile = csvCreate.createCSV();
        zipper = Zipper.zipping(Arrays.asList(csvFile), path);
    }

    @Test
    public void testCreate() throws Exception {

        assertNotNull(csvFile);
        assertTrue(csvFile.exists());
        assertTrue(csvFile.length() > 0);

        String content = new String(java.nio.file.Files.readAllBytes(csvFile.toPath()));
        assertFalse(content.contains("OD"));
        assertFalse(content.contains("AMB"));
        assertTrue(content.contains("Seg. Odontológica"));
        assertTrue(content.contains("Seg. Ambulatorial"));
    }
    @Test
    public void testZippingFile() throws IOException {
        assertTrue(csvFile.exists());

        zipper.zipFiles();
        zipFile = zipper.getZipFile();

        assertNotNull(zipFile);
        assertTrue(zipFile.exists());
        assertTrue(zipFile.length() > 0);

        java.util.zip.ZipFile zip = new java.util.zip.ZipFile(zipFile);
        assertNotNull(zip.getEntry(csvFile.getName()));
        zip.close();
    }
}
