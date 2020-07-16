package com.nikos.gnucobol_3_1.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.PlatformIcons;
import com.intellij.util.ProcessingContext;
import com.nikos.gnucobol_3_1.CobolUtil;
import com.nikos.gnucobol_3_1.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.stream.Collectors;

class NonConditionalProgramItems extends ProgramItems {
    @Override
    public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
        PsiElement typed = parameters.getPosition();

        CobolProgram_ program = PsiTreeUtil.getParentOfType(typed, CobolProgram_.class);

        if (program == null) return;

        Collection<CobolItemDecl_> items = program.items();
        items = items.stream().filter(it -> !(it instanceof CobolConditionalItemDecl_)).collect(Collectors.toList());

        for (CobolItemDecl_ var : items) {
            resultSet.addElement(createLookupElement(var));
        }
    }
}
