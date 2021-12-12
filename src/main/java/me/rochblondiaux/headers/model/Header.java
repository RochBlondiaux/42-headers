package me.rochblondiaux.headers.model;

import java.util.Objects;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class Header {

    private final Author author;
    private String raw;
    private boolean notificationEnabled;
    private boolean autoSaveEnabled;

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

    public boolean areNotificationEnabled() {
        return notificationEnabled;
    }

    public void setNotificationEnabled(boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }

    public boolean isAutoSaveEnabled() {
        return autoSaveEnabled;
    }

    public void setAutoSaveEnabled(boolean autoSaveEnabled) {
        this.autoSaveEnabled = autoSaveEnabled;
    }

    public boolean isValid() {
        return Objects.nonNull(author)
                && author.isValid()
                && Objects.nonNull(raw)
                && !raw.isEmpty();
    }
}
