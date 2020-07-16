package com.nikos.gnucobol_3_1.inspections;

import com.intellij.codeInspection.*;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import com.nikos.gnucobol_3_1.CobolUtil;
import com.nikos.gnucobol_3_1.Util;
import com.nikos.gnucobol_3_1.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class CobolNonSymbolConditions extends CobolInspectionBase {

    @NotNull
    protected CobolVisitor buildCobolVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new CobolVisitor() {
            @Override
            public void visitCondition_(@NotNull CobolCondition_ element) {
                if (element.getNode().findChildByType(CobolTypes.POSITIVE) != null) {
                    LocalQuickFix[] fixes = new LocalQuickFix[]{new PositiveToSymbolsFix(element)};
                    suggestChange(element, holder, fixes);
                }

                if (element.getNode().findChildByType(CobolTypes.NEGATIVE) != null) {
                    LocalQuickFix[] fixes = new LocalQuickFix[]{new NegativeToSymbolsFix(element)};
                    suggestChange(element, holder, fixes);
                }

                if (element.getNode().findChildByType(CobolTypes.EQUAL) != null) {
                    LocalQuickFix[] fixes = new LocalQuickFix[]{new EqualToSymbolsFix(element)};
                    suggestChange(element, holder, fixes);
                }
            }
        };
    }

    private void suggestChange(PsiElement add, ProblemsHolder holder, LocalQuickFix[] fixes) {
        holder.registerProblem(add, "Change to comparison with symbols", ProblemHighlightType.WARNING, fixes);
    }
}

class PositiveToSymbolsFix extends LocalQuickFixOnPsiElement {
    protected PositiveToSymbolsFix(@NotNull PsiElement element) {
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

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement condition, @NotNull PsiElement endElement) {
        Collection<PsiElement> all = CobolUtil.getSiblingsBetween(condition.getFirstChild(), condition.getNode().findChildByType(CobolTypes.POSITIVE).getPsi());
        all = all.stream()
                  .filter(it -> !(it instanceof PsiWhiteSpace))
                  .filter(it -> it.getNode().getElementType() != CobolTypes.POSITIVE)
                  .filter(it -> it.getNode().getElementType() != CobolTypes.IS)
                  .filter(it -> it.getNode().getElementType() != CobolTypes.NOT)
                  .collect(Collectors.toList());

        List<String> firstOperand = all.stream().map(it -> it.getText()).collect(Collectors.toList());

        System.out.println("#" + Util.implode(firstOperand, " ") + "#");

        String operator = ">";
        if (condition.getNode().findChildByType(CobolTypes.NOT) != null) {
            operator = "<=";
        }

        PsiElement statement = CobolElementFactory.createComparison(project, Util.implode(firstOperand, " "), operator, "0");
        condition.getParent().getNode().replaceChild(condition.getNode(), statement.getNode());
    }
}

class NegativeToSymbolsFix extends LocalQuickFixOnPsiElement {
    protected NegativeToSymbolsFix(@NotNull PsiElement element) {
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

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement condition, @NotNull PsiElement endElement) {
        String firstOperand = condition.getFirstChild().getText();

        String operator = "<";
        if (condition.getNode().findChildByType(CobolTypes.NOT) != null) {
            operator = ">=";
        }

        PsiElement statement = CobolElementFactory.createComparison(project, firstOperand, operator, "0");
        condition.getParent().getNode().replaceChild(condition.getNode(), statement.getNode());
    }
}

class EqualToSymbolsFix extends LocalQuickFixOnPsiElement {
    protected EqualToSymbolsFix(@NotNull PsiElement element) {
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

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement condition, @NotNull PsiElement endElement) {
        String firstOperand = condition.getFirstChild().getText();

        String operator = "=";
        if (condition.getNode().findChildByType(CobolTypes.NOT) != null) {
            operator = "not =";
        }

        PsiElement statement = CobolElementFactory.createComparison(project, firstOperand, operator, "0");
        condition.getParent().getNode().replaceChild(condition.getNode(), statement.getNode());
    }
}

//class SubtractToComputeFix extends LocalQuickFixOnPsiElement {
//    protected SubtractToComputeFix(@NotNull PsiElement element) {
//        super(element);
//    }
//
//    @Override
//    public @NotNull String getText() {
//        return "Change to compute";
//    }
//
//    @Override
//    public @NotNull String getFamilyName() {
//        return getText();
//    }
//
//    @Override
//    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement addStatement, @NotNull PsiElement endElement) {
//        Map<PsiElement, ArrayList<PsiElement>> groups = CobolNotComputeMathStatement.mapElementsToGroups(addStatement);
//
//        // Find addition operands
//        ArrayList<ArrayList<PsiElement>> minuends = new ArrayList<>();
//        ArrayList<ArrayList<PsiElement>> destinations = new ArrayList<>();
//        ArrayList<ArrayList<PsiElement>> bothMinuendsAndDestinations = new ArrayList<>();
//
//        boolean foundFrom = false;
//        boolean foundGiving = false;
//
//        for (Map.Entry<PsiElement, ArrayList<PsiElement>> entry : groups.entrySet()) {
//            PsiElement element = entry.getKey();
//            ArrayList<PsiElement> group = entry.getValue();
//
//            IElementType type = element.getNode().getElementType();
//
//            if (type == CobolTypes.FROM) {
//                foundFrom = true;
//                continue;
//            }
//
//            if (type == CobolTypes.GIVING) {
//                foundGiving = true;
//                continue;
//            }
//
//            if (!(element instanceof CobolItemUsage_) && type != CobolTypes.INTEGER && type != CobolTypes.FLOAT)
//                continue;
//
//            if (!foundFrom && !foundGiving) {
//                minuends.add(group);
//                continue;
//            }
//
//            if (foundFrom && foundGiving) {
//                minuends.addAll(bothMinuendsAndDestinations);
//                bothMinuendsAndDestinations.clear();
//                destinations.add(group);
//                continue;
//            }
//
//            if (foundFrom) {
//                bothMinuendsAndDestinations.add(group);
//                continue;
//            }
//        }
//
//        // subtract 1, 2 from foo1, foo2 -> foo1 = 1 - 2 - foo1, foo2 = 1 - 2 - foo2
//        if (bothMinuendsAndDestinations.size() > 0) {
//            destinations.addAll(bothMinuendsAndDestinations);
//
//            ArrayList<PsiElement> computes = new ArrayList<>();
//
//            for (ArrayList<PsiElement> destination : destinations) {
//                CobolCompute_ compute = CobolElementFactory.createComputeSubtractStatement(
//                    project,
//                    new ArrayList<ArrayList<PsiElement>>(minuends) {{
//                        add(0, destination);
//                    }},
//                    new ArrayList<ArrayList<PsiElement>>() {{
//                        add(destination);
//                    }}
//                );
//
//                computes.add(compute);
//            }
//
//            CobolUtil.replaceElementWithElements(addStatement, computes);
//        } else {
//            PsiElement statement = CobolElementFactory.createComputeSubtractStatement(project, minuends, destinations);
//            addStatement.getParent().getNode().replaceChild(addStatement.getNode(), statement.getNode());
//        }
//    }
//}
//
//class MultiplyToComputeFix extends LocalQuickFixOnPsiElement {
//    protected MultiplyToComputeFix(@NotNull PsiElement element) {
//        super(element);
//    }
//
//    @Override
//    public @NotNull String getText() {
//        return "Change to compute";
//    }
//
//    @Override
//    public @NotNull String getFamilyName() {
//        return getText();
//    }
//
//    @Override
//    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement addStatement, @NotNull PsiElement endElement) {
//        Map<PsiElement, ArrayList<PsiElement>> groups = CobolNotComputeMathStatement.mapElementsToGroups(addStatement);
//
//        // Find addition operands
//        ArrayList<ArrayList<PsiElement>> factors = new ArrayList<>();
//        ArrayList<ArrayList<PsiElement>> destinations = new ArrayList<>();
//        ArrayList<ArrayList<PsiElement>> bothFactorsAndDestinations = new ArrayList<>();
//
//        boolean foundBy = false;
//        boolean foundGiving = false;
//
//        for (Map.Entry<PsiElement, ArrayList<PsiElement>> entry : groups.entrySet()) {
//            PsiElement element = entry.getKey();
//            ArrayList<PsiElement> group = entry.getValue();
//
//            IElementType type = element.getNode().getElementType();
//
//            if (type == CobolTypes.BY) {
//                foundBy = true;
//                continue;
//            }
//
//            if (type == CobolTypes.GIVING) {
//                foundGiving = true;
//                continue;
//            }
//
//            if (!(element instanceof CobolItemUsage_) && type != CobolTypes.INTEGER && type != CobolTypes.FLOAT)
//                continue;
//
//            if (!foundBy && !foundGiving) {
//                factors.add(group);
//                continue;
//            }
//
//            if (foundBy && foundGiving) {
//                factors.addAll(bothFactorsAndDestinations);
//                bothFactorsAndDestinations.clear();
//                destinations.add(group);
//                continue;
//            }
//
//            if (foundBy) {
//                bothFactorsAndDestinations.add(group);
//                continue;
//            }
//        }
//
//        // subtract 1, 2 from foo1, foo2 -> foo1 = 1 - 2 - foo1, foo2 = 1 - 2 - foo2
//        if (bothFactorsAndDestinations.size() > 0) {
//            destinations.addAll(bothFactorsAndDestinations);
//
//            ArrayList<PsiElement> computes = new ArrayList<>();
//
//            for (ArrayList<PsiElement> destination : destinations) {
//                CobolCompute_ compute = CobolElementFactory.createComputeMultiplyStatement(
//                    project,
//                    new ArrayList<ArrayList<PsiElement>>(factors) {{
//                        add(0, destination);
//                    }},
//                    new ArrayList<ArrayList<PsiElement>>() {{
//                        add(destination);
//                    }}
//                );
//
//                computes.add(compute);
//            }
//
//            CobolUtil.replaceElementWithElements(addStatement, computes);
//        } else {
//            PsiElement statement = CobolElementFactory.createComputeMultiplyStatement(project, factors, destinations);
//            addStatement.getParent().getNode().replaceChild(addStatement.getNode(), statement.getNode());
//        }
//    }
//}
//
//class DivideToComputeFix extends LocalQuickFixOnPsiElement {
//    protected DivideToComputeFix(@NotNull PsiElement element) {
//        super(element);
//    }
//
//    @Override
//    public @NotNull String getText() {
//        return "Change to compute";
//    }
//
//    @Override
//    public @NotNull String getFamilyName() {
//        return getText();
//    }
//
//    @Override
//    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement addStatement, @NotNull PsiElement endElement) {
//        Map<PsiElement, ArrayList<PsiElement>> groups = CobolNotComputeMathStatement.mapElementsToGroups(addStatement);
//
//        // Find addition operands
//        ArrayList<PsiElement> divisor = new ArrayList<>();
//        ArrayList<PsiElement> divident = new ArrayList<>();
//        ArrayList<ArrayList<PsiElement>> destinations = new ArrayList<>();
//        ArrayList<ArrayList<PsiElement>> both = new ArrayList<>();
//
//        boolean foundInto = false;
//        boolean foundBy = false;
//        boolean foundGiving = false;
//
//        for (Map.Entry<PsiElement, ArrayList<PsiElement>> entry : groups.entrySet()) {
//            // Account for the 2 different forms of divide, into and by.
//            if (addStatement.getNode().findChildByType(CobolTypes.INTO) != null) {
//                PsiElement element = entry.getKey();
//                ArrayList<PsiElement> group = entry.getValue();
//
//                IElementType type = element.getNode().getElementType();
//
//                if (type == CobolTypes.INTO) {
//                    foundInto = true;
//                    continue;
//                }
//
//                if (type == CobolTypes.GIVING) {
//                    foundGiving = true;
//                    continue;
//                }
//
//                if (!(element instanceof CobolItemUsage_) && type != CobolTypes.INTEGER && type != CobolTypes.FLOAT)
//                    continue;
//
//                if (!foundInto && !foundGiving) {
//                    divisor.addAll(group);
//                    continue;
//                }
//
//                if (foundInto && foundGiving) {
//                    if (both.size() > 0) {
//                        divident = both.get(0);
//                        both.clear();
//                    }
//
//                    destinations.add(group);
//                    continue;
//                }
//
//                if (foundInto) {
//                    both.add(group);
//                    continue;
//                }
//            } else {
//                PsiElement element = entry.getKey();
//                ArrayList<PsiElement> group = entry.getValue();
//
//                IElementType type = element.getNode().getElementType();
//
//                if (type == CobolTypes.BY) {
//                    foundBy = true;
//                    continue;
//                }
//
//                if (type == CobolTypes.GIVING) {
//                    foundGiving = true;
//                    continue;
//                }
//
//                if (!(element instanceof CobolItemUsage_) && type != CobolTypes.INTEGER && type != CobolTypes.FLOAT)
//                    continue;
//
//                if (!foundBy && !foundGiving) {
//                    divident.addAll(group);
//                    continue;
//                }
//
//                if (foundBy && foundGiving) {
//                    destinations.add(group);
//                    continue;
//                }
//
//                if (foundBy) {
//                    divisor.addAll(group);
//                    continue;
//                }
//            }
//        }
//
//        // subtract 1, 2 from foo1, foo2 -> foo1 = 1 - 2 - foo1, foo2 = 1 - 2 - foo2
//        if (both.size() > 0) {
//            destinations.addAll(both);
//
//            ArrayList<PsiElement> computes = new ArrayList<>();
//
//            for (ArrayList<PsiElement> destination : destinations) {
//                CobolCompute_ compute = CobolElementFactory.createComputeDivideStatement(
//                    project,
//                    destination,                                                        // divident
//                    divisor,                                                            // divisor
//                    new ArrayList<ArrayList<PsiElement>>() {{
//                        add(destination);
//                    }}         // destination
//                );
//
//                computes.add(compute);
//            }
//
//            CobolUtil.replaceElementWithElements(addStatement, computes);
//        } else {
//            PsiElement statement = CobolElementFactory.createComputeDivideStatement(project, divident, divisor, destinations);
//            addStatement.getParent().getNode().replaceChild(addStatement.getNode(), statement.getNode());
//        }
//    }
//}
