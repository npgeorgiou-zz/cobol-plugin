// This is a generated file. Not intended for manual editing.
package com.nikos.gnucobol_3_1.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import java.util.Collection;

public interface CobolConditionalItemDecl_ extends CobolItemDecl_ {

  @NotNull
  CobolItemNameDecl_ getItemNameDecl_();

  @NotNull
  List<CobolLiteral_> getLiteral_List();

  Collection<CobolLiteral_> trueIf();

}
