package me.rochblondiaux.headers.model;

import java.util.Objects;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class Author {

    private String name;
    private String email;

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValid() {
        return Objects.nonNull(name)
                && Objects.nonNull(email)
                && !email.isEmpty()
                && !email.isBlank()
                && !name.isEmpty()
                && !name.isBlank();
    }
}
