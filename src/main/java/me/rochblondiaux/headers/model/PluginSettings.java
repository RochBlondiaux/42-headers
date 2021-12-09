package me.rochblondiaux.headers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Getter
@Setter
@AllArgsConstructor
public class PluginSettings {

    private final Author author;
    private String raw;

}
