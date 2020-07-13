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

class ProgramItems extends CobolCompletionProvider {
    @Override
    public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
        PsiElement typed = parameters.getPosition();

        CobolProgram_ program = PsiTreeUtil.getParentOfType(typed, CobolProgram_.class);

        if (program == null) return;

        for (CobolItemDecl_ var : program.items()) {
            resultSet.addElement(createLookupElement(var));
        }
    }

    protected LookupElement createLookupElement(CobolItemDecl_ var) {
        return LookupElementBuilder
                   .create(var.name())
                   .withIcon(PlatformIcons.VARIABLE_ICON)
                   .withTypeText(typeDescription(var))
                   .withTypeIconRightAligned(true);
    }

    private String typeDescription(CobolItemDecl_ var) {
        if (var instanceof CobolGroupItemDecl_) {
            return "Group item";
        }

        CobolElementaryItemDecl_ elementaryDecl = (CobolElementaryItemDecl_) var;
        String type = elementaryDecl.type();

        if (type.equals(CobolUtil.ALPHA)) {
            return "alpha(" + elementaryDecl.length() + ")";
        }

        if (type.equals(CobolUtil.ALPHANUMERIC)) {
            return "alphanumeric(" + elementaryDecl.length() + ")";
        }

        if (type.equals(CobolUtil.NUMERIC)) {
            return
                (elementaryDecl.isSigned() ? "signed " : "") +
                ("numeric(" + elementaryDecl.length() + ")") +
                (elementaryDecl.hasDecimals() ? "(" + elementaryDecl.decimalLength() + ")" : "");
        }

        return "";
    }

}
