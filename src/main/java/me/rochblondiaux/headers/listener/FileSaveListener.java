package me.rochblondiaux.headers.listener;

import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import me.rochblondiaux.headers.utils.HeaderUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class FileSaveListener implements BulkFileListener {

    @Override
    public void after(@NotNull List<? extends VFileEvent> events) {
        events.forEach(e -> {
            if (e.isFromSave() || e.isFromRefresh())
                HeaderUtils.update(e.getFile());
        });
    }

}
