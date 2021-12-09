package me.rochblondiaux.headers.utils;

import com.google.common.collect.ImmutableMap;
import me.rochblondiaux.headers.model.PluginSettings;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class StringUtils {

    public static String format(String filename, boolean isMakefile, boolean create) {
        String raw = PluginSettings.HEADER.getRaw();

        Map<String, String> placeholders = ImmutableMap.of("{file}", filename,
                "{author}", PluginSettings.HEADER.getAuthor().getName(),
                "{email}", PluginSettings.HEADER.getAuthor().getEmail(),
                "{update_date}", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        raw = replacePlaceholders(raw, placeholders);
        if (create)
            raw = replacePlaceholder(raw, "{creation_date}", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        if (isMakefile)
            raw = replaceBounds(raw, "#");
        return raw;
    }

    public static String replaceBounds(String line, String new_spacer) {
        line = line.replaceAll("/\\*", new_spacer);
        line = line.replaceAll("\\*/", new_spacer);
        return line;
    }

    public static String replacePlaceholders(String header, Map<String, String> placeholders) {
        StringBuilder formatted = new StringBuilder();
        for (String line : header.split("\n")) {
            for (Map.Entry<String, String> placeholder : placeholders.entrySet())
                line = replacePlaceholder(line, placeholder.getKey(), placeholder.getValue());
            formatted.append(line).append("\n");
        }
        return formatted.toString();
    }

    public static String replacePlaceholder(String header, String key, String value) {
        String i;

        if (value.length() < key.length())
            value += " ".repeat(key.length() - value.length());
        i = header.replace(key, value);
        if (value.length() > key.length())
            i = remove_spaces(i, value, value.length() - key.length());
        return i;
    }

    private static String remove_spaces(String str, String replacement, int spaces) {
        int index = str.indexOf(replacement);
        if (index == -1)
            return str;
        index += replacement.length();


        StringBuilder strBuilder = new StringBuilder();
        int founds = 0;
        char[] rmString = str.toCharArray();
        strBuilder.append(str, 0, index);
        for (int i = index; i < rmString.length; i++) {
            if (rmString[i] == ' ' && founds < spaces && (i + 1 >= rmString.length || rmString[i + 1] == ' '))
                founds++;
            else
                strBuilder.append(rmString[i]);
        }
        return strBuilder.toString();
    }

    public static int getOffsetUpdatedLine(boolean isMakefile) {
        String raw = PluginSettings.HEADER.getRaw();
        if (isMakefile)
            raw = replaceBounds(raw, "#");

        int offset = 0;
        for (String s : raw.split("\n")) {
            if (s.contains("{update_date}"))
                return offset;
            offset += s.length() + 1;
        }
        return -1;
    }
}
