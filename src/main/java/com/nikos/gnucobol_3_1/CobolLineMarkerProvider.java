package com.nikos.gnucobol_3_1;

import com.intellij.codeInsight.daemon.*;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.*;
import com.nikos.gnucobol_3_1.psi.CobolCopy_;
import com.nikos.gnucobol_3_1.psi.CobolFile;
import com.nikos.gnucobol_3_1.psi.CobolTypes;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CobolLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(
            @NotNull PsiElement element,
            @NotNull Collection<? super RelatedItemLineMarkerInfo> result
    ) {
        copiedFiles(element, result);
    }

    private void copiedFiles(PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
        if (!(element instanceof CobolCopy_)) return;

        CobolFile file = ((CobolCopy_) element).file();

        if (file == null) return;

        RelatedItemLineMarkerInfo<PsiElement> marker = NavigationGutterIconBuilder.create(CobolIcons.FILE)
                .setTarget(file)
                .setTooltipText("Navigate to file")
                .createLineMarkerInfo(element.getNode().findChildByType(CobolTypes.STRING).getPsi());

        result.add(marker);
    }
}