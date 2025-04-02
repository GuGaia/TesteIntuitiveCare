package main.java.WebScraper;

import java.io.File;

public class Anexo {

    private String name;
    private String dowloadLink;
    private File file = null;

    public Anexo(String name, String pageLink){
        this.name = name;
        this.dowloadLink = getAnexoDowloadLink(pageLink);
        this.file = downloadFile();
    }

    public String getName(){
        return this.name;
    }
    public String getDowloadLink(){
        return this.dowloadLink;
    }
}
