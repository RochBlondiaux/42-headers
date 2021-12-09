package me.rochblondiaux.headers.model;

import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import me.rochblondiaux.headers.ui.SettingsUI;
import me.rochblondiaux.headers.utils.ConfigurationUtils;
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
    private static final Header header;

    static {
        header = new Header(new Author("404",
                "null@student.42-lyon.fr"),
                "null");
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
        ConfigurationUtils.init(header);
        ConfigurationUtils.update(header);
        ui.getAuthorName().setText(header.getAuthor().getName());
        ui.getAuthorEmail().setText(header.getAuthor().getEmail());
        ui.getHeaderRaw().setText(header.getRaw());
        return ui.getPanel();
    }

    @Override
    public boolean isModified() {
        return !(ui.getAuthorName().getText().equals(header.getAuthor().getName())
                && ui.getAuthorEmail().getText().equals(header.getAuthor().getEmail())
                && ui.getHeaderRaw().getText().equals(header.getRaw()));
    }

    @Override
    public void apply() {
        header.setRaw(ui.getHeaderRaw().getText());
        header.getAuthor().setName(ui.getAuthorName().getText());
        header.getAuthor().setEmail(ui.getAuthorEmail().getText());
        ConfigurationUtils.save(header);
    }
}
