package com.nikos.gnucobol_3_1.psi;

import com.google.common.collect.Lists;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.nikos.gnucobol_3_1.CobolFileType;
import com.nikos.gnucobol_3_1.CobolLanguage;
import com.nikos.gnucobol_3_1.CobolUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CobolFile extends PsiFileBase {
    public CobolFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, CobolLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return CobolFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Cobol File";
    }

    private Collection<CobolProgram_> firstLevelPrograms() {
        return Stream.of(getChildren())
                .filter(psiElement -> psiElement instanceof CobolProgram_)
                .map(psiElement -> (CobolProgram_) psiElement)
                .collect(Collectors.toList());
    }

    public Collection<CobolProgram_> allTypedPrograms() {
        Collection<CobolProgram_> programs = firstLevelPrograms();

        Collection<CobolProgram_> subPrograms = programs.stream()
                .flatMap(program -> program.subPrograms().stream())
                .collect(Collectors.toList());

        programs.addAll(subPrograms);

        return programs;
    }

    public Collection<CobolCopy_> copies() {
        return Lists.newArrayList(getChildren()).stream()
                .filter(psiElement -> psiElement instanceof CobolCopy_)
                .map(psiElement -> (CobolCopy_) psiElement)
                .collect(Collectors.toList());
    }

    public Collection<CobolProgram_> allTypedAndCopiedPrograms() {
        return CobolUtil.traverseFilesAndCollect(this, (f -> f.allTypedPrograms()));
    }
}