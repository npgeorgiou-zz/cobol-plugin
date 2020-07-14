package com.nikos.gnucobol_3_1.psi.impl;

import com.google.common.collect.Iterables;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.nikos.gnucobol_3_1.CobolUtil;
import com.nikos.gnucobol_3_1.Util;
import com.nikos.gnucobol_3_1.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CobolPsiImplUtil {
    public static String getName(CobolProgramNameDecl_ name) {
        return name.getFirstChild().getText();
    }

    public static PsiElement setName(CobolProgramNameDecl_ name, String newName) {
        PsiElement identifier = CobolElementFactory.createIdentifier(name.getProject(), newName);
        name.getIdentifierOrString_().getFirstChild().replace(identifier);

        return name;
    }

    public static PsiElement getNameIdentifier(CobolProgramNameDecl_ name) {
        return name.getFirstChild();
    }


    public static CobolProgramNameReference getReference(CobolProgramName_ name) {
        TextRange a = name.getNode().findChildByType(CobolTypes.IDENTIFIER_OR_STRING_).getPsi().getTextRangeInParent();
        return new CobolProgramNameReference(name, a);
    }

    public static PsiElement setName(CobolProgramName_ name, String newName) {
        PsiElement identifier = CobolElementFactory.createIdentifier(name.getProject(), newName);
        name.getIdentifierOrString_().getFirstChild().replace(identifier);

        return name;
    }


    public static String getName(CobolItemNameDecl_ name) {
        return name.getFirstChild().getText();
    }

    public static PsiElement setName(CobolItemNameDecl_ name, String newName) {
        PsiElement identifier = CobolElementFactory.createIdentifier(name.getProject(), newName);
        name.getFirstChild().replace(identifier);

        return name;
    }

    public static PsiElement getNameIdentifier(CobolItemNameDecl_ name) {
        return name.getFirstChild();
    }


    public static CobolItemNameReference getReference(CobolItemUsage_ name) {
        TextRange a = name.getFirstChild().getTextRangeInParent();
        return new CobolItemNameReference(name, a);
    }

    public static PsiElement setName(CobolItemUsage_ name, String newName) {
        PsiElement identifier = CobolElementFactory.createIdentifier(name.getProject(), newName);
        name.getFirstChild().replace(identifier);

        return name;
    }

    public static Collection<PsiElement> parentChain(CobolItemUsage_ itemUsage, Collection<PsiElement> chain, boolean recursive) {
        Collection<PsiElement> currentChain = new ArrayList<>();

        PsiElement next = itemUsage.getNextSibling();
        if (next == null) return chain;
        currentChain.add(next);

        next = next.getNextSibling();
        if (next == null) return chain;
        if (next.getNode().getElementType() != CobolTypes.OF && next.getNode().getElementType() != CobolTypes.IN) return chain;
        currentChain.add(next);

        next = next.getNextSibling();
        if (next == null) return chain;
        currentChain.add(next);

        next = next.getNextSibling();
        if (next == null) return chain;
        if (!(next instanceof CobolItemUsage_)) return chain;
        currentChain.add(next);

        chain.addAll(currentChain);

        return recursive ? ((CobolItemUsage_)next).parentChain(chain, true) : chain;
    }

    public static CobolItemUsage_ typedGroupItem(CobolItemUsage_ itemUsage, boolean recursive) {
        Collection elements = itemUsage.parentChain(new ArrayList<>(), recursive);
        if (elements.isEmpty()) return null;
        return (CobolItemUsage_) Iterables.getLast(elements);
    }

    public static String path(CobolCopy_ copy) {
        return CobolUtil.textOfNullable(copy.getNode().findChildByType(CobolTypes.STRING));
    }

    public static CobolFile file(CobolCopy_ copy) {
        // It is valid to copy files as both "foo" and "foo.cob".
        String filePath = copy.path().replace(".cob", "");
        return CobolUtil.cobolFileWithPath(CobolUtil.COPY_PATH + filePath + ".cob", copy.getProject());
    }


    public static boolean isStatic(CobolCall_ call) {
        PsiElement callable = call.getIdentifierOrString_();

        if (callable == null) {
            return false;
        }

        return callable.getFirstChild().getNode().getElementType() == CobolTypes.STRING;
    }

    public static String subprogramName(CobolCall_ call) {
        return Util.unquote(call.getIdentifierOrString_().getFirstChild().getText());
    }


    public static String name(CobolProgram_ program) {
        return CobolUtil.textOfNullable(program.getIdentificationDivision_().getProgramId_().getProgramNameDecl_().getIdentifierOrString_());
    }

    public static Collection<CobolItemDecl_> wsItems(CobolProgram_ program) {
        return Optional.of(program)
            .map(it -> it.getDataDivision_())
            .map(it -> it.getWsSection_())
            .map(it -> it.getItemDecl_List())
            .map(it -> (Collection<CobolItemDecl_>) it)
            .orElseGet(ArrayList::new);
    }

    public static Collection<CobolItemDecl_> lsItems(CobolProgram_ program) {
        return Optional.of(program)
            .map(it -> it.getDataDivision_())
            .map(it -> it.getLsSection_())
            .map(it -> it.getItemDecl_List())
            .map(it -> (Collection<CobolItemDecl_>) it)
            .orElseGet(ArrayList::new);
    }

    public static Collection<CobolItemDecl_> items(CobolProgram_ program) {
        return Stream.concat(
                program.lsItems().stream(),
                program.wsItems().stream()
        ).collect(Collectors.toList());
    }

    public static Collection<CobolGroupItemDecl_> groupItems(CobolProgram_ program) {
        return Stream
                   .concat(program.lsItems().stream(), program.wsItems().stream())
                   .filter(it -> it instanceof CobolGroupItemDecl_)
                   .map(it -> (CobolGroupItemDecl_)it)
                   .collect(Collectors.toList());
    }

    public static Collection<CobolElementaryItemDecl_> elementaryItems(CobolProgram_ program) {
        return Stream
                   .concat(program.lsItems().stream(), program.wsItems().stream())
                   .filter(it -> it instanceof CobolElementaryItemDecl_)
                   .map(it -> (CobolElementaryItemDecl_)it)
                   .collect(Collectors.toList());
    }

    public static Collection<CobolProgram_> subPrograms(CobolProgram_ program) {
        ArrayList<CobolProgram_> acc = new ArrayList<>();
        return subProgramsOf(program, acc);
    }

    private static Collection<CobolProgram_> subProgramsOf(CobolProgram_ program, ArrayList<CobolProgram_> acc) {
        Stream.of(program.getProcedureDivision_().getChildren())
                .filter(psiElement -> psiElement instanceof CobolProgram_)
                .map(psiElement -> (CobolProgram_) psiElement)
                .forEach(subProgram -> {
                    acc.add(subProgram);
                    subProgramsOf(subProgram, acc);
                });

        return acc;
    }

    public static Collection<CobolCall_> calls(CobolProgram_ program) {
        return PsiTreeUtil.findChildrenOfType(program.getProcedureDivision_(), CobolCall_.class);
    }


    public static int level(CobolItemDecl_ itemDecl) {
        return Integer.parseInt(itemDecl.getFirstChild().getText());
    }

    public static String name(CobolItemDecl_ itemDecl) {
        if (itemDecl instanceof CobolElementaryItemDecl_) {
            return((CobolElementaryItemDecl_) itemDecl).getItemNameDecl_().getName();
        }

        if (itemDecl instanceof CobolGroupItemDecl_) {
            return((CobolGroupItemDecl_) itemDecl).getItemNameDecl_().getName();
        }

        if (itemDecl instanceof CobolRenamesItemDecl_) {
            return((CobolRenamesItemDecl_) itemDecl).getItemNameDecl_().getName();
        }

        if (itemDecl instanceof CobolConditionalItemDecl_) {
            return((CobolConditionalItemDecl_) itemDecl).getItemNameDecl_().getName();
        }

        return null;
    }

    public static CobolItemDecl_ parent(CobolItemDecl_ itemDecl) {
        return CobolUtil.previousSibling(
            itemDecl,
            null,
            (element) -> {
                if (!(element instanceof CobolItemDecl_)) return false;
                return ((CobolItemDecl_) element).level() < itemDecl.level();
            }
        );
    }

    public static String type(CobolElementaryItemDecl_ itemDeclaration) {
        ASTNode node = itemDeclaration.getItemTypeDecl_().getNode();

        if (node.findChildByType(CobolTypes.PIC_TYPE_ALPHA) != null) {
            return CobolUtil.ALPHA;
        }

        if (node.findChildByType(CobolTypes.PIC_TYPE_ALPHANUMERIC) != null) {
            return CobolUtil.ALPHANUMERIC;
        }

        if (node.findChildByType(CobolTypes.PIC_TYPE_NUMERIC) != null) {
            return CobolUtil.NUMERIC;
        }

        if (node.findChildByType(CobolTypes.PIC_TYPE_DECIMAL_NUMERIC) != null) {
            return CobolUtil.NUMERIC;
        }

        if (node.findChildByType(CobolTypes.PIC_TYPE_SIGNED_NUMERIC) != null) {
            return CobolUtil.NUMERIC;
        }

        return null;
    }

    public static boolean isSigned(CobolElementaryItemDecl_ itemDeclaration) {
        return itemDeclaration.getItemTypeDecl_().getNode().findChildByType(CobolTypes.PIC_TYPE_SIGNED_NUMERIC) != null;
    }

    public static boolean hasDecimals(CobolElementaryItemDecl_ itemDeclaration) {
        return itemDeclaration.getItemTypeDecl_().getNode().findChildByType(CobolTypes.PIC_TYPE_DECIMAL_NUMERIC) != null;
    }

    public static int length(CobolElementaryItemDecl_ itemDeclaration) {
        String string = itemDeclaration.getItemTypeDecl_().getItemLengthDecl_List().get(0).getText();
        String withoutParentheses = string.substring(1, string.length()-1);

        return Integer.parseInt(withoutParentheses);
    }

    public static int decimalLength(CobolElementaryItemDecl_ itemDeclaration) {
        String string = itemDeclaration.getItemTypeDecl_().getItemLengthDecl_List().get(1).getText();
        String withoutParentheses = string.substring(1, string.length()-1);

        return Integer.parseInt(withoutParentheses);
    }

    public static CobolItemDecl_ redefines(CobolElementaryItemDecl_ itemDeclaration) {
        ASTNode redefines = itemDeclaration.getNode().findChildByType(CobolTypes.REDEFINES);

        if (redefines == null) return null;

        CobolItemUsage_ itemUsage = (CobolItemUsage_) redefines.getPsi().getNextSibling().getNextSibling();
        CobolItemNameDecl_ nameDecl = (CobolItemNameDecl_) itemUsage.getReference().resolve();

        if (nameDecl == null) return null;

        return (CobolItemDecl_) nameDecl.getParent();
    }

    public static List<CobolLiteral_> trueIf(CobolConditionalItemDecl_ itemDeclaration) {
        return itemDeclaration.getLiteral_List();
    }

    public static PsiElement initialValue(CobolElementaryItemDecl_ itemDeclaration) {
        return Optional.of(itemDeclaration)
            .map(it -> it.getItemValueDecl_())
            .map(it -> it.getLastChild())
            .orElse(null);
    }

    public static boolean isAlphabetic(CobolLiteral_ literal) {
        if (literal.isAll()) {
            return CobolUtil.isAlphabetic(literal.getLastChild());
        }

        return CobolUtil.isAlphabetic(literal.getFirstChild());
    }

    public static boolean isAlphaNumeric(CobolLiteral_ literal) {
        if (literal.isAll()) {
            return CobolUtil.isAlphaNumeric(literal.getLastChild());
        }

        return CobolUtil.isAlphaNumeric(literal.getFirstChild());
    }

    public static boolean isInteger(CobolLiteral_ literal) {
        if (literal.isAll()) {
            return CobolUtil.isInteger(literal.getLastChild());
        }

        return CobolUtil.isInteger(literal.getFirstChild());
    }

    public static boolean isFloat(CobolLiteral_ literal) {
        if (literal.isAll()) {
            return CobolUtil.isFloat(literal.getLastChild());
        }

        return CobolUtil.isFloat(literal.getFirstChild());
    }

    public static boolean isFigurativeConstant(CobolLiteral_ literal) {
        return CobolUtil.isFigurativeConstant(literal.getFirstChild());
    }

    public static boolean isAll(CobolLiteral_ literal) {
        IElementType type = literal.getFirstChild().getNode().getElementType();
        return type == CobolTypes.ALL;
    }
}
