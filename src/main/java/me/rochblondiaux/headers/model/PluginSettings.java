package me.rochblondiaux.headers.model;

import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.ui.EditorNotificationPanel;
import me.rochblondiaux.headers.ui.SettingsUI;
import me.rochblondiaux.headers.utils.ConfigurationUtils;
import me.rochblondiaux.headers.utils.HeaderUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class PluginSettings implements SearchableConfigurable {

    private SettingsUI ui;
    public static final Header HEADER;

    static {
        HEADER = new Header(new Author("404",
                "null@student.42-lyon.fr"),
                "null");
        ConfigurationUtils.init(HEADER);
        ConfigurationUtils.update(HEADER);
    }

    @Override
    public @NotNull
    @NonNls String getId() {
        return "preferences.42headers";
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "42 Headers";
    }

    @Override
    public @Nullable JComponent createComponent() {
        this.ui = new SettingsUI();
        ConfigurationUtils.update(HEADER);
        ui.getAuthorName().setText(HEADER.getAuthor().getName());
        ui.getAuthorEmail().setText(HEADER.getAuthor().getEmail());
        ui.getHeaderRaw().setText(HEADER.getRaw());
        ui.getAutoUpdate().setSelected(HEADER.isAutoSaveEnabled());
        ui.getNotifications().setSelected(HEADER.areNotificationEnabled());

        return ui.getPanel();
    }

    @Override
    public boolean isModified() {
        return !(ui.getAuthorName().getText().equals(HEADER.getAuthor().getName())
                && ui.getAuthorEmail().getText().equals(HEADER.getAuthor().getEmail())
                && ui.getHeaderRaw().getText().equals(HEADER.getRaw())
                && ui.getNotifications().isSelected() == HEADER.areNotificationEnabled()
                && ui.getAutoUpdate().isSelected() == HEADER.isAutoSaveEnabled());
    }

    @Override
    public void apply() {
        HEADER.setRaw(ui.getHeaderRaw().getText());
        HEADER.getAuthor().setName(ui.getAuthorName().getText());
        HEADER.getAuthor().setEmail(ui.getAuthorEmail().getText());
        HEADER.setAutoSaveEnabled(ui.getAutoUpdate().isSelected());
        HEADER.setNotificationEnabled(ui.getNotifications().isSelected());
        ConfigurationUtils.save(HEADER);
    }
}
