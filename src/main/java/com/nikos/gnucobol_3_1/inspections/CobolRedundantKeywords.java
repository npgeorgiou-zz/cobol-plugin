package com.nikos.gnucobol_3_1.inspections;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.nikos.gnucobol_3_1.psi.CobolFile;
import com.nikos.gnucobol_3_1.psi.CobolTypes;

import java.util.Collection;
import java.util.stream.Collectors;

public class CobolRedundantKeywords extends CobolInspectionBase {

    @Override
    protected void checkFile(CobolFile file, ProblemsHolder holder) {
        Collection<PsiElement> all = PsiTreeUtil.findChildrenOfType(file, PsiElement.class);

        redundantKeyword(CobolTypes.DATA, all, holder);
        redundantKeyword(CobolTypes.IS, all, holder);
    }

    private void redundantKeyword(IElementType type, Collection<PsiElement> all, ProblemsHolder holder) {
        Collection<PsiElement> data = all.stream().filter(element -> element.getNode().getElementType() == type).collect(Collectors.toList());

        for (PsiElement element : data) {
            holder.registerProblem(element, "No spamming please :)", ProblemHighlightType.LIKE_UNUSED_SYMBOL);
        }
    }
}
