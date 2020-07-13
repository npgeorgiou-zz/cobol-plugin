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

public class CobolLiteral_Impl extends ASTWrapperPsiElement implements CobolLiteral_ {

  public CobolLiteral_Impl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CobolVisitor visitor) {
    visitor.visitLiteral_(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CobolVisitor) accept((CobolVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public boolean isAlphabetic() {
    return CobolPsiImplUtil.isAlphabetic(this);
  }

  @Override
  public boolean isAlphaNumeric() {
    return CobolPsiImplUtil.isAlphaNumeric(this);
  }

  @Override
  public boolean isInteger() {
    return CobolPsiImplUtil.isInteger(this);
  }

  @Override
  public boolean isFloat() {
    return CobolPsiImplUtil.isFloat(this);
  }

  @Override
  public boolean isFigurativeConstant() {
    return CobolPsiImplUtil.isFigurativeConstant(this);
  }

  @Override
  public boolean isAll() {
    return CobolPsiImplUtil.isAll(this);
  }

}
