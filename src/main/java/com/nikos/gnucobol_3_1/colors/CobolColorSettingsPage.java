package com.nikos.gnucobol_3_1.colors;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.nikos.gnucobol_3_1.CobolIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class CobolColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Literals - String", CobolSyntaxHighlighter.STRING_STYLE),
            new AttributesDescriptor("Literals - Number", CobolSyntaxHighlighter.NUMBER_STYLE),
            new AttributesDescriptor("Comment", CobolSyntaxHighlighter.COMMENT_STYLE),

            new AttributesDescriptor("Dot", CobolSyntaxHighlighter.DOT_STYLE),
            new AttributesDescriptor("Headers - Division header", CobolSyntaxHighlighter.DIVISION_HEADER_STYLE),
            new AttributesDescriptor("Headers - Section header", CobolSyntaxHighlighter.SECTION_HEADER_STYLE),
            new AttributesDescriptor("Headers - Division paragraphs", CobolSyntaxHighlighter.DIVISION_PARAGRAPHS_STYLE),

            new AttributesDescriptor("Identifier", CobolSyntaxHighlighter.IDENTIFIER_STYLE),
            new AttributesDescriptor("Keywords", CobolSyntaxHighlighter.KEYWORD_STYLE),
            new AttributesDescriptor("Conceptual data items", CobolSyntaxHighlighter.CONCEPTUAL_DATA_ITEMS_STYLE),
            new AttributesDescriptor("Figurative constants", CobolSyntaxHighlighter.FIGURATIVE_CONSTANTS_STYLE),
            new AttributesDescriptor("Program declaration", CobolSyntaxHighlighter.PROGRAM_DECLARATION_STYLE),
            new AttributesDescriptor("Program end", CobolSyntaxHighlighter.PROGRAM_END_STYLE),
            new AttributesDescriptor("Program call", CobolSyntaxHighlighter.PROGRAM_CALL_STYLE),

            new AttributesDescriptor("Bad Value", CobolSyntaxHighlighter.BAD_CHARACTER_STYLE),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return CobolIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new CobolSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "identification division.\n" +
                "  program-id. helloWorld.\n" +
                "\n" +
                "environment division.\n" +
                "    data division.\n" +
                "        working-storage section.\n" +
                "            01 response pic 9(1).\n" +
                "            01 response pic 9(1) value 3.\n" +
                "            01 response pic 9(1) value zero.\n" +
                "\n" +
                "procedure division.\n" +
                "    accept response from date yyyymmdd.\n" +
                "    call \"aNumber\" using response.\n" +
                "    display response.\n" +
                "\n" +
                "    *> comment\n" +
                "    identification division.\n" +
                "        program-id. aNumber.\n" +
                "        data division.\n" +
                "            linkage section.\n" +
                "                01 response pic 9(1).\n" +
                "    procedure division using response.\n" +
                "        move 5 to response.\n" +
                "    end program aNumber.\n" +
                "\n" +
                "end program helloWorld.\n";
    }

    @Override
    public @Nullable Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Cobol";
    }
}