package test.java.DataTransforming;

import main.java.DataTransforming.Sheet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class DataTransformingTest {

    private static final String xslxFileName = "Anexo_I_Rol_2021RN_465.2021_RN627L.2024.xlsx";
    private static final String fileLink = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.xlsx";
    private static final String DIRETORIO_TESTE = "DownloadsTest";

    private DataTransforming dataTransforming;
    private Sheet sheet;
    private File csvFile;
    private File zipFile;

    @Before
    public void setUp() throws Exception {
        sheet = new Sheet(xslxFileName, fileLink);
        dataTransforming = new DataTransforming(sheet);
    }

    @Test
    public void testCreate() throws Exception {
        csvFile = dataTransforming.gerarCSV();

        assertNotNull(csvFile);
        assertTrue(csvFile.exists());
        assertTrue(csvFile.length() > 0);

        String content = new String(java.nio.file.Files.readAllBytes(csvFile.toPath()));
        assertFalse(content.contains("OD"));
        assertFalse(content.contains("AMB"));
        assertTrue(content.contains("Seg. Odontológica"));
        assertTrue(content.contains("Seg. Ambulatorial"));

    }
}
