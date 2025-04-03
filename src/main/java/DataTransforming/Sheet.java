package main.java.DataTransforming;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Sheet {

    private final String name;
    private final String downloadlink;
    private File XlsxFile = null;

    public Sheet(String name, String link) throws IOException {
        this.name = name;
        this.downloadlink = getAnexoDowloadLink(link);
        this.XlsxFile = downloadSheet();
    }

    private String getAnexoDowloadLink(String pageLink){
        if (pageLink.endsWith("/")) {
            return pageLink + name;
        } else {
            return pageLink + "/" + name;
        }
    }

    public File downloadSheet() throws IOException {
        try {
            File downloadsDir = new File("Resources");
            if (!downloadsDir.exists()) {
                downloadsDir.mkdirs();
            }
            File outFile = new File(downloadsDir, name);

            try (InputStream in = new URL(downloadlink).openStream();
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

    public String getName() {
        return name;
    }

    public String getLink() {
        return downloadlink;
    }

    public File getXlsFile() {
        return this.XlsxFile;
    }
}

