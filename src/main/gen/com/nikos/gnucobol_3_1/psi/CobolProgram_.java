// This is a generated file. Not intended for manual editing.
package com.nikos.gnucobol_3_1.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import java.util.Collection;

public interface CobolProgram_ extends PsiElement {

  @Nullable
  CobolDataDivision_ getDataDivision_();

  @NotNull
  CobolEndProgram_ getEndProgram_();

  @Nullable
  CobolEnvironmentDivision_ getEnvironmentDivision_();

  @NotNull
  CobolIdentificationDivision_ getIdentificationDivision_();

  @Nullable
  CobolProcedureDivision_ getProcedureDivision_();

  String name();

  Collection<CobolItemDecl_> wsItems();

  Collection<CobolItemDecl_> lsItems();

  Collection<CobolItemDecl_> items();

  Collection<CobolGroupItemDecl_> groupItems();

  Collection<CobolElementaryItemDecl_> elementaryItems();

  Collection<CobolProgram_> subPrograms();

  Collection<CobolCall_> calls();

}
