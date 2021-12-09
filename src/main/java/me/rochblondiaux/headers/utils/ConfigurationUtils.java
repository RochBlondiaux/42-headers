package me.rochblondiaux.headers.utils;

import com.intellij.ide.util.PropertiesComponent;
import me.rochblondiaux.headers.model.Header;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class ConfigurationUtils {

    public static final String AUTHOR_NAME = "42headers_author_name";
    public static final String AUTHOR_EMAIL = "42headers_author_email";
    public static final String HEADER_RAW = "42headers_raw";

    public static void save(Header header) {
        set(AUTHOR_NAME, header.getAuthor().getName());
        set(AUTHOR_EMAIL, header.getAuthor().getEmail());
        set(HEADER_RAW, header.getRaw());
    }

    public static void update(Header header) {
        header.setRaw(get(HEADER_RAW, HeaderUtils.DEFAULT_HEADER));
        header.getAuthor().setName(get(AUTHOR_NAME, System.getenv("USER")));
        header.getAuthor().setEmail(get(AUTHOR_EMAIL, System.getenv("USER") + "@student.42-lyon.fr"));
    }

    public static void init(Header header) {
        if (exists(AUTHOR_NAME) && exists(AUTHOR_EMAIL) && exists(HEADER_RAW))
            return;
        set(HEADER_RAW, HeaderUtils.DEFAULT_HEADER);
        set(AUTHOR_NAME, System.getenv("USER"));
        set(AUTHOR_EMAIL, System.getenv("USER") + "@student.42-lyon.fr");
    }

    public static void reset(Header header) {
        set(HEADER_RAW, HeaderUtils.DEFAULT_HEADER);
        set(AUTHOR_NAME, System.getenv("USER"));
        set(AUTHOR_EMAIL, System.getenv("USER") + "@student.42-lyon.fr");
    }

    private static void set(String key, String value) {
        PropertiesComponent.getInstance().setValue(key, value);
    }

    private static boolean exists(String key) {
        return PropertiesComponent.getInstance().isValueSet(key);
    }

    private static void unset(String key) {
        PropertiesComponent.getInstance().unsetValue(key);
    }

    private static String get(String key, String d) {
        if (!exists(key))
            set(key, d);
        return PropertiesComponent.getInstance().getValue(key);
    }
}
