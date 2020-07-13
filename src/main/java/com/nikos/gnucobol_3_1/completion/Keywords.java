package com.nikos.gnucobol_3_1.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

class Keywords extends CobolCompletionProvider {
    String[] keywords;
    boolean canContinue;

    public Keywords(String... keywords) {
        this(true, keywords);
    }

    public Keywords(boolean canContinue, String... keywords) {
        this.keywords = keywords;
        this.canContinue = canContinue;
    }

    @Override
    public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
        for (String keyword : keywords) {
            resultSet.addElement(LookupElementBuilder.create(keyword + (canContinue ? " " : "")));
        }
    }
}
