package com.nikos.gnucobol_3_1;


import com.intellij.lang.cacheBuilder.*;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.*;
import com.intellij.psi.tree.TokenSet;
import com.intellij.usageView.UsageViewLongNameLocation;
import com.intellij.usageView.UsageViewShortNameLocation;
import com.nikos.gnucobol_3_1.psi.CobolTypes;
import org.jetbrains.annotations.*;

public class CobolFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(
                new CobolLexerAdapter(),
                TokenSet.create(CobolTypes.IDENTIFIER),
                CobolParserDefinition.COMMENTS,
                TokenSet.EMPTY
        );
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof PsiNamedElement;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        if (element instanceof PsiElement) {
            return element.getClass().getName();
        } else {
            return "";
        }
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        return ElementDescriptionUtil.getElementDescription(element, UsageViewLongNameLocation.INSTANCE);
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        return ElementDescriptionUtil.getElementDescription(element, UsageViewShortNameLocation.INSTANCE);
    }
}