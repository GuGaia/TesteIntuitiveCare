package test.java.DataTransforming;
import main.java.DataTransforming.Sheet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static test.java.AssertFilesEqual.assertFilesEqual;

public class SheetTest {

    private Sheet sheet;
    private final String nome = "Anexo_I_Rol_2021RN_465.2021_RN627L.2024.xlsx";
    private final String destino = "Resources";
    private File sheetFile;

    @Before
    public void setUp() throws IOException {
        String pageLink = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
        sheet = new Sheet(nome, pageLink);
        sheetFile = new File("Downloads/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf");
    }

    @Test
    public void testCreate() throws IOException {
        assertEquals(nome, sheet.getName());
        String link = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.xlsx";
        assertEquals(link, sheet.getLink());

        File downloadedSheetFile = sheet.getXlsFile();
        assertFilesEqual(downloadedSheetFile, sheetFile);
    }

}
