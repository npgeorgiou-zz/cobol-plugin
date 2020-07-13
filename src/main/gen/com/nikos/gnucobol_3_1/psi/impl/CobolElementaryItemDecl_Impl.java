// This is a generated file. Not intended for manual editing.
package com.nikos.gnucobol_3_1.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.nikos.gnucobol_3_1.psi.CobolTypes.*;
import com.nikos.gnucobol_3_1.psi.*;

public class CobolElementaryItemDecl_Impl extends CobolItemDecl_Impl implements CobolElementaryItemDecl_ {

  public CobolElementaryItemDecl_Impl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CobolVisitor visitor) {
    visitor.visitElementaryItemDecl_(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CobolVisitor) accept((CobolVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CobolItemNameDecl_ getItemNameDecl_() {
    return findNotNullChildByClass(CobolItemNameDecl_.class);
  }

  @Override
  @NotNull
  public CobolItemTypeDecl_ getItemTypeDecl_() {
    return findNotNullChildByClass(CobolItemTypeDecl_.class);
  }

  @Override
  @Nullable
  public CobolItemUsage_ getItemUsage_() {
    return findChildByClass(CobolItemUsage_.class);
  }

  @Override
  @Nullable
  public CobolItemValueDecl_ getItemValueDecl_() {
    return findChildByClass(CobolItemValueDecl_.class);
  }

  @Override
  public CobolItemDecl_ redefines() {
    return CobolPsiImplUtil.redefines(this);
  }

  @Override
  public String type() {
    return CobolPsiImplUtil.type(this);
  }

  @Override
  public boolean isSigned() {
    return CobolPsiImplUtil.isSigned(this);
  }

  @Override
  public boolean hasDecimals() {
    return CobolPsiImplUtil.hasDecimals(this);
  }

  @Override
  public int length() {
    return CobolPsiImplUtil.length(this);
  }

  @Override
  public int decimalLength() {
    return CobolPsiImplUtil.decimalLength(this);
  }

  @Override
  public PsiElement initialValue() {
    return CobolPsiImplUtil.initialValue(this);
  }

}
