package com.nikos.gnucobol_3_1.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.nikos.gnucobol_3_1.psi.*;
import com.sun.istack.NotNull;

public class CobolListsWithoutCommas extends CobolInspectionBase {

    @NotNull
    protected CobolVisitor buildCobolVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new CobolVisitor() {
            @Override
            public void visitDisplay_(@NotNull CobolDisplay_ display) {
                checkItemAndLiteralList(display, holder);
            }

            public void visitMove_(@NotNull CobolMove_ display) {
                checkItemList(display, holder);
            }

            public void visitInitialize_(@NotNull CobolInitialize_ initialize) {
                checkItemList(initialize, holder);
            }

            public void visitAdd_(@NotNull CobolAdd_ add) {
                checkItemAndLiteralList(add, holder);
            }

            public void visitSubtract_(@NotNull CobolSubtract_ subtract) {
                checkItemAndLiteralList(subtract, holder);
            }

            public void visitMultiply_(@NotNull CobolMultiply_ multiply) {
                checkItemList(multiply, holder);
            }

            public void visitDivide_(@NotNull CobolDivide_ divide) {
                checkItemList(divide, holder);
            }

            public void visitCompute_(@NotNull CobolCompute_ compute) {
                checkItemList(compute, holder);
            }

            public void visitSet_(@NotNull CobolSet_ set) {
                checkItemList(set, holder);
            }
        };
    }

    protected void checkItemList(PsiElement element, ProblemsHolder holder) {
        int itemUsagesInRow = 0;
        PsiElement child = element.getFirstChild();

        while ((child = child.getNextSibling()) != null) {
            if (child instanceof PsiWhiteSpace) {
                continue;
            }

            if (!(child instanceof CobolItemUsage_)) {
                itemUsagesInRow = 0;
                continue;
            }

            itemUsagesInRow++;
            if (itemUsagesInRow == 2) {
                // Add a highlight to the space before.
                holder.registerProblem(child.getPrevSibling(), "A comma separated list would help readability", ProblemHighlightType.WARNING);
                itemUsagesInRow = 1;
            }
        }
    }

    protected void checkItemAndLiteralList(PsiElement element, ProblemsHolder holder) {
        int listItemsInRow = 0;
        PsiElement child = element.getFirstChild();

        while ((child = child.getNextSibling()) != null) {
            if (child instanceof PsiWhiteSpace) {
                continue;
            }

            if (
                    !(child instanceof CobolItemUsage_)
                    && child.getNode().getElementType() != CobolTypes.STRING
                    && child.getNode().getElementType() != CobolTypes.INTEGER
                    && child.getNode().getElementType() != CobolTypes.FLOAT
                )
            {
                listItemsInRow = 0;
                continue;
            }

            listItemsInRow++;
            if (listItemsInRow == 2) {
                // Add a highlight to the space before.
                holder.registerProblem(child.getPrevSibling(), "A comma separated list would help readability", ProblemHighlightType.WARNING);
                listItemsInRow = 1;
            }
        }
    }
}
