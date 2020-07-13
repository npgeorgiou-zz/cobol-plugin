package com.nikos.gnucobol_3_1.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.nikos.gnucobol_3_1.CobolUtil;
import com.nikos.gnucobol_3_1.Util;
import com.nikos.gnucobol_3_1.psi.CobolElementaryItemDecl_;
import com.nikos.gnucobol_3_1.psi.CobolLiteral_;
import com.nikos.gnucobol_3_1.psi.CobolVisitor;
import com.sun.istack.NotNull;

public class CobolInitialItemValue extends CobolInspectionBase {

    @NotNull
    protected CobolVisitor buildCobolVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new CobolVisitor() {
            @Override
            public void visitElementaryItemDecl_(@NotNull CobolElementaryItemDecl_ item) {
                typeMismatch(item, holder);
                sizeMismatch(item, holder);
            }
        };
    }

    private void typeMismatch(CobolElementaryItemDecl_ declaration, ProblemsHolder holder) {
        PsiElement value = declaration.initialValue();

        if (!(value instanceof CobolLiteral_)) return;
        CobolLiteral_ literal = (CobolLiteral_) value;

        boolean definedAsAlpha = declaration.type().equals(CobolUtil.ALPHA);
        boolean definedAsAlphaNumeric = declaration.type().equals(CobolUtil.ALPHANUMERIC);
        boolean definedAsDecimalNumeric = declaration.type().equals(CobolUtil.NUMERIC) && declaration.hasDecimals();
        boolean definedAsNonDecimalNumeric = !definedAsDecimalNumeric && declaration.type().equals(CobolUtil.NUMERIC);

        if (definedAsAlpha && !literal.isAlphabetic()) {
            holder.registerProblem(declaration.getItemValueDecl_(), "Value is not alphabetic", ProblemHighlightType.WARNING);
        }

        if (definedAsAlphaNumeric && !literal.isAlphaNumeric()) {
            holder.registerProblem(declaration.getItemValueDecl_(), "Value is not alphanumeric", ProblemHighlightType.WARNING);
        }

        if (definedAsNonDecimalNumeric && !literal.isInteger()) {
            holder.registerProblem(declaration.getItemValueDecl_(), "Value is not integer", ProblemHighlightType.WARNING);
        }

        if (definedAsDecimalNumeric && !literal.isFloat()) {
            holder.registerProblem(declaration.getItemValueDecl_(), "Value is not float", ProblemHighlightType.WARNING);
        }
    }

    private void sizeMismatch(CobolElementaryItemDecl_ declaration, ProblemsHolder holder) {
        PsiElement value = declaration.initialValue();

        if (!(value instanceof CobolLiteral_)) return;
        CobolLiteral_ literal = (CobolLiteral_) value;

        if (literal.isFigurativeConstant() && !literal.isAll()) return;

        if (literal.isAll()) {
            if (CobolUtil.isFigurativeConstant(literal.getLastChild())) return;
        }

        String valueString = literal.isAll() ? literal.getLastChild().getText() : literal.getText();
        valueString = Util.unquote(valueString);

        String error = "Value size exceeds data size.";

        if (declaration.type().equals(CobolUtil.NUMERIC) && declaration.hasDecimals()) {
            String[] parts = valueString.split("\\.");

            if (parts.length == 2) {
                String decimalPart = parts[1];

                if (!isDecimalLengthOK(decimalPart, declaration.decimalLength())) {
                    holder.registerProblem(declaration.getItemValueDecl_(), error, ProblemHighlightType.WARNING);
                }
            }

            if (!isIntLengthOK(parts[0], declaration.length())) {
                holder.registerProblem(declaration.getItemValueDecl_(), error, ProblemHighlightType.WARNING);
            }

            return;
        }

        if (declaration.type().equals(CobolUtil.NUMERIC)) {
            if (!isIntLengthOK(valueString, declaration.length())) {
                holder.registerProblem(declaration.getItemValueDecl_(), error, ProblemHighlightType.WARNING);
            }
            return;
        }

        if (declaration.type().equals(CobolUtil.ALPHA) || declaration.type().equals(CobolUtil.ALPHANUMERIC)) {
            if (valueString.length() > declaration.length()) {
                holder.registerProblem(declaration.getItemValueDecl_(), error, ProblemHighlightType.WARNING);
            }
        }
    }

    private boolean isIntLengthOK(String value, int declaredLength) {
        if (value.length() > declaredLength) {

            String excess = Util.startOverflow(value, declaredLength);
            boolean excessIsZeros = excess.matches("0*");

            if (!excessIsZeros) {
                return false;
            }
        }

        return true;
    }

    private boolean isDecimalLengthOK(String value, int declaredLength) {
        if (value.length() > declaredLength) {

            String excess = Util.endOverflow(value, declaredLength);

            boolean excessIsZeros = excess.matches("0*");

            if (!excessIsZeros) {
                return false;
            }
        }

        return true;
    }
}
