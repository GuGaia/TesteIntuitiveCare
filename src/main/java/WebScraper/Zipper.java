package main.java.WebScraper;

import java.io.*;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {
    private final String outputZipPath;
    private List<File> files;
    private File zipFile;

    private Zipper(List<File> files, String path){
        this.files = files;
        this.outputZipPath = path;
        this.zipFile = new File(outputZipPath);
    }
    public static Zipper zipping(List<File> files, String path){
        Zipper zipper = new Zipper(files, path);
        return zipper;
    }
    public void zipFiles() throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (File file : files) {
                if (file != null && file.exists()) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        ZipEntry zipEntry = new ZipEntry(file.getName());
                        zos.putNextEntry(zipEntry);

                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }
                        zos.closeEntry();
                    }
                }
            }
        }
    }

    public File getZipFile() {
        return zipFile;
    }
}
