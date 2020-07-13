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

public class CobolIdentificationDivision_Impl extends ASTWrapperPsiElement implements CobolIdentificationDivision_ {

  public CobolIdentificationDivision_Impl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CobolVisitor visitor) {
    visitor.visitIdentificationDivision_(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CobolVisitor) accept((CobolVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CobolAuthor_ getAuthor_() {
    return findChildByClass(CobolAuthor_.class);
  }

  @Override
  @Nullable
  public CobolDateCompiled_ getDateCompiled_() {
    return findChildByClass(CobolDateCompiled_.class);
  }

  @Override
  @Nullable
  public CobolDateWritten_ getDateWritten_() {
    return findChildByClass(CobolDateWritten_.class);
  }

  @Override
  @Nullable
  public CobolInstallation_ getInstallation_() {
    return findChildByClass(CobolInstallation_.class);
  }

  @Override
  @Nullable
  public CobolProgramId_ getProgramId_() {
    return findChildByClass(CobolProgramId_.class);
  }

  @Override
  @Nullable
  public CobolSecurity_ getSecurity_() {
    return findChildByClass(CobolSecurity_.class);
  }

}
