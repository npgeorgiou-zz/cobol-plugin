// This is a generated file. Not intended for manual editing.
package com.nikos.gnucobol_3_1.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.nikos.gnucobol_3_1.psi.CobolTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.nikos.gnucobol_3_1.psi.*;

public class CobolItemNameDecl_Impl extends ASTWrapperPsiElement implements CobolItemNameDecl_ {

  public CobolItemNameDecl_Impl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CobolVisitor visitor) {
    visitor.visitItemNameDecl_(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CobolVisitor) accept((CobolVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public PsiElement setName(String newName) {
    return CobolPsiImplUtil.setName(this, newName);
  }

  @Override
  public String getName() {
    return CobolPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return CobolPsiImplUtil.getNameIdentifier(this);
  }

}
