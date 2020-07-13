package com.nikos.gnucobol_3_1.psi;

import com.intellij.psi.tree.IElementType;
import com.nikos.gnucobol_3_1.CobolLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CobolElementType extends IElementType {
    public CobolElementType(@NotNull @NonNls String debugName) {
        super(debugName, CobolLanguage.INSTANCE);
    }
}