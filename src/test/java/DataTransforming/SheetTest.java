package test.java.DataTransforming;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static test.java.AssertFilesEqual.assertFilesEqual;

public class SheetTest {

    private Sheet sheet;
    private final String nome = "anexo_i_rol_de_procedimentos.xls";
    private final String link = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.xls";
    private final String destino = "Resources";
    private File sheetFile;

    @Before
    public void setUp() {
        sheet = new Sheet(nome, link);
        sheetFile = new File("Downloads/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf");
    }

    @Test
    public void testCreate() throws IOException {
        assertEquals(nome, sheet.getNome());
        assertEquals(link, sheet.getLink());
        assertNull(sheet.getXlsFile());

        sheet.downloadSheet(destino);

        File downloadedSheetFile = sheet.getXlsFile();
        assertFilesEqual(downloadedSheetFile, sheetFile);
    }

}
