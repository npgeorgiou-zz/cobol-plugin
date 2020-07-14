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

public class CobolConditionalItemDecl_Impl extends CobolItemDecl_Impl implements CobolConditionalItemDecl_ {

  public CobolConditionalItemDecl_Impl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CobolVisitor visitor) {
    visitor.visitConditionalItemDecl_(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CobolVisitor) accept((CobolVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CobolItemNameDecl_ getItemNameDecl_() {
    return findChildByClass(CobolItemNameDecl_.class);
  }

  @Override
  @NotNull
  public List<CobolLiteral_> getLiteral_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CobolLiteral_.class);
  }

  @Override
  public List<CobolLiteral_> trueIf() {
    return CobolPsiImplUtil.trueIf(this);
  }

}
