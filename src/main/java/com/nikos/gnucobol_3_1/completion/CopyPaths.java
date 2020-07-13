package com.nikos.gnucobol_3_1.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.util.PlatformIcons;
import com.intellij.util.ProcessingContext;
import com.nikos.gnucobol_3_1.CobolUtil;
import com.nikos.gnucobol_3_1.Util;
import com.nikos.gnucobol_3_1.psi.CobolTypes;
import org.jetbrains.annotations.NotNull;

class CopyPaths extends CobolCompletionProvider {
    @Override
    public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
        Project project = parameters.getOriginalFile().getProject();
        boolean typingWithoutQuotes = parameters.getPosition().getNode().getElementType() == CobolTypes.IDENTIFIER;

        CobolUtil.allCobolFiles(project).stream()
            .filter(file -> !file.getVirtualFile().getPath().equals(parameters.getOriginalFile().getVirtualFile().getPath()))
            .map(file -> file.getVirtualFile().getPath())
            .map(path -> path.replace(CobolUtil.COPY_PATH, ""))
            .map(path -> typingWithoutQuotes ? Util.quote(path) + "." : path)
            .forEach(path -> resultSet.addElement(createLookupElement(path)));
    }

    protected LookupElement createLookupElement(String path) {
        return LookupElementBuilder.create(path).withIcon(PlatformIcons.FILE_ICON);
    }
}
