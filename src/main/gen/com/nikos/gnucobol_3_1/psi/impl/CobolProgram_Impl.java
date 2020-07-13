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

public class CobolProgram_Impl extends ASTWrapperPsiElement implements CobolProgram_ {

  public CobolProgram_Impl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CobolVisitor visitor) {
    visitor.visitProgram_(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CobolVisitor) accept((CobolVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CobolDataDivision_ getDataDivision_() {
    return findChildByClass(CobolDataDivision_.class);
  }

  @Override
  @NotNull
  public CobolEndProgram_ getEndProgram_() {
    return findNotNullChildByClass(CobolEndProgram_.class);
  }

  @Override
  @Nullable
  public CobolEnvironmentDivision_ getEnvironmentDivision_() {
    return findChildByClass(CobolEnvironmentDivision_.class);
  }

  @Override
  @NotNull
  public CobolIdentificationDivision_ getIdentificationDivision_() {
    return findNotNullChildByClass(CobolIdentificationDivision_.class);
  }

  @Override
  @Nullable
  public CobolProcedureDivision_ getProcedureDivision_() {
    return findChildByClass(CobolProcedureDivision_.class);
  }

  @Override
  public String name() {
    return CobolPsiImplUtil.name(this);
  }

  @Override
  public Collection<CobolItemDecl_> wsItems() {
    return CobolPsiImplUtil.wsItems(this);
  }

  @Override
  public Collection<CobolItemDecl_> lsItems() {
    return CobolPsiImplUtil.lsItems(this);
  }

  @Override
  public Collection<CobolItemDecl_> items() {
    return CobolPsiImplUtil.items(this);
  }

  @Override
  public Collection<CobolGroupItemDecl_> groupItems() {
    return CobolPsiImplUtil.groupItems(this);
  }

  @Override
  public Collection<CobolElementaryItemDecl_> elementaryItems() {
    return CobolPsiImplUtil.elementaryItems(this);
  }

  @Override
  public Collection<CobolProgram_> subPrograms() {
    return CobolPsiImplUtil.subPrograms(this);
  }

  @Override
  public Collection<CobolCall_> calls() {
    return CobolPsiImplUtil.calls(this);
  }

}
