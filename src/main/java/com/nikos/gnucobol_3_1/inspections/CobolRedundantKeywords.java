package com.nikos.gnucobol_3_1.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.nikos.gnucobol_3_1.psi.*;
import com.sun.istack.NotNull;

import java.util.Collection;
import java.util.stream.Collectors;

public class CobolRedundantKeywords extends CobolInspectionBase {

//    @Override
//    protected void checkFile(CobolFile file, ProblemsHolder holder) {
//        Collection<PsiElement> all = PsiTreeUtil.findChildrenOfType(file, PsiElement.class);
//
//        redundantKeyword(CobolTypes.DATA, all, holder);
//        redundantKeyword(CobolTypes.IS, all, holder);
//    }

    @NotNull
    protected CobolVisitor buildCobolVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new CobolVisitor() {
            @Override
            public void visitItemDecl_(@NotNull CobolItemDecl_ element) {
                Collection<PsiElement> all = PsiTreeUtil.findChildrenOfType(element, PsiElement.class);
                redundantKeyword(CobolTypes.IS, all, holder);
            }

            @Override
            public void visitInitialize_(@NotNull CobolInitialize_ element) {
                Collection<PsiElement> all = PsiTreeUtil.findChildrenOfType(element, PsiElement.class);
                redundantKeyword(CobolTypes.DATA, all, holder);
            }

            @Override
            public void visitIf_(@NotNull CobolIf_ element) {
                Collection<PsiElement> all = PsiTreeUtil.findChildrenOfType(element, PsiElement.class);
                redundantKeyword(CobolTypes.THEN, all, holder);
            }

            @Override
            public void visitCondition_(@NotNull CobolCondition_ element) {
                Collection<PsiElement> all = PsiTreeUtil.findChildrenOfType(element, PsiElement.class);
                redundantKeyword(CobolTypes.IS, all, holder);
                redundantKeyword(CobolTypes.THAN, all, holder);
                redundantKeyword(CobolTypes.TO, all, holder);
            }
        };
    }

    private void redundantKeyword(IElementType type, Collection<PsiElement> all, ProblemsHolder holder) {
        Collection<PsiElement> data = all.stream().filter(element -> element.getNode().getElementType() == type).collect(Collectors.toList());

        for (PsiElement element : data) {
            holder.registerProblem(element, "No spamming please :)", ProblemHighlightType.LIKE_UNUSED_SYMBOL);
        }
    }
}
