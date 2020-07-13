package com.nikos.gnucobol_3_1.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

class ProgramSeed extends CobolCompletionProvider {
    public static String PROGRAM_SEED =
        "identification division.\n" +
            "  program-id. foo.\n" +
            "environment division.\n" +
            "data division.\n" +
            "  working-storage section.\n" +
            "procedure division.\n" +
            "end program foo.";

    @Override
    public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
        // TODO: Indent appropriately.
        resultSet.addElement(LookupElementBuilder.create(PROGRAM_SEED));
    }
}
