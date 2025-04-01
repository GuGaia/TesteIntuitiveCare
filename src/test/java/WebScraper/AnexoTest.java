package test.java.WebScraper;


import main.java.WebScraper.Anexo;
import org.junit.Before;
import org.junit.Test;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

import static org.junit.Assert.assertEquals;

public class AnexoTest {

    private Anexo anexo1;
    private Anexo anexo2;

    @Before
    public void setUp(){
        anexo1 = new Anexo("Anexo_I_Rol_2021RN_465.2021_RN627L.2024","https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf");
        anexo2 = new Anexo("Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025","https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025.pdf");
    }

    @Test
    public void testCreate(){
        String nameAnexo1 = "Anexo_I_Rol_2021RN_465.2021_RN627L.2024";
        String nameAnexo2 = "Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025";
        assertEquals(anexo1.getName(), nameAnexo1);
        assertEquals(anexo2.getName(), nameAnexo2);
        String anexo1Link = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf";
        String anexo2Link = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025.pdf";
        assertEquals(anexo1.getDowloadLink(), anexo1Link);
        assertEquals(anexo2.getDowloadLink(), anexo2Link);
    }

}
