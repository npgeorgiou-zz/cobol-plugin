package com.nikos.gnucobol_3_1;

import com.google.common.collect.Iterables;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.search.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.nikos.gnucobol_3_1.psi.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CobolUtil {
    // TODO: Find a better palce for this, maybe use a mixin for the psiUtil class eventually.
    public static String ALPHA = "ALPHA";
    public static String ALPHANUMERIC = "ALPHANUMERIC";
    public static String NUMERIC = "NUMERIC";

    // TODO: Have these values configurable.
    public static String COPY_PATH = "/home/nikos/Documents/Git/cobol/src/";
    public static String MAIN_FILE = "/home/nikos/Documents/Git/cobol/src/hello.cob";

    public static Collection<CobolFile> allCobolFiles(Project project) {
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(CobolFileType.INSTANCE, GlobalSearchScope.allScope(project));
        return virtualFiles.stream().map((VirtualFile file) -> (CobolFile) PsiManager.getInstance(project).findFile(file)).collect(Collectors.toList());
    }

    public static CobolFile cobolFileWithPath(String path, Project project) {
        Collection<CobolFile> cobolFiles = allCobolFiles(project);

        cobolFiles.removeIf((CobolFile file) -> !file.getVirtualFile().getPath().equals(path));

        if (cobolFiles.isEmpty()) {
            return null;
        }

        return (CobolFile) cobolFiles.toArray()[0];
    }

    public static CobolFile mainFileFor(Project project) {
        return cobolFileWithPath(CobolUtil.MAIN_FILE, project);
    }

    public static Collection<CobolProgram_> visibleProgramsFor(CobolCall_ call) {
        return CobolUtil.mainFileFor(call.getProject()).allTypedAndCopiedPrograms();
    }

    /*
     * Files that are linked via a copy statement.
     * */
    public static Collection<CobolFile> allFilesToBeCompiled(Project project) {
        return CobolUtil.traverseFilesAndCollect(
            mainFileFor(project),
            f -> {
                ArrayList<CobolFile> list = new ArrayList<>();
                list.add(f);
                return list;
            }
        );
    }

    public static boolean fileWillBeCompiled(CobolFile file) {
        Collection<CobolFile> files = CobolUtil.allFilesToBeCompiled(file.getProject());
        return files.contains(file);
    }

    public static <T> Collection<T> traverseFilesAndCollect(CobolFile file, Function<CobolFile, Collection<T>> function) {
        return traverseFilesAndCollect(file, function, new ArrayList<>());
    }

    private static <T> Collection<T> traverseFilesAndCollect(
        CobolFile file,
        Function<CobolFile, Collection<T>> function,
        Collection<CobolFile> copiedFiles
    ) {
        Collection<T> acc = function.apply(file);

        copiedFiles.add(file);

        // Copied programs of file.
        for (CobolCopy_ copy : file.copies()) {
            CobolFile copiedFile = copy.file();
            if (copiedFile != null && !copiedFiles.contains(copiedFile)) {
                acc.addAll(traverseFilesAndCollect(copiedFile, function, copiedFiles));
            }
        }

        return acc;
    }

    public static CobolItemUsage_ previousItemUsage(PsiElement element, boolean strict) {
        PsiElement prevLeaf = element;

        while ((prevLeaf = PsiTreeUtil.prevVisibleLeaf(prevLeaf)) != null) {

            if (!(prevLeaf.getNode().getElementType() == CobolTypes.IDENTIFIER && prevLeaf.getParent() instanceof CobolItemUsage_)) {
                if (strict) {
                    return null;
                }

                continue;
            }

            return (CobolItemUsage_) prevLeaf.getParent();
        }

        return null;
    }

    public static String textOfNullable(PsiElement element) {
        if (element == null) {
            return "";
        }

        return Util.unquote(element.getText());
    }

    public static String textOfNullable(ASTNode node) {
        if (node == null) {
            return "";
        }

        return Util.unquote(node.getText());
    }

    public static String toString(Collection<PsiElement> elements) {
        return elements.stream().map(it -> it.getText()).reduce("", (acc, it) -> acc + it);
    }

    public static Collection<PsiElement>  getSiblingsBetween(PsiElement start, PsiElement end) {
        Collection<PsiElement> list = new ArrayList<>();

        list.add(start);
        PsiElement next = start.getNextSibling();

        while (true) {
            if (next == null) {
                return null;
            }

            list.add(next);

            if (next.equals(end)) {
                break;
            }

            next = next.getNextSibling();
        }

        return list;
    }

    public static void replaceElementWithElements(PsiElement toReplace, Collection<PsiElement> elements) {
        PsiElement parent = toReplace.getParent();

        PsiElement anchor = toReplace;
        for (PsiElement newElement : elements) {
            parent.addAfter(newElement, anchor);

            anchor = newElement;

            if (!newElement.equals(Iterables.getLast(elements))) {
                parent.addAfter(CobolElementFactory.createWhitespace(toReplace.getProject()), anchor);
            }
        }

        parent.getNode().removeChild(toReplace.getNode());
    }
}
