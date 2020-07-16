// This is a generated file. Not intended for manual editing.
package com.nikos.gnucobol_3_1.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import java.util.Collection;

public interface CobolItemUsage_ extends PsiElement {

  PsiElement setName(String newName);

  CobolItemNameReference getReference();

  CobolItemDecl_ declaration();

  CobolItemUsage_ typedGroupItem(boolean recursive);

  Collection<PsiElement> parentChain(Collection<PsiElement> chain, boolean recursive);

}
