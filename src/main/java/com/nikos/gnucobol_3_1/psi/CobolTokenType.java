package com.nikos.gnucobol_3_1.psi;

import com.intellij.psi.tree.IElementType;
import com.nikos.gnucobol_3_1.CobolLanguage;
import org.jetbrains.annotations.*;

public class CobolTokenType extends IElementType {
    public CobolTokenType(@NotNull @NonNls String debugName) {
        super(debugName, CobolLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "CobolTokenType." + super.toString();
    }
}