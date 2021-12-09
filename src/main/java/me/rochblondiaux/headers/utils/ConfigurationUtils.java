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
    public static final String NOTIFICATION = "42headers_notifications";
    public static final String AUTO_SAVE = "42headers_autosave";

    public static void save(Header header) {
        set(AUTHOR_NAME, header.getAuthor().getName());
        set(AUTHOR_EMAIL, header.getAuthor().getEmail());
        set(HEADER_RAW, header.getRaw());
        set(NOTIFICATION, header.areNotificationEnabled());
        set(AUTO_SAVE, header.isAutoSaveEnabled());

    }

    public static void update(Header header) {
        header.setRaw(get(HEADER_RAW, HeaderUtils.DEFAULT_HEADER));
        header.getAuthor().setName(get(AUTHOR_NAME, System.getenv("USER")));
        header.getAuthor().setEmail(get(AUTHOR_EMAIL, System.getenv("USER") + "@student.42-lyon.fr"));
        header.setNotificationEnabled(get(NOTIFICATION));
        header.setAutoSaveEnabled(get(AUTO_SAVE));
    }

    public static void init(Header header) {
        if (exists(AUTHOR_NAME) && exists(AUTHOR_EMAIL) && exists(HEADER_RAW) && exists(AUTO_SAVE) && exists(NOTIFICATION))
            return;
        set(HEADER_RAW, HeaderUtils.DEFAULT_HEADER);
        set(AUTHOR_NAME, System.getenv("USER"));
        set(AUTHOR_EMAIL, System.getenv("USER") + "@student.42-lyon.fr");
        set(AUTO_SAVE, true);
        set(NOTIFICATION, true);
    }

    private static void set(String key, String value) {
        PropertiesComponent.getInstance().setValue(key, value);
    }

    private static void set(String key, boolean value) {
        PropertiesComponent.getInstance().setValue(key, value);
    }

    private static boolean exists(String key) {
        return PropertiesComponent.getInstance().isValueSet(key);
    }

    private static boolean get(String key) {
        if (PropertiesComponent.getInstance().isTrueValue(key))
            set(key, true);
        return PropertiesComponent.getInstance().getBoolean(key);
    }

    private static String get(String key, String d) {
        if (!exists(key))
            set(key, d);
        return PropertiesComponent.getInstance().getValue(key);
    }
}
