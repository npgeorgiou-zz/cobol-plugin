package com.nikos.gnucobol_3_1.inspections;

import com.google.common.collect.Iterables;
import com.intellij.codeInspection.*;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.nikos.gnucobol_3_1.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public class CobolNonSymbolConditions extends CobolInspectionBase {

    @NotNull
    protected CobolVisitor buildCobolVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new CobolVisitor() {
            @Override
            public void visitCondition_(@NotNull CobolCondition_ element) {
                if (element.getNode().findChildByType(CobolTypes.POSITIVE) != null) {
                    LocalQuickFix[] fixes = new LocalQuickFix[]{new PositiveToSymbolsFix(element)};
                    suggestChange(element, holder, fixes);
                    return;
                }

                if (element.getNode().findChildByType(CobolTypes.NEGATIVE) != null) {
                    LocalQuickFix[] fixes = new LocalQuickFix[]{new NegativeToSymbolsFix(element)};
                    suggestChange(element, holder, fixes);
                    return;
                }

                if (element.getNode().findChildByType(CobolTypes.GREATER) != null) {
                    LocalQuickFix[] fixes = new LocalQuickFix[]{new GreaterToSymbolsFix(element)};
                    suggestChange(element, holder, fixes);
                    return;
                }

                if (element.getNode().findChildByType(CobolTypes.LESS) != null) {
                    LocalQuickFix[] fixes = new LocalQuickFix[]{new LessToSymbolsFix(element)};
                    suggestChange(element, holder, fixes);
                    return;
                }

                if (element.getNode().findChildByType(CobolTypes.EQUAL) != null || element.getNode().findChildByType(CobolTypes.EQUALS) != null) {
                    LocalQuickFix[] fixes = new LocalQuickFix[]{new EqualToSymbolsFix(element)};
                    suggestChange(element, holder, fixes);
                    return;
                }
            }
        };
    }

    private void suggestChange(PsiElement add, ProblemsHolder holder, LocalQuickFix[] fixes) {
        holder.registerProblem(add, "Change to comparison with symbols", ProblemHighlightType.WARNING, fixes);
    }
}

class PositiveToSymbolsFix extends ComparisonWithoutSymbolsFix {
    protected PositiveToSymbolsFix(@NotNull PsiElement element) {
        super(element);
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement condition, @NotNull PsiElement endElement) {
        PsiElement statement = CobolElementFactory.createComparison(
            project,
            firstOperand().getText(),
            condition.getNode().findChildByType(CobolTypes.NOT) == null ? ">" : "<=",
            "0"
        );
        condition.getParent().getNode().replaceChild(condition.getNode(), statement.getNode());
    }
}

class NegativeToSymbolsFix extends ComparisonWithoutSymbolsFix {
    protected NegativeToSymbolsFix(@NotNull PsiElement element) {
        super(element);
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement condition, @NotNull PsiElement endElement) {
        PsiElement statement = CobolElementFactory.createComparison(
            project,
            firstOperand().getText(),
            condition.getNode().findChildByType(CobolTypes.NOT) == null ? "<" : ">=",
            "0"
        );

        condition.getParent().getNode().replaceChild(condition.getNode(), statement.getNode());
    }
}

class EqualToSymbolsFix extends ComparisonWithoutSymbolsFix {
    protected EqualToSymbolsFix(@NotNull PsiElement element) {
        super(element);
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement condition, @NotNull PsiElement endElement) {
        PsiElement statement = CobolElementFactory.createComparison(
            project,
            firstOperand().getText(),
            condition.getNode().findChildByType(CobolTypes.NOT) == null ? "=" : "not =",
            secondOperand().getText()
        );

        condition.getParent().getNode().replaceChild(condition.getNode(), statement.getNode());
    }
}

class GreaterToSymbolsFix extends ComparisonWithoutSymbolsFix {
    protected GreaterToSymbolsFix(@NotNull PsiElement element) {
        super(element);
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement condition, @NotNull PsiElement endElement) {
        PsiElement statement = CobolElementFactory.createComparison(
            project,
            firstOperand().getText(),
            condition.getNode().findChildByType(CobolTypes.EQUAL) == null ? ">" : ">=",
            secondOperand().getText()
        );

        condition.getParent().getNode().replaceChild(condition.getNode(), statement.getNode());
    }
}

class LessToSymbolsFix extends ComparisonWithoutSymbolsFix {
    protected LessToSymbolsFix(@NotNull PsiElement element) {
        super(element);
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement condition, @NotNull PsiElement endElement) {
        PsiElement statement = CobolElementFactory.createComparison(
            project,
            firstOperand().getText(),
            condition.getNode().findChildByType(CobolTypes.EQUAL) == null ? "<" : "<=",
            secondOperand().getText()
        );

        condition.getParent().getNode().replaceChild(condition.getNode(), statement.getNode());
    }
}

abstract class ComparisonWithoutSymbolsFix extends LocalQuickFixOnPsiElement {
    protected ComparisonWithoutSymbolsFix(@NotNull PsiElement element) {
        super(element);
    }

    @Override
    public @NotNull String getText() {
        return "Change to comparison with symbols";
    }

    @Override
    public @NotNull String getFamilyName() {
        return getText();
    }

    private Collection<CobolIfConditionOperand> operands() {
        return PsiTreeUtil.findChildrenOfType(this.getStartElement(), CobolIfConditionOperand.class);
    }

    protected CobolIfConditionOperand firstOperand() {
        return Iterables.get(operands(), 0);
    }

    protected CobolIfConditionOperand secondOperand() {
        return Iterables.get(operands(), 1);
    }
}
