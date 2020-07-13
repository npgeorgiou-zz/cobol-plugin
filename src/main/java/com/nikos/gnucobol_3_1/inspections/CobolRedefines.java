package com.nikos.gnucobol_3_1.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.nikos.gnucobol_3_1.psi.CobolElementaryItemDecl_;
import com.nikos.gnucobol_3_1.psi.CobolItemDecl_;
import com.nikos.gnucobol_3_1.psi.CobolItemNameDecl_;
import com.nikos.gnucobol_3_1.psi.CobolVisitor;
import com.sun.istack.NotNull;

public class CobolRedefines extends CobolInspectionBase {

    @NotNull
    protected CobolVisitor buildCobolVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new CobolVisitor() {
            @Override
            public void visitItemDecl_(@NotNull CobolItemDecl_ declaration) {
                if (!(declaration instanceof CobolElementaryItemDecl_)) return;

                if (((CobolElementaryItemDecl_) declaration).redefines() == null) return;

                holder.registerProblem(declaration, "Avoid redefining items.", ProblemHighlightType.WARNING);
            }
        };
    }
}
