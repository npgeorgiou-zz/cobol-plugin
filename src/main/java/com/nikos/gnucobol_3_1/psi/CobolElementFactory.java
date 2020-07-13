package com.nikos.gnucobol_3_1.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.nikos.gnucobol_3_1.CobolFileType;
import com.nikos.gnucobol_3_1.CobolUtil;
import com.nikos.gnucobol_3_1.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class CobolElementFactory {

    public static CobolProgram_ createProgram(
        Project project,
        String name,
        String procedureDivisionContent
    ) {
        String text = "identification division.\n" +
                          "  program-id." + name + ".\n" +
                          "environment division.\n" +
                          "data division.\n" +
                          "procedure division.\n" +
                          procedureDivisionContent +
                          "end program " + name + ".";

        final CobolFile file = createFile(project, text);
        return (CobolProgram_) file.getFirstChild();
    }

    public static PsiElement createIdentifier(Project project, String name) {
        CobolProgram_ p = createProgram(project, name, "");

        CobolProgramNameDecl_ e = p.getIdentificationDivision_().getProgramId_().getProgramNameDecl_();

        if (e == null) {
            return null;
        }

        return e.getIdentifierOrString_().getNode().findChildByType(CobolTypes.IDENTIFIER).getPsi();
    }

    public static CobolCompute_ createComputeAddStatement(Project project, ArrayList<ArrayList<PsiElement>> summands, ArrayList<ArrayList<PsiElement>> destinations) {
        String text = "compute ";
        text += Util.implode(destinations.stream().map(it -> CobolUtil.toString(it)).collect(Collectors.toList()), ", ");
        text += " = ";
        text += Util.implode(summands.stream().map(it -> CobolUtil.toString(it)).collect(Collectors.toList()), " + ");
        text += ".";

        CobolSentence_ s = (CobolSentence_) createProgram(project, "foo", text).getProcedureDivision_().getLastChild();
        CobolCompute_ c = (CobolCompute_) s.getStatement_List().get(0).getFirstChild();
        return c;
    }

    public static CobolCompute_ createComputeSubtractStatement(Project project, ArrayList<ArrayList<PsiElement>> minuends, ArrayList<ArrayList<PsiElement>> destinations) {
        String text = "compute ";
        text += Util.implode(destinations.stream().map(it -> CobolUtil.toString(it)).collect(Collectors.toList()), ", ");
        text += " = ";
        text += Util.implode(minuends.stream().map(it -> CobolUtil.toString(it)).collect(Collectors.toList()), " - ");
        text += ".";

        CobolSentence_ s = (CobolSentence_) createProgram(project, "foo", text).getProcedureDivision_().getLastChild();
        CobolCompute_ c = (CobolCompute_) s.getStatement_List().get(0).getFirstChild();
        return c;
    }

    public static CobolCompute_ createComputeMultiplyStatement(Project project, ArrayList<ArrayList<PsiElement>> factors, ArrayList<ArrayList<PsiElement>> destinations) {
        String text = "compute ";
        text += Util.implode(destinations.stream().map(it -> CobolUtil.toString(it)).collect(Collectors.toList()), ", ");
        text += " = ";
        text += Util.implode(factors.stream().map(it -> CobolUtil.toString(it)).collect(Collectors.toList()), " * ");
        text += ".";

        CobolSentence_ s = (CobolSentence_) createProgram(project, "foo", text).getProcedureDivision_().getLastChild();
        CobolCompute_ c = (CobolCompute_) s.getStatement_List().get(0).getFirstChild();
        return c;
    }

    public static CobolCompute_ createComputeDivideStatement(
        Project project,
        ArrayList<PsiElement> divident,
        ArrayList<PsiElement> divisor,
        ArrayList<ArrayList<PsiElement>> destinations
    ) {
        String text = "compute ";
        text += Util.implode(destinations.stream().map(it -> CobolUtil.toString(it)).collect(Collectors.toList()), ", ");
        text += " = ";
        text += CobolUtil.toString(divident) + " / " + CobolUtil.toString(divisor);
        text += ".";

        CobolSentence_ s = (CobolSentence_) createProgram(project, "foo", text).getProcedureDivision_().getLastChild();
        CobolCompute_ c = (CobolCompute_) s.getStatement_List().get(0).getFirstChild();
        return c;
    }

    public static CobolFile createFile(Project project, String text) {
        String name = "dummy.cob";
        return (CobolFile) PsiFileFactory.getInstance(project).createFileFromText(name, CobolFileType.INSTANCE, text);
    }

    public static PsiElement createWhitespace(Project project) {
        return PsiParserFacade.SERVICE.getInstance(project).createWhiteSpaceFromText(" ");
    }

    public static PsiElement createNewline(Project project) {
        return PsiParserFacade.SERVICE.getInstance(project).createWhiteSpaceFromText(System.lineSeparator());
    }
}