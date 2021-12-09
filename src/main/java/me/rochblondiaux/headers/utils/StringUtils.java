package me.rochblondiaux.headers.utils;

import com.intellij.openapi.vfs.VirtualFile;
import me.rochblondiaux.headers.model.Header;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class StringUtils {

    public static String format(Header header, String filename, boolean isMakefile, boolean create) {
        String raw = header.getRaw();

        raw = replacePlaceholder(raw, "{file}", filename);
        raw = replacePlaceholder(raw, "{author}", header.getAuthor().getName());
        raw = replacePlaceholder(raw, "{email}", header.getAuthor().getEmail());
        if (create)
            raw = replacePlaceholder(raw, "{creation_date}", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        raw = replacePlaceholder(raw, "{update_date}", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        if (isMakefile)
            raw = replaceBounds(raw, "#");
        return raw;
    }

    public static String replaceBounds(String line, String new_spacer) {
        line = line.replaceAll("/\\*", new_spacer);
        line = line.replaceAll("\\*/", new_spacer);
        return line;
    }

    public static String replacePlaceholder(String header, String key, String value) {
        if (value.length() < key.length())
            value += " ".repeat(key.length() - value.length());
        else if (value.length() > key.length())
            key += " ".repeat(value.length() - key.length());
        return header.replace(key, value);
    }

}
