package me.rochblondiaux.headers.listener;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.vfs.VirtualFile;
import me.rochblondiaux.headers.utils.HeaderUtils;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class KeyStrokeListener extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        VirtualFile file = e.getData(VIRTUAL_FILE);
        Editor editor = e.getData(LangDataKeys.EDITOR);

        HeaderUtils.insert(file, editor);
    }
}
