package me.rochblondiaux.headers.model;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class Header {

    private final Author author;
    private String raw;

    public Header(Author author, String raw) {
        this.author = author;
        this.raw = raw;
    }

    public Author getAuthor() {
        return author;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
