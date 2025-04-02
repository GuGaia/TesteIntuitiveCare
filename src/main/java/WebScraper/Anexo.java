package main.java.WebScraper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Anexo {

    private final String name;
    private final String dowloadLink;
    private File file = null;

    public Anexo(String name, String pageLink){
        this.name = name;
        this.dowloadLink = getAnexoDowloadLink(pageLink);
        this.file = downloadFile(this.dowloadLink);
    }

    public String getName(){
        return this.name;
    }
    public String getDownloadLink(){
        return this.dowloadLink;
    }
    private String getAnexoDowloadLink(String pageLink){
        if (pageLink.endsWith("/")) {
            return pageLink + name + ".pdf";
        } else {
            return pageLink + "/" + name + ".pdf";
        }
    }
    public File getFile(){
        return this.file;
    }
    private File downloadFile(String link) {
        try {
            File downloadsDir = new File("Resources");
            if (!downloadsDir.exists()) {
                downloadsDir.mkdirs();
            }
            File outFile = new File(downloadsDir, name + ".pdf");

            try (InputStream in = new URL(link).openStream();
                 FileOutputStream fos = new FileOutputStream(outFile)) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }
            return outFile;

        } catch (IOException e) {
            System.err.println("Falha ao baixar arquivo: " + e.getMessage());
            return null;
        }
    }
}
