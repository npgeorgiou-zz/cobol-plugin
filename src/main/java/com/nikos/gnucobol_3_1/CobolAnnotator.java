package com.nikos.gnucobol_3_1;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import com.nikos.gnucobol_3_1.colors.CobolSyntaxHighlighter;
import com.nikos.gnucobol_3_1.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CobolAnnotator implements Annotator {

    private void error(PsiElement element, String text, AnnotationHolder holder, IntentionAction... fixes) {
        Annotation annotation = holder.createErrorAnnotation(element, text);
        annotation.setTextAttributes(CobolSyntaxHighlighter.BAD_CHARACTER_STYLE);

        for (IntentionAction fix : fixes) {
            annotation.registerFix(fix);
        }
    }

    private void error(PsiElement element, String text, AnnotationHolder holder) {
        Annotation annotation = holder.createErrorAnnotation(element, text);
        annotation.setTextAttributes(CobolSyntaxHighlighter.BAD_CHARACTER_STYLE);
    }

    private void warning(PsiElement element, String text, AnnotationHolder holder) {
        Annotation annotation = holder.createWarningAnnotation(element, text);
        annotation.setTextAttributes(CobolSyntaxHighlighter.BAD_CHARACTER_STYLE);
    }

    private void info(PsiElement element, String text, AnnotationHolder holder) {
        Annotation annotation = holder.createInfoAnnotation(element, text);
        annotation.setTextAttributes(CobolSyntaxHighlighter.BAD_CHARACTER_STYLE);
    }

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof CobolItemDecl_) {
            CobolItemDecl_ itemDeclaration = (CobolItemDecl_) element;

            duplicateItemName(itemDeclaration, holder);

            if (element instanceof CobolElementaryItemDecl_) {
                CobolElementaryItemDecl_ item = (CobolElementaryItemDecl_) element;

                if (item.redefines() != null) {
                    checkRedefines(item, holder);
                }

                if (item.level() == 77) {
                    checkNonContiguousItem(item, holder);
                }
            }

            if (element instanceof CobolRenamesItemDecl_) {
                CobolRenamesItemDecl_ item = (CobolRenamesItemDecl_) element;
                checkRenames(item, holder);
            }

            return;
        }

        if (element instanceof CobolItemUsage_) {
            CobolItemUsage_ itemUsage = (CobolItemUsage_) element;

            undefinedItem(itemUsage, holder);
            nonExistingParent(itemUsage, holder);
            return;
        }

        if (element instanceof CobolMove_) {
            CobolMove_ move = (CobolMove_) element;
            if (
                move.getNode().findChildByType(CobolTypes.CORRESPONDING) != null
                    || move.getNode().findChildByType(CobolTypes.CORR) != null
            ) {
                notGroupOperands(move.getItemUsage_List(), holder);
            }

            return;
        }

        if (element instanceof CobolDisplay_) {
            CobolDisplay_ display = (CobolDisplay_) element;

            conditionalOperands(display.getItemUsage_List(), holder);

            return;
        }

        if (element instanceof CobolAdd_) {
            CobolAdd_ add = (CobolAdd_) element;

            if (
                add.getNode().findChildByType(CobolTypes.CORRESPONDING) != null
                    || add.getNode().findChildByType(CobolTypes.CORR) != null
            ) {
                notGroupOperands(add.getItemUsage_List(), holder);
            } else {
                notElementaryOperands(add.getItemUsage_List(), holder);
                notNumericItems(add.getItemUsage_List(), holder);
            }

            return;
        }

        if (element instanceof CobolSubtract_) {
            CobolSubtract_ subtract = (CobolSubtract_) element;

            if (
                subtract.getNode().findChildByType(CobolTypes.CORRESPONDING) != null
                    || subtract.getNode().findChildByType(CobolTypes.CORR) != null
            ) {
                notGroupOperands(subtract.getItemUsage_List(), holder);
            } else {
                notElementaryOperands(subtract.getItemUsage_List(), holder);
                notNumericItems(subtract.getItemUsage_List(), holder);
            }

            return;
        }

        if (element instanceof CobolMultiply_) {
            CobolMultiply_ multiply = (CobolMultiply_) element;

            notElementaryOperands(multiply.getItemUsage_List(), holder);
            notNumericItems(multiply.getItemUsage_List(), holder);

            return;
        }

        if (element instanceof CobolDivide_) {
            CobolDivide_ divide = (CobolDivide_) element;

            notElementaryOperands(divide.getItemUsage_List(), holder);
            notNumericItems(divide.getItemUsage_List(), holder);

            return;
        }

        if (element instanceof CobolCompute_) {
            CobolCompute_ compute = (CobolCompute_) element;

            notElementaryOperands(compute.getItemUsage_List(), holder);
            return;
        }

        if (element instanceof CobolCondition_) {
            CobolCondition_ condition = (CobolCondition_) element;

            truthConditionWithoutConditionalItem(condition, holder);
            return;
        }

        if (element instanceof CobolCopy_) {
            CobolCopy_ copy = (CobolCopy_) element;

            missingCopyTargets(copy, holder);
            duplicateCopies(copy, holder);
            copyContainsAlreadyExistingProgram(copy, holder);
            unusedCopies(copy, holder);
            return;
        }

        if (element instanceof CobolCall_) {
            CobolCall_ call = (CobolCall_) element;

            dynamicCall(call, holder);
            callToNonExistingProgram(call, holder);
            missingArguments(call, holder);
            return;
        }

        if (element instanceof CobolProgramId_) {
            CobolProgramId_ programId = (CobolProgramId_) element;

            duplicateName(programId, holder);
            return;
        }

        if (element instanceof CobolEndProgram_) {
            CobolEndProgram_ endProgram = (CobolEndProgram_) element;

            nonMatchingEndProgramName(endProgram, holder);
            return;
        }
    }

    private void duplicateItemName(CobolItemDecl_ declaration, AnnotationHolder holder) {
        CobolProgram_ program = PsiTreeUtil.getParentOfType(declaration, CobolProgram_.class);

        Collection<CobolItemDecl_> allVars = program.items();

        // Remove self.
        allVars.removeIf(it -> it.equals(declaration));

        // Remove those with other name.
        allVars.removeIf(it -> !it.name().equals(declaration.name()));

        if (!allVars.isEmpty()) {
            for (CobolItemDecl_ var : allVars) {
                error(var, "Duplicate item name.", holder);
            }
        }
    }

    private void checkRedefines(CobolElementaryItemDecl_ declaration, AnnotationHolder holder) {
        PsiElement redefined = declaration.redefines();

        if (redefined instanceof CobolElementaryItemDecl_) {
            if (((CobolElementaryItemDecl_) redefined).redefines() != null) {
                error(declaration, "Cant redefine a redefining item.", holder);
                return;
            }
        }

        CobolItemDecl_ previousSameLevelDecl = CobolUtil.previousSibling(
            declaration,
            (element) -> {
                if (!(element instanceof CobolItemDecl_)) return false;
                return ((CobolItemDecl_) element).level() < declaration.level();
            },
            (element) -> {
                if (!(element instanceof CobolItemDecl_)) return false;

                // Skip other redefinitions.
                if (element instanceof CobolElementaryItemDecl_) {
                    if (((CobolElementaryItemDecl_) element).redefines() != null) return false;
                }

                return ((CobolItemDecl_) element).level() == declaration.level();
            }
        );

        if (!declaration.redefines().equals(previousSameLevelDecl)) {
            error(declaration, "Redefining item must follow the original definition.", holder);
        }
    }

    private void checkRenames(CobolRenamesItemDecl_ declaration, AnnotationHolder holder) {
        List<CobolItemUsage_> renamedItems = declaration.getItemUsage_List();

        PsiElement renameStart = renamedItems.get(0).getReference().resolve();
        if (renameStart == null) return;

        PsiElement renameEnd = renamedItems.get(1).getReference().resolve();
        if (renameEnd == null) return;

        if (renameStart.equals(renameEnd)) {
            error(declaration, "Renamed start and end items cant be the same.", holder);
        }
    }

    private void checkNonContiguousItem(CobolElementaryItemDecl_ declaration, AnnotationHolder holder) {
        CobolItemDecl_ nextItem = CobolUtil.nextSibling(
            declaration,
            null,
            (element) -> element instanceof CobolItemDecl_
        );

        if (nextItem == null) return;

        if (nextItem.level() != 1 && nextItem.level() != 77 && nextItem.level() != 88) {
            error(declaration, "Non-contiguous item can only be at first level.", holder);
        }
    }

    private void notGroupOperands(List<CobolItemUsage_> itemUsages, AnnotationHolder holder) {
        for (CobolItemUsage_ usage : itemUsages) {
            CobolItemDecl_ declaration = usage.declaration();
            if (declaration == null) continue;

            if (!(declaration instanceof CobolGroupItemDecl_)) {
                error(usage, "Not a group item.", holder);
            }
        }
    }

    private void notElementaryOperands(List<CobolItemUsage_> itemUsages, AnnotationHolder holder) {
        for (CobolItemUsage_ usage : itemUsages) {
            CobolItemDecl_ declaration = usage.declaration();
            if (declaration == null) continue;

            if (PsiTreeUtil.prevVisibleLeaf(usage).getNode().getElementType() == CobolTypes.OF) {
                continue;
            }

            if (!(declaration instanceof CobolElementaryItemDecl_)) {
                error(usage, "Not an elementary item.", holder);
            }
        }
    }

    private void notNumericItems(Collection<CobolItemUsage_> itemUsages, AnnotationHolder holder) {
        for (CobolItemUsage_ usage : itemUsages) {
            CobolItemDecl_ declaration = usage.declaration();
            if (declaration == null) continue;

            if (!(declaration instanceof CobolElementaryItemDecl_)) continue;

            String type = ((CobolElementaryItemDecl_) declaration).type();

            // Can happen if a user types pic after a group item, then it becomes an Elementary item
            // but it still has no type. Maybe a better approach is to have only one Item and play with
            // isGroup, isElem, isConditional etch.
            if (type == null) continue;

            if (!type.equals(CobolUtil.NUMERIC)) {
                error(usage, "Not a numeric item.", holder);
            }
        }
    }

    private void undefinedItem(CobolItemUsage_ itemUsage, AnnotationHolder holder) {
        if (itemUsage.getReference().resolve() == null) {
            error(itemUsage, "Undefined item.", holder);
        }
    }

    private void conditionalOperands(Collection<CobolItemUsage_> itemUsages, AnnotationHolder holder) {
        for (CobolItemUsage_ usage : itemUsages) {
            CobolItemDecl_ declaration = usage.declaration();
            if (declaration == null) continue;

            if (PsiTreeUtil.prevVisibleLeaf(usage).getNode().getElementType() == CobolTypes.OF) {
                continue;
            }

            if (declaration instanceof CobolConditionalItemDecl_) {
                error(usage, "Conditional item not allowed here.", holder);
            }
        }
    }

    private void nonExistingParent(CobolItemUsage_ itemUsage, AnnotationHolder holder) {
        CobolItemUsage_ typedGroupItem = itemUsage.typedGroupItem(false);

        if (typedGroupItem == null) return;

        CobolItemDecl_ declaration = itemUsage.declaration();
        if (declaration == null) {
            return;
        }

        CobolItemDecl_ parent = declaration.parent();

        if (parent == null) {
            error(itemUsage.getNextSibling().getNextSibling(), "Item has no parent.", holder);
            return;
        }

        if (!typedGroupItem.getText().equals(parent.name())) {
            CorrectGroupFix fix = new CorrectGroupFix(typedGroupItem, parent.name());
            error(typedGroupItem, "Wrong group, right one is " + parent.name(), holder, fix);
        }
    }

    private void missingCopyTargets(CobolCopy_ copy, AnnotationHolder holder) {
        if (copy.file() == null) {
            error(copy, "File not found in copy directory.", holder);
        }
    }

    private void duplicateCopies(CobolCopy_ copy, AnnotationHolder holder) {
        CobolFile copiedFile = copy.file();
        if (copiedFile == null) {
            return;
        }

        if (!CobolUtil.fileWillBeCompiled((CobolFile) copy.getContainingFile())) {
            return;
        }

        Collection<CobolCopy_> copies = CobolUtil.traverseFilesAndCollect(
            CobolUtil.mainFileFor(copy.getProject()),
            file -> file.copies()
        );

        // Remove self.
        copies.removeIf(c -> c.equals(copy));

        // Remove if not pointing at the same file.
        copies.removeIf(c -> c.file() == null || !c.file().equals(copy.file()));

        if (!copies.isEmpty()) {
            for (CobolCopy_ c : copies) {
                error(copy, "Duplicate copy, also in file " + c.getContainingFile().getName(), holder);
            }
        }
    }

    private void copyContainsAlreadyExistingProgram(CobolCopy_ copy, AnnotationHolder holder) {
        CobolFile copiedFile = copy.file();
        if (copiedFile == null) {
            return;
        }

        if (!CobolUtil.fileWillBeCompiled((CobolFile) copy.getContainingFile())) {
            return;
        }

        Collection<CobolProgram_> contributedFromCopy = copiedFile.allTypedAndCopiedPrograms();

        Collection<CobolProgram_> programsInAllOtherFiles = CobolUtil.mainFileFor(copy.getProject())
                                                                .allTypedAndCopiedPrograms().stream()
                                                                .filter(program -> !contributedFromCopy.contains(program))
                                                                .collect(Collectors.toList());


        for (CobolProgram_ fromCopy : contributedFromCopy) {
            for (CobolProgram_ inOtherFile : programsInAllOtherFiles) {
                if (fromCopy.name().equals(inOtherFile.name())) {
                    error(copy, "Program with name " + fromCopy.name() + " already existing in file " + inOtherFile.getContainingFile().getName(), holder);
                }
            }
        }
    }

    private void unusedCopies(CobolCopy_ copy, AnnotationHolder holder) {
        CobolFile copiedFile = copy.file();
        if (copiedFile == null) {
            return;
        }

        Collection<CobolProgram_> contributedFromCopy = copiedFile.allTypedAndCopiedPrograms();

        Collection<CobolProgram_> programsInAllOtherFiles = CobolUtil.traverseFilesAndCollect(
            CobolUtil.mainFileFor(copy.getProject()),
            (f -> (f.equals(copiedFile)) ? new ArrayList<>() : f.allTypedPrograms())
        );


        Collection<String> staticCallTargets = programsInAllOtherFiles.stream()
                                                   .flatMap(program -> program.calls().stream())
                                                   .filter(call -> call.isStatic())
                                                   .map(call -> call.subprogramName())
                                                   .collect(Collectors.toList());

        boolean isNotUsed = contributedFromCopy.stream().noneMatch(copiedProgram -> {
            return staticCallTargets.stream().anyMatch(staticCallTarget -> {
                return staticCallTarget.equals(copiedProgram.name());
            });
        });

        if (isNotUsed) {
            error(copy, "Not used.", holder);
        }
    }

    // TODO check something called inspections, Go project seems to have this kind of checks there.
    private void dynamicCall(CobolCall_ call, AnnotationHolder holder) {
        // Not supporting calling by variable.
        if (!call.isStatic()) {
            error(call, "Not supporting IDE help for dynamic calls.", holder);
            return;
        }
    }

    private void callToNonExistingProgram(CobolCall_ call, AnnotationHolder holder) {
        if (!call.isStatic()) {
            return;
        }

        /*
         * Oh COBOL... In COBOL any program included in a file in visible to each other, including programs copied from other files.
         * For example:
         *
         * File 1------.
         * [p1         .
         *   [p2       .
         *       [p3]  .
         *   ]         .
         * ]           .
         * [p4]        .
         *-------------
         * p1 can call p2 and p3 (!)
         * p2 can call p1 and p3 as expected.
         * p3 can call p1 and p2 as expected.
         * p4 can call p1 and p1/3 (!)
         *
         *  Also, assuming that the compilation root is File 1:
         *
         * File1------.    File2------.   File3------.
         * [p1        .    . [p1]     .   . [p3]     .
         *   [p2      .    . [p2]     .   ------------
         *     [p3]   .    ------------
         *   ]        .
         * ]          .
         *            .
         * copy file2 .
         * copy file3 .
         *-------------
         *
         * Now File2.* can call File3.p1 as well as File1.* (!!!)
         *
         * IDE hints would require to know the compilation root:
         * A "copy file3" on file 2 is valid, unless compilation root is File1 (as p3 is already included in the bundled code).
         * Similarly, a File1.p3 call from file3.p3 is not valid, unless compilation root is File1.
         *
         * TODO: For Snowball, make it so that it has a more rational and expected scope:
         *   Programs know only other programs in their own, or their parents scope (including copied files).
         *   Transpilation will take care tor educe all copies to an array of uniques and copy all at the main.
         * */

        Collection<CobolProgram_> visiblePrograms = CobolUtil.visibleProgramsFor(call);

        String subprogramName = call.subprogramName();

        if (visiblePrograms.stream().noneMatch(p -> p.name().equals(subprogramName))) {
            error(call, "Subprogram not found in local or copied files.", holder);
        }
    }

    // TODO: Implement.
    private void missingArguments(CobolCall_ call, AnnotationHolder holder) {
        if (!call.isStatic()) {
            return;
        }
    }

    private void duplicateName(CobolProgramId_ programId, AnnotationHolder holder) {
        if (!CobolUtil.fileWillBeCompiled((CobolFile) programId.getContainingFile())) {
            return;
        }

        CobolProgram_ program = (CobolProgram_) programId.getParent().getParent();
        Collection<CobolProgram_> programsToBeCompiled = CobolUtil.mainFileFor(programId.getProject()).allTypedAndCopiedPrograms();

        // Remove self.
        programsToBeCompiled.removeIf(p -> p.equals(program));

        // Remove those with other name.
        programsToBeCompiled.removeIf(p -> !p.name().equals(program.name()));

        if (!programsToBeCompiled.isEmpty()) {
            for (CobolProgram_ visibleProgram : programsToBeCompiled) {
                error(programId, "Program with same name exists in " + visibleProgram.getContainingFile().getName(), holder);
            }
        }
    }

    private void nonMatchingEndProgramName(CobolEndProgram_ endProgram, AnnotationHolder holder) {
        CobolProgram_ program = (CobolProgram_) endProgram.getParent();

        String endProgramName = CobolUtil.textOfNullable(endProgram.getProgramName_().getIdentifierOrString_());

        if (!program.name().equals(endProgramName)) {
            error(endProgram, "End program is different than program id.", holder);
        }
    }

    private void truthConditionWithoutConditionalItem(CobolCondition_ condition, AnnotationHolder holder) {
        if (condition.getChildren().length > 1) return;
        if (!(condition.getFirstChild() instanceof CobolItemUsage_)) return;

        CobolItemDecl_ decl = ((CobolItemUsage_) condition.getFirstChild()).declaration();
        if (decl == null) return;

        if (decl instanceof CobolConditionalItemDecl_) return;

        error(condition, "Truth checks need a conditional item.", holder);
    }
}

class CorrectGroupFix extends BaseIntentionAction {
    CobolItemUsage_ element;
    String renameTo;

    CorrectGroupFix(CobolItemUsage_ element, String renameTo) {
        this.element = element;
        this.renameTo = renameTo;
    }

    @Override
    public @NotNull String getText() {
        return "Change to " + renameTo;
    }

    @Override
    public @NotNull String getFamilyName() {
        return getText();
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return true;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        PsiElement identifier = CobolElementFactory.createIdentifier(project, renameTo);
        this.element.getFirstChild().replace(identifier);
    }
}
