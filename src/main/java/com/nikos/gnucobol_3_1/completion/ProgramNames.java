package com.nikos.gnucobol_3_1.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.util.ProcessingContext;
import com.nikos.gnucobol_3_1.CobolUtil;
import com.nikos.gnucobol_3_1.Util;
import com.nikos.gnucobol_3_1.psi.CobolTypes;
import org.jetbrains.annotations.NotNull;

class ProgramNames extends CobolCompletionProvider {
    @Override
    public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
        Project project = parameters.getOriginalFile().getProject();
        boolean typingWithoutQuotes = parameters.getPosition().getNode().getElementType() == CobolTypes.IDENTIFIER;

        CobolUtil.allCobolFiles(project).stream()
            .flatMap(file -> file.allTypedPrograms().stream())
            .map(program -> program.name())
            .map(name -> typingWithoutQuotes ? Util.quote(name) : name)
            .forEach(name -> resultSet.addElement(LookupElementBuilder.create(name)));
    }
}
