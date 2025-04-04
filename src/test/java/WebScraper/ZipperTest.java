package test.java.WebScraper;

import main.java.WebScraper.Anexo;
import main.java.WebScraper.Zipper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ZipperTest {

    private Anexo anexo1;
    private Anexo anexo2;
    private File file1;
    private File file2;
    private Zipper zipper;
    private File zipFile;
    private final String path = "Resources/anexosPdf_compactados.zip";

    @Before
    public void setUp() throws IOException {
        String anexo1Name = "Anexo_I_Rol_2021RN_465.2021_RN627L.2024";
        String anexo2Name = "Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025";
        String pageLink = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

        anexo1 = new Anexo(anexo1Name, pageLink);
        anexo2 = new Anexo(anexo2Name, pageLink);
        file1 = anexo1.getFile();
        file2 = anexo2.getFile();
        zipper = Zipper.zipping(Arrays.asList(file1, file2), path);
    }

    @Test
    public void testZipFiles() throws Exception {
        assertTrue(file1.exists());
        assertTrue(file2.exists());
        zipper.zipFiles();
        zipFile = zipper.getZipFile();

        assertNotNull(zipFile);
        assertTrue(zipFile.exists());
        assertTrue(zipFile.length() > 0);

        java.util.zip.ZipFile zip = new java.util.zip.ZipFile(zipFile);
        assertNotNull(zip.getEntry(file1.getName()));
        assertNotNull(zip.getEntry(file2.getName()));
        zip.close();
    }
}
