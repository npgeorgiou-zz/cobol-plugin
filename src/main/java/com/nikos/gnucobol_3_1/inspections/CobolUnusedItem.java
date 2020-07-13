package com.nikos.gnucobol_3_1.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.nikos.gnucobol_3_1.psi.CobolItemNameDecl_;
import com.nikos.gnucobol_3_1.psi.CobolVisitor;
import com.sun.istack.NotNull;

public class CobolUnusedItem extends CobolInspectionBase {

    @NotNull
    protected CobolVisitor buildCobolVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new CobolVisitor() {
            @Override
            public void visitItemNameDecl_(@NotNull CobolItemNameDecl_ name) {
                GlobalSearchScope scope = GlobalSearchScope.FilesScope.fileScope(name.getContainingFile());

                if (ReferencesSearch.search(name, scope).findFirst() == null) {
                    holder.registerProblem(name, "Unused item.", ProblemHighlightType.LIKE_UNUSED_SYMBOL);
                }
            }
        };
    }
}
