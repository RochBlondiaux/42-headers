package me.rochblondiaux.headers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Getter
@Setter
@AllArgsConstructor
public class Header {

    private final Author author;
    private final Date creationDate;
    private Date updateDate;
}
