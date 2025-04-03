package test.java.WebScraper;


import main.java.WebScraper.Anexo;
import org.junit.Before;
import org.junit.Test;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static test.java.AssertFilesEqual.assertFilesEqual;

public class AnexoTest {

    private Anexo anexo1;
    private Anexo anexo2;
    private File downloadedFile1;
    private File downloadedFile2;
    private String anexo1Name;
    private String anexo2Name;
    private String anexo1Link;
    private String anexo2Link;
    private String pageLink;

    @Before
    public void setUp(){
        anexo1Name = "Anexo_I_Rol_2021RN_465.2021_RN627L.2024";
        anexo2Name = "Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025";
        pageLink = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
        anexo1Link = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf";
        anexo2Link = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025.pdf";
        downloadedFile1 = new File("Downloads/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf");
        downloadedFile2 = new File("Downloads/Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025.pdf");
    }
    @Test
    public void testCreateNameAndPagelink() throws IOException {
        anexo1 = new Anexo(anexo1Name, pageLink);
        anexo2= new Anexo(anexo2Name, pageLink);
        assertEquals(anexo1.getName(), anexo1Name);
        assertEquals(anexo2.getName(), anexo2Name);

        assertTrue(anexo1.getDownloadLink().endsWith(anexo1Name + ".pdf"));
        assertTrue(anexo2.getDownloadLink().endsWith(anexo2Name + ".pdf"));

        File anexo1File = anexo1.getFile();
        File anexo2File = anexo2.getFile();

        assertFilesEqual(anexo1File, downloadedFile1);
        assertFilesEqual(anexo2File, downloadedFile2);
    }


}
