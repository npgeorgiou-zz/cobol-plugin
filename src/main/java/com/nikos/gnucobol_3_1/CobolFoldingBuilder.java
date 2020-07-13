package com.nikos.gnucobol_3_1;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.nikos.gnucobol_3_1.psi.CobolFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CobolFoldingBuilder extends FoldingBuilderEx implements DumbAware {

    @Override
    public @NotNull FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        CobolFile file = (CobolFile) root;

        final List<FoldingDescriptor> folds = new ArrayList<>();

        // Comment folding.
        if (!quick) {
            Set<PsiElement> processedComments = new HashSet<>();

            PsiTreeUtil.processElements(file, element -> {
                if (element.getNode().getElementType() == CobolParserDefinition.COMMENT) {
                    PsiElement end = findFoldingEndForComment(element, processedComments);

                    if (end != null) {
                        folds.add(new FoldingDescriptor(
                                element,
                                element.getTextRange().getStartOffset(),
                                end.getTextRange().getEndOffset(),
                                null,
                                (element.getText().length() > 20 ? element.getText().substring(0, 20) : element.getText()).concat("...")
                        ));
                    }
                }

                return true;
            });
        }
        return folds.toArray(new FoldingDescriptor[folds.size()]);
    }

    @Override
    public @Nullable String getPlaceholderText(@NotNull ASTNode node) {
        return null;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }

    private static PsiElement findFoldingEndForComment(@NotNull PsiElement comment, @NotNull Set<PsiElement> processedComments) {
        if (processedComments.contains(comment)) return null;

        PsiElement next = comment.getNextSibling();
        PsiElement end = null;

        while (next != null) {
            IElementType elementType = next.getNode().getElementType();

            if (elementType != CobolParserDefinition.COMMENT && elementType != TokenType.WHITE_SPACE) {
                break;
            }

            if (elementType == CobolParserDefinition.COMMENT) {
                end = next;
                processedComments.add(next);
            }

            next = next.getNextSibling();
        }

        return end;
    }
}
