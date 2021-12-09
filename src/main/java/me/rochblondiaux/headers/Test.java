package me.rochblondiaux.headers;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import me.rochblondiaux.headers.ui.SettingsUI;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class Test implements SearchableConfigurable  {

    @Override
    public @NotNull @NonNls String getId() {
        return "preferences.42Header";
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "42 Headers";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return new SettingsUI().getPanel();
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }
}
