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

public class CobolStatement_Impl extends ASTWrapperPsiElement implements CobolStatement_ {

  public CobolStatement_Impl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CobolVisitor visitor) {
    visitor.visitStatement_(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CobolVisitor) accept((CobolVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CobolAccept_ getAccept_() {
    return findChildByClass(CobolAccept_.class);
  }

  @Override
  @Nullable
  public CobolAdd_ getAdd_() {
    return findChildByClass(CobolAdd_.class);
  }

  @Override
  @Nullable
  public CobolCall_ getCall_() {
    return findChildByClass(CobolCall_.class);
  }

  @Override
  @Nullable
  public CobolCompute_ getCompute_() {
    return findChildByClass(CobolCompute_.class);
  }

  @Override
  @Nullable
  public CobolDisplay_ getDisplay_() {
    return findChildByClass(CobolDisplay_.class);
  }

  @Override
  @Nullable
  public CobolDivide_ getDivide_() {
    return findChildByClass(CobolDivide_.class);
  }

  @Override
  @Nullable
  public CobolIf_ getIf_() {
    return findChildByClass(CobolIf_.class);
  }

  @Override
  @Nullable
  public CobolInitialize_ getInitialize_() {
    return findChildByClass(CobolInitialize_.class);
  }

  @Override
  @Nullable
  public CobolMove_ getMove_() {
    return findChildByClass(CobolMove_.class);
  }

  @Override
  @Nullable
  public CobolMultiply_ getMultiply_() {
    return findChildByClass(CobolMultiply_.class);
  }

  @Override
  @Nullable
  public CobolSet_ getSet_() {
    return findChildByClass(CobolSet_.class);
  }

  @Override
  @Nullable
  public CobolSubtract_ getSubtract_() {
    return findChildByClass(CobolSubtract_.class);
  }

}
