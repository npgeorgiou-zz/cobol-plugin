package com.nikos.gnucobol_3_1.colors;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.nikos.gnucobol_3_1.psi.CobolCall_;
import com.nikos.gnucobol_3_1.psi.CobolProgram_;
import com.nikos.gnucobol_3_1.psi.CobolTypes;
import org.jetbrains.annotations.NotNull;


public class CobolHighlightingAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof CobolCall_) {
            CobolCall_ call = (CobolCall_) element;
            PsiElement string = call.getIdentifierOrString_().getFirstChild();

            color(string, CobolSyntaxHighlighter.PROGRAM_CALL_STYLE, holder);
        }

        if (element instanceof CobolProgram_) {
            CobolProgram_ program = (CobolProgram_) element;
            PsiElement startIdentifier = program
                                             .getIdentificationDivision_()
                                             .getProgramId_()
                                             .getProgramNameDecl_();

            color(startIdentifier, CobolSyntaxHighlighter.PROGRAM_DECLARATION_STYLE, holder);

            PsiElement endIdentifier = program.getEndProgram_().getNode().findChildByType(CobolTypes.PROGRAM_NAME_).getPsi();
            color(endIdentifier, CobolSyntaxHighlighter.PROGRAM_END_STYLE, holder);
        }
    }

    private void color(PsiElement element, TextAttributesKey style, AnnotationHolder holder) {
        Annotation a = holder.createInfoAnnotation(element, null);
        a.setTextAttributes(style);

    }
}
