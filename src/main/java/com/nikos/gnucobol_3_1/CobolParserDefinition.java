package com.nikos.gnucobol_3_1;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.nikos.gnucobol_3_1.parser.CobolParser;
import com.nikos.gnucobol_3_1.psi.CobolFile;
import com.nikos.gnucobol_3_1.psi.CobolTokenType;
import com.nikos.gnucobol_3_1.psi.CobolTypes;
import org.jetbrains.annotations.NotNull;

public class CobolParserDefinition implements ParserDefinition {
    public static final IElementType COMMENT = new CobolTokenType("COMMENT");

    public static final TokenSet COMMENTS = TokenSet.create(COMMENT);
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet STRING_LITERALS = TokenSet.create(CobolTypes.STRING);
    public static final TokenSet NUMBER_LITERALS = TokenSet.create(CobolTypes.INTEGER, CobolTypes.FLOAT);

    public static final TokenSet DIVISION_HEADERS = TokenSet.create(
            CobolTypes.IDENTIFICATION_DIVISION,
            CobolTypes.ENVIRONMENT_DIVISION,
            CobolTypes.DATA_DIVISION,
            CobolTypes.PROCEDURE_DIVISION
    );

    public static final TokenSet DIVISION_PARAGRAPH_HEADERS = TokenSet.create(
            CobolTypes.PROGRAM_ID,
            CobolTypes.AUTHOR,
            CobolTypes.INSTALLATION,
            CobolTypes.DATE_WRITTEN,
            CobolTypes.DATE_COMPILED,
            CobolTypes.SECURITY
    );

    public static final TokenSet DIVISION_SECTION_HEADERS = TokenSet.create(
            CobolTypes.WORKING_STORAGE_SECTION,
            CobolTypes.LOCAL_STORAGE_SECTION,
            CobolTypes.LINKAGE_SECTION
    );

    public static final TokenSet PARENTHESES = TokenSet.create(
        CobolTypes.PAREN_OPEN,
        CobolTypes.PAREN_CLOSE
    );

    public static final TokenSet OPERATORS = TokenSet.create(
        CobolTypes.EQUALS_OP,
        CobolTypes.POWER_OP,
        CobolTypes.ADD_OP,
        CobolTypes.SUBTRACT_OP,
        CobolTypes.MULTIPLY_OP,
        CobolTypes.DIVIDE_OP,
        CobolTypes.MORE_OP,
        CobolTypes.MORE_EQUAL_OP,
        CobolTypes.LESS_OP,
        CobolTypes.LESS_EQUAL_OP
    );

    public static final TokenSet KEYWORDS = TokenSet.create(
            CobolTypes.PIC,
            CobolTypes.IS,
            CobolTypes.VALUE,
            CobolTypes.REDEFINES,
            CobolTypes.RENAMES,
            CobolTypes.THROUGH,
            CobolTypes.PIC_TYPE_ALPHA,
            CobolTypes.PIC_TYPE_ALPHANUMERIC,
            CobolTypes.PIC_TYPE_NUMERIC,
            CobolTypes.PIC_TYPE_SIGNED_NUMERIC,
            CobolTypes.PIC_TYPE_DECIMAL_NUMERIC,
            CobolTypes.ACCEPT,
            CobolTypes.FROM,
            CobolTypes.DISPLAY,
            CobolTypes.OF,
            CobolTypes.IN,
            CobolTypes.ADD,
            CobolTypes.GIVING,
            CobolTypes.SUBTRACT,
            CobolTypes.MULTIPLY,
            CobolTypes.DIVIDE,
            CobolTypes.INTO,
            CobolTypes.REMAINDER,
            CobolTypes.COMPUTE,
            CobolTypes.INITIALIZE,
            CobolTypes.REPLACING,
            CobolTypes.ALPHABETIC,
            CobolTypes.ALPHANUMERIC,
            CobolTypes.NUMERIC,
            CobolTypes.DATA,
            CobolTypes.BY,
            CobolTypes.USING,
            CobolTypes.MOVE,
            CobolTypes.CORR,
            CobolTypes.CORRESPONDING,
            CobolTypes.TO,
            CobolTypes.END,
            CobolTypes.PROGRAM,
            CobolTypes.COPY,
            CobolTypes.CALL,
            CobolTypes.USING,
            CobolTypes.SET,
            CobolTypes.TRUE,
            CobolTypes.IF,
            CobolTypes.ELSE,
            CobolTypes.THEN,
            CobolTypes.END_IF,
            CobolTypes.NOT,
            CobolTypes.AND,
            CobolTypes.OR,
            CobolTypes.EQUAL,
            CobolTypes.EQUALS,
            CobolTypes.GREATER,
            CobolTypes.LESS,
            CobolTypes.THAN,
            CobolTypes.POSITIVE,
            CobolTypes.NEGATIVE
    );

    public static final TokenSet CONCEPTUAL_DATA_ITEMS = TokenSet.create(
            CobolTypes.DATE,
            CobolTypes.YYYYMMDD,
            CobolTypes.DAY,
            CobolTypes.YYYYDDD,
            CobolTypes.DAY_OF_WEEK,
            CobolTypes.TIME
    );

    public static final TokenSet FIGURATIVE_CONSTANTS = TokenSet.create(
        CobolTypes.ZERO,
        CobolTypes.ZERO,
        CobolTypes.ZERO,
        CobolTypes.SPACE,
        CobolTypes.SPACE,
        CobolTypes.HIGH_VALUE,
        CobolTypes.HIGH_VALUE,
        CobolTypes.LOW_VALUE,
        CobolTypes.LOW_VALUE,
        CobolTypes.QUOTE,
        CobolTypes.QUOTE,
        CobolTypes.NULL,
        CobolTypes.NULL,
        CobolTypes.ALL
        );

    public static final IFileElementType FILE = new IFileElementType(CobolLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new CobolLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return STRING_LITERALS;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new CobolParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new CobolFile(viewProvider);
    }

    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return CobolTypes.Factory.createElement(node);
    }
}