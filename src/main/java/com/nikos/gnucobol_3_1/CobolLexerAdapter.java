package com.nikos.gnucobol_3_1;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class CobolLexerAdapter extends FlexAdapter {
    public CobolLexerAdapter() {
        super(new CobolLexer((Reader) null));
    }
}