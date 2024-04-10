package at.ac.tgm.llatschbacher;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class WortPaar {

    private String wort;
    private String url;

    public WortPaar() {}
    public WortPaar(String wort, String url) {
        if(this.check(wort,url)) {
            this.wort = wort;
            this.url = url;
        } else {
            this.wort = "";
            this.url = "";
        }

    }

    public boolean check(String wort, String url) {
        if(wort==null || wort.isEmpty()) {
            return false;
        }
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException ex) {
            return false;
        }

    }
}