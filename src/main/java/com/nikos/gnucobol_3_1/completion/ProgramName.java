package com.nikos.gnucobol_3_1.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.nikos.gnucobol_3_1.psi.CobolProgram_;
import org.jetbrains.annotations.NotNull;

class ProgramName extends CobolCompletionProvider {
    @Override
    public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
        PsiElement element = parameters.getPosition();
        CobolProgram_ program = (CobolProgram_) element.getParent().getParent().getParent().getParent();
        resultSet.addElement(LookupElementBuilder.create(program.name()));
    }
}
