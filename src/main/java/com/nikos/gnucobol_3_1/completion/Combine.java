package com.nikos.gnucobol_3_1.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

class Combine extends CobolCompletionProvider {
    CobolCompletionProvider[] providers;

    public Combine(CobolCompletionProvider... providers) {
        this.providers = providers;
    }

    @Override
    public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
        for (CobolCompletionProvider provider : providers) {
            provider.addCompletions(parameters, context, resultSet);
        }
    }
}
