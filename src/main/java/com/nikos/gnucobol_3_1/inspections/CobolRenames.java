package com.nikos.gnucobol_3_1.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.nikos.gnucobol_3_1.psi.CobolRenamesItemDecl_;
import com.nikos.gnucobol_3_1.psi.CobolVisitor;
import com.sun.istack.NotNull;

public class CobolRenames extends CobolInspectionBase {

    @NotNull
    protected CobolVisitor buildCobolVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new CobolVisitor() {
            @Override
            public void visitRenamesItemDecl_(@NotNull CobolRenamesItemDecl_ declaration) {
                holder.registerProblem(declaration, "Avoid renaming items.", ProblemHighlightType.WARNING);
            }
        };
    }
}
