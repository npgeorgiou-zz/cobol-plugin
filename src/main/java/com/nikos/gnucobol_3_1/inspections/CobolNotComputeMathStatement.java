package com.nikos.gnucobol_3_1.inspections;

import com.google.common.collect.Iterables;
import com.intellij.codeInspection.*;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.nikos.gnucobol_3_1.CobolUtil;
import com.nikos.gnucobol_3_1.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CobolNotComputeMathStatement extends CobolInspectionBase {

    @NotNull
    protected CobolVisitor buildCobolVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new CobolVisitor() {
            @Override
            public void visitAdd_(@NotNull CobolAdd_ element) {
                if (element.getNode().findChildByType(CobolTypes.CORRESPONDING) == null && element.getNode().findChildByType(CobolTypes.CORR) == null) {
                    LocalQuickFix[] fixes = new LocalQuickFix[]{new AddToComputeFix(element)};
                    suggestCompute(element, holder, fixes);
                }
            }

            @Override
            public void visitSubtract_(@NotNull CobolSubtract_ element) {
                if (element.getNode().findChildByType(CobolTypes.CORRESPONDING) == null && element.getNode().findChildByType(CobolTypes.CORR) == null) {
                    LocalQuickFix[] fixes = new LocalQuickFix[]{new SubtractToComputeFix(element)};
                    suggestCompute(element, holder, fixes);
                }
            }

            @Override
            public void visitMultiply_(@NotNull CobolMultiply_ element) {
                LocalQuickFix[] fixes = new LocalQuickFix[]{new MultiplyToComputeFix(element)};
                suggestCompute(element, holder, fixes);
            }

            @Override
            public void visitDivide_(@NotNull CobolDivide_ element) {
                // Not supporting remainder for now.
                if (element.getNode().findChildByType(CobolTypes.REMAINDER) == null) {
                    LocalQuickFix[] fixes = new LocalQuickFix[]{new DivideToComputeFix(element)};
                    suggestCompute(element, holder, fixes);
                }
            }
        };
    }

    private void suggestCompute(PsiElement add, ProblemsHolder holder, LocalQuickFix[] fixes) {
        holder.registerProblem(add, "Change to compute", ProblemHighlightType.WARNING, fixes);
    }

    public static Map<PsiElement, ArrayList<PsiElement>> mapElementsToGroups(PsiElement statement) {
        PsiElement next = statement.getFirstChild();
        Map<PsiElement, ArrayList<PsiElement>> map = new LinkedHashMap<>();

        while ((next = next.getNextSibling()) != null) {
            ArrayList<PsiElement> groups = new ArrayList<>();

            if (!(next instanceof CobolItemUsage_)) {
                groups.add(next);
                map.put(next, groups);
                continue;
            }

            CobolItemUsage_ itemUsage = (CobolItemUsage_) next;
            PsiElement groupItemUsage = itemUsage.typedGroupItem(true);

            if (groupItemUsage != null) {
                Collection<PsiElement> parentChain = CobolUtil.getSiblingsBetween(next, groupItemUsage);
                groups.addAll(parentChain);
                next = Iterables.getLast(parentChain);
            } else {
                groups.add(next);
            }

            map.put(next, groups);
            continue;
        }

        return map;
    }
}

class AddToComputeFix extends LocalQuickFixOnPsiElement {
    protected AddToComputeFix(@NotNull PsiElement element) {
        super(element);
    }

    @Override
    public @NotNull String getText() {
        return "Change to compute";
    }

    @Override
    public @NotNull String getFamilyName() {
        return getText();
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement addStatement, @NotNull PsiElement endElement) {
        Map<PsiElement, ArrayList<PsiElement>> groups = CobolNotComputeMathStatement.mapElementsToGroups(addStatement);

        ArrayList<ArrayList<PsiElement>> summands = new ArrayList<>();
        ArrayList<ArrayList<PsiElement>> sums = new ArrayList<>();
        ArrayList<ArrayList<PsiElement>> bothSummandsAndSums = new ArrayList<>();

        boolean foundTo = false;
        boolean foundGiving = false;

        for (Map.Entry<PsiElement, ArrayList<PsiElement>> entry : groups.entrySet()) {
            PsiElement element = entry.getKey();
            ArrayList<PsiElement> group = entry.getValue();

            IElementType type = element.getNode().getElementType();

            if (type == CobolTypes.TO) {
                foundTo = true;
                continue;
            }

            if (type == CobolTypes.GIVING) {
                foundGiving = true;
                continue;
            }

            if (!(element instanceof CobolItemUsage_) && type != CobolTypes.INTEGER && type != CobolTypes.FLOAT)
                continue;

            if (!foundTo && !foundGiving) {
                summands.add(group);
                continue;
            }

            if (foundTo && foundGiving) {
                summands.addAll(bothSummandsAndSums);
                bothSummandsAndSums.clear();
                sums.add(group);
                continue;
            }

            if (foundTo) {
                bothSummandsAndSums.add(group);
                continue;
            }

            if (foundGiving) {
                sums.add(group);
                continue;
            }
        }

        // add foo1, foo1 to foo1, foo2 -> foo1 = foo1 + foo1 + foo1, foo2 = foo1 + foo1 + foo2
        if (bothSummandsAndSums.size() > 0) {
            sums.addAll(bothSummandsAndSums);

            ArrayList<PsiElement> computes = new ArrayList<>();

            for (ArrayList<PsiElement> destination : sums) {
                CobolCompute_ compute = CobolElementFactory.createComputeAddStatement(
                    project,
                    new ArrayList<ArrayList<PsiElement>>(summands) {{
                        add(0, destination);
                    }},
                    new ArrayList<ArrayList<PsiElement>>() {{
                        add(destination);
                    }}
                );

                computes.add(compute);
            }

            CobolUtil.replaceElementWithElements(addStatement, computes);
        } else {
            PsiElement statement = CobolElementFactory.createComputeAddStatement(project, summands, sums);
            addStatement.getParent().getNode().replaceChild(addStatement.getNode(), statement.getNode());
        }
    }
}

class SubtractToComputeFix extends LocalQuickFixOnPsiElement {
    protected SubtractToComputeFix(@NotNull PsiElement element) {
        super(element);
    }

    @Override
    public @NotNull String getText() {
        return "Change to compute";
    }

    @Override
    public @NotNull String getFamilyName() {
        return getText();
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement addStatement, @NotNull PsiElement endElement) {
        Map<PsiElement, ArrayList<PsiElement>> groups = CobolNotComputeMathStatement.mapElementsToGroups(addStatement);

        // Find addition operands
        ArrayList<ArrayList<PsiElement>> minuends = new ArrayList<>();
        ArrayList<ArrayList<PsiElement>> destinations = new ArrayList<>();
        ArrayList<ArrayList<PsiElement>> bothMinuendsAndDestinations = new ArrayList<>();

        boolean foundFrom = false;
        boolean foundGiving = false;

        for (Map.Entry<PsiElement, ArrayList<PsiElement>> entry : groups.entrySet()) {
            PsiElement element = entry.getKey();
            ArrayList<PsiElement> group = entry.getValue();

            IElementType type = element.getNode().getElementType();

            if (type == CobolTypes.FROM) {
                foundFrom = true;
                continue;
            }

            if (type == CobolTypes.GIVING) {
                foundGiving = true;
                continue;
            }

            if (!(element instanceof CobolItemUsage_) && type != CobolTypes.INTEGER && type != CobolTypes.FLOAT)
                continue;

            if (!foundFrom && !foundGiving) {
                minuends.add(group);
                continue;
            }

            if (foundFrom && foundGiving) {
                minuends.addAll(bothMinuendsAndDestinations);
                bothMinuendsAndDestinations.clear();
                destinations.add(group);
                continue;
            }

            if (foundFrom) {
                bothMinuendsAndDestinations.add(group);
                continue;
            }
        }

        // subtract 1, 2 from foo1, foo2 -> foo1 = 1 - 2 - foo1, foo2 = 1 - 2 - foo2
        if (bothMinuendsAndDestinations.size() > 0) {
            destinations.addAll(bothMinuendsAndDestinations);

            ArrayList<PsiElement> computes = new ArrayList<>();

            for (ArrayList<PsiElement> destination : destinations) {
                CobolCompute_ compute = CobolElementFactory.createComputeSubtractStatement(
                    project,
                    new ArrayList<ArrayList<PsiElement>>(minuends) {{
                        add(0, destination);
                    }},
                    new ArrayList<ArrayList<PsiElement>>() {{
                        add(destination);
                    }}
                );

                computes.add(compute);
            }

            CobolUtil.replaceElementWithElements(addStatement, computes);
        } else {
            PsiElement statement = CobolElementFactory.createComputeSubtractStatement(project, minuends, destinations);
            addStatement.getParent().getNode().replaceChild(addStatement.getNode(), statement.getNode());
        }
    }
}

class MultiplyToComputeFix extends LocalQuickFixOnPsiElement {
    protected MultiplyToComputeFix(@NotNull PsiElement element) {
        super(element);
    }

    @Override
    public @NotNull String getText() {
        return "Change to compute";
    }

    @Override
    public @NotNull String getFamilyName() {
        return getText();
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement addStatement, @NotNull PsiElement endElement) {
        Map<PsiElement, ArrayList<PsiElement>> groups = CobolNotComputeMathStatement.mapElementsToGroups(addStatement);

        // Find addition operands
        ArrayList<ArrayList<PsiElement>> factors = new ArrayList<>();
        ArrayList<ArrayList<PsiElement>> destinations = new ArrayList<>();
        ArrayList<ArrayList<PsiElement>> bothFactorsAndDestinations = new ArrayList<>();

        boolean foundBy = false;
        boolean foundGiving = false;

        for (Map.Entry<PsiElement, ArrayList<PsiElement>> entry : groups.entrySet()) {
            PsiElement element = entry.getKey();
            ArrayList<PsiElement> group = entry.getValue();

            IElementType type = element.getNode().getElementType();

            if (type == CobolTypes.BY) {
                foundBy = true;
                continue;
            }

            if (type == CobolTypes.GIVING) {
                foundGiving = true;
                continue;
            }

            if (!(element instanceof CobolItemUsage_) && type != CobolTypes.INTEGER && type != CobolTypes.FLOAT)
                continue;

            if (!foundBy && !foundGiving) {
                factors.add(group);
                continue;
            }

            if (foundBy && foundGiving) {
                factors.addAll(bothFactorsAndDestinations);
                bothFactorsAndDestinations.clear();
                destinations.add(group);
                continue;
            }

            if (foundBy) {
                bothFactorsAndDestinations.add(group);
                continue;
            }
        }

        // subtract 1, 2 from foo1, foo2 -> foo1 = 1 - 2 - foo1, foo2 = 1 - 2 - foo2
        if (bothFactorsAndDestinations.size() > 0) {
            destinations.addAll(bothFactorsAndDestinations);

            ArrayList<PsiElement> computes = new ArrayList<>();

            for (ArrayList<PsiElement> destination : destinations) {
                CobolCompute_ compute = CobolElementFactory.createComputeMultiplyStatement(
                    project,
                    new ArrayList<ArrayList<PsiElement>>(factors) {{
                        add(0, destination);
                    }},
                    new ArrayList<ArrayList<PsiElement>>() {{
                        add(destination);
                    }}
                );

                computes.add(compute);
            }

            CobolUtil.replaceElementWithElements(addStatement, computes);
        } else {
            PsiElement statement = CobolElementFactory.createComputeMultiplyStatement(project, factors, destinations);
            addStatement.getParent().getNode().replaceChild(addStatement.getNode(), statement.getNode());
        }
    }
}

class DivideToComputeFix extends LocalQuickFixOnPsiElement {
    protected DivideToComputeFix(@NotNull PsiElement element) {
        super(element);
    }

    @Override
    public @NotNull String getText() {
        return "Change to compute";
    }

    @Override
    public @NotNull String getFamilyName() {
        return getText();
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @NotNull PsiElement addStatement, @NotNull PsiElement endElement) {
        Map<PsiElement, ArrayList<PsiElement>> groups = CobolNotComputeMathStatement.mapElementsToGroups(addStatement);

        // Find addition operands
        ArrayList<PsiElement> divisor = new ArrayList<>();
        ArrayList<PsiElement> divident = new ArrayList<>();
        ArrayList<ArrayList<PsiElement>> destinations = new ArrayList<>();
        ArrayList<ArrayList<PsiElement>> both = new ArrayList<>();

        boolean foundInto = false;
        boolean foundBy = false;
        boolean foundGiving = false;

        for (Map.Entry<PsiElement, ArrayList<PsiElement>> entry : groups.entrySet()) {
            // Account for the 2 different forms of divide, into and by.
            if (addStatement.getNode().findChildByType(CobolTypes.INTO) != null) {
                PsiElement element = entry.getKey();
                ArrayList<PsiElement> group = entry.getValue();

                IElementType type = element.getNode().getElementType();

                if (type == CobolTypes.INTO) {
                    foundInto = true;
                    continue;
                }

                if (type == CobolTypes.GIVING) {
                    foundGiving = true;
                    continue;
                }

                if (!(element instanceof CobolItemUsage_) && type != CobolTypes.INTEGER && type != CobolTypes.FLOAT)
                    continue;

                if (!foundInto && !foundGiving) {
                    divisor.addAll(group);
                    continue;
                }

                if (foundInto && foundGiving) {
                    if (both.size() > 0) {
                        divident = both.get(0);
                        both.clear();
                    }

                    destinations.add(group);
                    continue;
                }

                if (foundInto) {
                    both.add(group);
                    continue;
                }
            } else {
                PsiElement element = entry.getKey();
                ArrayList<PsiElement> group = entry.getValue();

                IElementType type = element.getNode().getElementType();

                if (type == CobolTypes.BY) {
                    foundBy = true;
                    continue;
                }

                if (type == CobolTypes.GIVING) {
                    foundGiving = true;
                    continue;
                }

                if (!(element instanceof CobolItemUsage_) && type != CobolTypes.INTEGER && type != CobolTypes.FLOAT)
                    continue;

                if (!foundBy && !foundGiving) {
                    divident.addAll(group);
                    continue;
                }

                if (foundBy && foundGiving) {
                    destinations.add(group);
                    continue;
                }

                if (foundBy) {
                    divisor.addAll(group);
                    continue;
                }
            }
        }

        // subtract 1, 2 from foo1, foo2 -> foo1 = 1 - 2 - foo1, foo2 = 1 - 2 - foo2
        if (both.size() > 0) {
            destinations.addAll(both);

            ArrayList<PsiElement> computes = new ArrayList<>();

            for (ArrayList<PsiElement> destination : destinations) {
                CobolCompute_ compute = CobolElementFactory.createComputeDivideStatement(
                    project,
                    destination,                                                        // divident
                    divisor,                                                            // divisor
                    new ArrayList<ArrayList<PsiElement>>() {{
                        add(destination);
                    }}         // destination
                );

                computes.add(compute);
            }

            CobolUtil.replaceElementWithElements(addStatement, computes);
        } else {
            PsiElement statement = CobolElementFactory.createComputeDivideStatement(project, divident, divisor, destinations);
            addStatement.getParent().getNode().replaceChild(addStatement.getNode(), statement.getNode());
        }
    }
}
