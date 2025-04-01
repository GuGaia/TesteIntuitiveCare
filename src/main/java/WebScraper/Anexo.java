package main.java.WebScraper;

import java.io.File;

public class Anexo {

    private String name;
    private String dowloadLink;
    private File file = null;

    public Anexo(String name, String dowloadLink){
        this.name = name;
        this.dowloadLink = dowloadLink;
    }

    public String getName(){
        return this.name;
    }
    public String getDowloadLink(){
        return this.dowloadLink;
    }
}
