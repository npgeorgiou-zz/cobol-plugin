package com.nikos.gnucobol_3_1.psi.impl;


import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.nikos.gnucobol_3_1.psi.CobolNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class CobolNamedElementImpl extends ASTWrapperPsiElement implements CobolNamedElement {
    public CobolNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}