package com.nikos.gnucobol_3_1.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import com.nikos.gnucobol_3_1.CobolUtil;
import com.nikos.gnucobol_3_1.psi.CobolElementaryItemDecl_;
import com.nikos.gnucobol_3_1.psi.CobolItemDecl_;
import com.nikos.gnucobol_3_1.psi.CobolProgram_;
import org.jetbrains.annotations.NotNull;

class NumericItems extends ProgramItems {
    @Override
    public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
        PsiElement typed = parameters.getPosition();
        CobolProgram_ program = PsiTreeUtil.getParentOfType(typed, CobolProgram_.class);

        if (program == null) return;

        for (CobolItemDecl_ item : program.elementaryItems()) {
            if (!((CobolElementaryItemDecl_) item).type().equals(CobolUtil.NUMERIC)) continue;

            resultSet.addElement(createLookupElement(item));
        }
    }
}
