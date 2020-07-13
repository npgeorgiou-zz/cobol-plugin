package com.nikos.gnucobol_3_1.refactoring;

import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.openapi.project.Project;
import com.nikos.gnucobol_3_1.psi.CobolElementFactory;
import org.jetbrains.annotations.NotNull;

public class CobolNamesValidator implements NamesValidator {
    @Override
    public boolean isKeyword(@NotNull String name, Project project) {
        return false;
    }

    @Override
    public boolean isIdentifier(@NotNull String name, Project project) {
        return CobolElementFactory.createIdentifier(project, name) != null;
    }
}