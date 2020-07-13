package com.nikos.gnucobol_3_1.inspections;

import com.intellij.codeInspection.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.util.ObjectUtils;
import com.nikos.gnucobol_3_1.psi.CobolFile;
import com.nikos.gnucobol_3_1.psi.CobolVisitor;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

public class CobolInspectionBase extends LocalInspectionTool {
    @NotNull
    @Override
    public final PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly, @NotNull LocalInspectionToolSession session) {
        CobolFile file = ObjectUtils.tryCast(session.getFile(), CobolFile.class);
        return file != null ? buildCobolVisitor(holder, session) : new CobolVisitor() ;
    }

    @NotNull
    @Override
    public final PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        throw new IllegalStateException();
    }


    @Nullable
    @Override
    public final ProblemDescriptor[] checkFile(@NotNull PsiFile file, @NotNull InspectionManager manager, boolean isOnTheFly) {
        throw new IllegalStateException();
    }


    @NotNull
    protected CobolVisitor buildCobolVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new CobolVisitor() {
            @Override
            public void visitFile(PsiFile file) {
                checkFile((CobolFile) file, holder);
            }
        };
    }

    protected void checkFile(@NotNull CobolFile file, @NotNull ProblemsHolder problemsHolder) {
    }
}
