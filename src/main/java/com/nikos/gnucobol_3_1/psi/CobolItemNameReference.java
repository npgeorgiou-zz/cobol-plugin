package com.nikos.gnucobol_3_1.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.stream.Collectors;


public class CobolItemNameReference extends PsiReferenceBase<PsiElement> implements PsiReference {


    public CobolItemNameReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        Collection<CobolItemDecl_> vars = PsiTreeUtil.getParentOfType(getElement(), CobolProgram_.class).items();

        vars = vars.stream()
                .filter(declaration -> declaration.name().equals(myElement.getText()))
                .collect(Collectors.toList());

        if (vars.isEmpty()) {
            return null;
        }

        // That can happen if the ws/ls section defines more than one items with the same name.
        // In that case, the Annotator annotates them as an error, but we also don't want to use any
        // of them as a reference resolve target, as in that case that one will be NOT marked as unused.
        // It will just be weird visually that lets say, all 3 are marked as error "duplicate name" and 2 also marked as unused.
        if (vars.stream().count() > 1) {
            return null;
        }

        CobolItemDecl_ itemDecl = vars.stream().findFirst().get();

        if (itemDecl instanceof CobolElementaryItemDecl_) {
            return((CobolElementaryItemDecl_) itemDecl).getItemNameDecl_();
        }

        if (itemDecl instanceof CobolGroupItemDecl_) {
            return((CobolGroupItemDecl_) itemDecl).getItemNameDecl_();
        }

        // Cant happen, element is one of these 2 interfaces.
        return null;
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        // todo: change to myElement.remane, somehow.
        ((CobolItemUsage_) myElement).setName(newName);
        return myElement;
    }
}
