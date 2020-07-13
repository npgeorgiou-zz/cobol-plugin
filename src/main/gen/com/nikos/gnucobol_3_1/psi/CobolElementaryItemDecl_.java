// This is a generated file. Not intended for manual editing.
package com.nikos.gnucobol_3_1.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CobolElementaryItemDecl_ extends CobolItemDecl_ {

  @NotNull
  CobolItemNameDecl_ getItemNameDecl_();

  @NotNull
  CobolItemTypeDecl_ getItemTypeDecl_();

  @Nullable
  CobolItemValueDecl_ getItemValueDecl_();

  String type();

  boolean isSigned();

  boolean hasDecimals();

  int length();

  int decimalLength();

  String initialValue();

}
