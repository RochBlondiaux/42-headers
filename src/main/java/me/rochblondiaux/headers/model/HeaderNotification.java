package me.rochblondiaux.headers.model;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class HeaderNotification extends Notification {


    public HeaderNotification(String title, String message, NotificationType type) {
        super("42Headers", null, "42 Headers", title, message, type, null);
    }
}