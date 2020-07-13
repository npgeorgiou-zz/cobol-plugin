package com.nikos.gnucobol_3_1.colors;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.nikos.gnucobol_3_1.CobolLexerAdapter;
import com.nikos.gnucobol_3_1.CobolParserDefinition;
import com.nikos.gnucobol_3_1.psi.CobolTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class CobolSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey STRING_STYLE = createTextAttributesKey("COBOL_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER_STYLE = createTextAttributesKey("COBOL_DIGIT", DefaultLanguageHighlighterColors.NUMBER);

    public static final TextAttributesKey COMMENT_STYLE = createTextAttributesKey("COBOL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey DOT_STYLE = createTextAttributesKey("COBOL_DOT", DefaultLanguageHighlighterColors.SEMICOLON);

    public static final TextAttributesKey DIVISION_HEADER_STYLE = createTextAttributesKey("COBOL_DIVISION_HEADER", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL);
    public static final TextAttributesKey DIVISION_PARAGRAPHS_STYLE = createTextAttributesKey("COBOL_DIVISION_PARAGRAPHS", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL);
    public static final TextAttributesKey SECTION_HEADER_STYLE = createTextAttributesKey("COBOL_SECTION_HEADER", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL);

    public static final TextAttributesKey IDENTIFIER_STYLE = createTextAttributesKey("COBOL_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey KEYWORD_STYLE = createTextAttributesKey("COBOL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey CONCEPTUAL_DATA_ITEMS_STYLE = createTextAttributesKey("COBOL_CONCEPTUAL_DATA_ITEMS", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey FIGURATIVE_CONSTANTS_STYLE = createTextAttributesKey("COBOL_FIGURATIVE_CONSTANTS", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey PROGRAM_CALL_STYLE = createTextAttributesKey("COBOL_PROGRAM_CALL", DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey PROGRAM_DECLARATION_STYLE = createTextAttributesKey("COBOL_PROGRAM_DECLARATION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey PROGRAM_END_STYLE = createTextAttributesKey("COBOL_PROGRAM_END", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);

    public static final TextAttributesKey BAD_CHARACTER_STYLE = createTextAttributesKey("COBOL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

    static {
        fillMap(ATTRIBUTES, CobolParserDefinition.DIVISION_HEADERS, DIVISION_HEADER_STYLE);
        fillMap(ATTRIBUTES, CobolParserDefinition.DIVISION_PARAGRAPH_HEADERS, DIVISION_PARAGRAPHS_STYLE);
        fillMap(ATTRIBUTES, CobolParserDefinition.DIVISION_SECTION_HEADERS, SECTION_HEADER_STYLE);
        fillMap(ATTRIBUTES, CobolParserDefinition.KEYWORDS, KEYWORD_STYLE);
        fillMap(ATTRIBUTES, CobolParserDefinition.NUMBER_LITERALS, NUMBER_STYLE);
        fillMap(ATTRIBUTES, CobolParserDefinition.CONCEPTUAL_DATA_ITEMS, CONCEPTUAL_DATA_ITEMS_STYLE);
        fillMap(ATTRIBUTES, CobolParserDefinition.FIGURATIVE_CONSTANTS, FIGURATIVE_CONSTANTS_STYLE);

        fillMap(ATTRIBUTES, STRING_STYLE, CobolTypes.STRING);
        fillMap(ATTRIBUTES, COMMENT_STYLE, CobolParserDefinition.COMMENT);
        fillMap(ATTRIBUTES, DOT_STYLE, CobolTypes.DOT);
        fillMap(ATTRIBUTES, IDENTIFIER_STYLE, CobolTypes.IDENTIFIER);
        fillMap(ATTRIBUTES, BAD_CHARACTER_STYLE, TokenType.BAD_CHARACTER);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new CobolLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(ATTRIBUTES.get(tokenType));
    }
}