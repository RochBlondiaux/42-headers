package me.rochblondiaux.headers.utils;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.WindowManager;
import me.rochblondiaux.headers.model.Header;
import me.rochblondiaux.headers.model.PluginSettings;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class HeaderUtils {

    public final static String DEFAULT_HEADER =
            "/* ************************************************************************** */\n" +
                    "/*                                                                            */\n" +
                    "/*                                                        :::      ::::::::   */\n" +
                    "/*   {file}                                             :+:      :+:    :+:   */\n" +
                    "/*                                                    +:+ +:+         +:+     */\n" +
                    "/*   By: {author} <{email}>                         +#+  +:+       +#+        */\n" +
                    "/*                                                +#+#+#+#+#+   +#+           */\n" +
                    "/*   Created: {creation_date} by {author}              #+#    #+#             */\n" +
                    "/*   Updated: {update_date} by {author}               ###   ########.fr       */\n" +
                    "/*                                                                            */\n" +
                    "/* ************************************************************************** */\n";

    public static void insert(VirtualFile file, Editor editor) {
        if (Objects.isNull(file) || file.isDirectory())
            return;
        if (hasHeader(file)) {
            update(file);
            return;
        }
        Header header = PluginSettings.HEADER;
        boolean isMakefile = file.getName().equalsIgnoreCase("Makefile");

        String raw = StringUtils.format(file.getName(), isMakefile, true);
        getActiveProject().ifPresent(project -> {
            Runnable runnable = () -> editor.getDocument().insertString(0, raw);
            WriteCommandAction.runWriteCommandAction(project, runnable);
        });
    }

    public static void update(VirtualFile file) {
        if (Objects.isNull(file) || file.isDirectory() || !hasHeader(file))
            return;
        Document doc = FileDocumentManager.getInstance().getDocument(file);
        if (Objects.isNull(doc))
            return;
        boolean isMakefile = file.getName().equalsIgnoreCase("Makefile");
        Header header = PluginSettings.HEADER;
        String raw = StringUtils.format(file.getName(), isMakefile, true);

        Stream.of(raw.split("\n"))
                .filter(e -> e.contains("Updated:"))
                .findFirst()
                .ifPresent(line -> {
                    getActiveProject().ifPresent(project -> {
                        int offset = StringUtils.getOffsetUpdatedLine(isMakefile);

                        if (offset != -1) {
                            Runnable updateHeader = () -> doc.replaceString(offset, offset + line.length(), line);
                            if (doc.getModificationStamp() > 0)
                                WriteCommandAction.runWriteCommandAction(project, updateHeader);
                        }
                    });
                });
    }

    public static boolean hasHeader(VirtualFile file) {
        try {
            String str = new String(file.getInputStream().readNBytes(3));
            return str.startsWith("/* ") || str.startsWith("# *");
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static Optional<Project> getActiveProject() {
        return Arrays.stream(ProjectManager.getInstance()
                        .getOpenProjects())
                .filter(project -> {
                    Window window = WindowManager.getInstance().suggestParentWindow(project);
                    return window != null && window.isActive();
                })
                .findFirst();
    }
}
