package me.rochblondiaux.headers.ui;

import javax.swing.*;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class SettingsUI {

    private JPanel panel1;
    private JTextField authorName;
    private JTextField authorEmail;
    private JTextArea headerRaw;
    private JLabel authorNameLabel;
    private JLabel authorEmailLabel;
    private JCheckBox notifications;
    private JCheckBox autoUpdate;

    public JPanel getPanel() {
        return panel1;
    }

    public JTextField getAuthorName() {
        return authorName;
    }

    public JTextField getAuthorEmail() {
        return authorEmail;
    }

    public JTextArea getHeaderRaw() {
        return headerRaw;
    }

    public JCheckBox getNotifications() {
        return notifications;
    }

    public JCheckBox getAutoUpdate() {
        return autoUpdate;
    }
}
