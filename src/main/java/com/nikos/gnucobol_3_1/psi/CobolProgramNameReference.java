package com.nikos.gnucobol_3_1.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class CobolProgramNameReference extends PsiReferenceBase<PsiElement> implements PsiReference {


    public CobolProgramNameReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        CobolProgram_ p = PsiTreeUtil.getParentOfType(getElement(), CobolProgram_.class);


        return p.getIdentificationDivision_().getProgramId_().getProgramNameDecl_();
    }


    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        // todo: change to myElement.remane, somehow.
        ((CobolProgramName_) myElement).setName(newName);
        return myElement;
    }

    // TODO: Can also use that for populating the autocompletion list. Maybe will be useful at some point.
//    @Override
//    public @NotNull Object[] getVariants() {
//        return new  String[]{"a", "b", "c"};
//    }
}
