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
import java.util.Collection;

public class CobolItemUsage_Impl extends ASTWrapperPsiElement implements CobolItemUsage_ {

  public CobolItemUsage_Impl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CobolVisitor visitor) {
    visitor.visitItemUsage_(this);
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
  public CobolItemNameReference getReference() {
    return CobolPsiImplUtil.getReference(this);
  }

  @Override
  public CobolItemUsage_ typedGroupItem(boolean recursive) {
    return CobolPsiImplUtil.typedGroupItem(this, recursive);
  }

  @Override
  public Collection<PsiElement> parentChain(Collection<PsiElement> chain, boolean recursive) {
    return CobolPsiImplUtil.parentChain(this, chain, recursive);
  }

}
