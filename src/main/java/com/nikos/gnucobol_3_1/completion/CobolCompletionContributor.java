package com.nikos.gnucobol_3_1.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PatternCondition;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import com.nikos.gnucobol_3_1.CobolUtil;
import com.nikos.gnucobol_3_1.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class CobolCompletionContributor extends CompletionContributor {
    private class Node {
        PsiElementPattern.Capture<PsiElement> pattern;
        CobolCompletionProvider provider;
        Node[] children;
        Node parent;

        public Node(CobolCompletionProvider provider) {
            this(null, provider);
        }

        public Node(PsiElementPattern.Capture<PsiElement> pattern, CobolCompletionProvider provider) {
            this(pattern, provider, new Node[]{});
        }

        public Node(PsiElementPattern.Capture<PsiElement> pattern, CobolCompletionProvider provider, Node... children) {
            this.pattern = pattern;
            this.provider = provider;
            this.children = children;

            if (children != null) {
                for (Node c : children) {
                    c.parent = this;
                }
            }
        }

        public boolean isRoot() {
            return this.parent == null;
        }

        public boolean isRepeatingBranchRoot() {
            return this instanceof RepeatingBranchRootNode;
        }

        public boolean isInRepeatingBranch() {
            return this.repeatingBranchRoot() != null && !this.isRepeatingBranchRoot();
        }

        public RepeatingBranchRootNode repeatingBranchRoot() {
            if (this.isRepeatingBranchRoot()) return (RepeatingBranchRootNode) this;

            if (this.isRoot()) return null;

            return this.parent.repeatingBranchRoot();
        }

        public RootNode root() {
            if (this.isRoot()) return (RootNode) this;

            return this.parent.root();
        }

        public Collection<Node> leaves() {
            return leaves(new ArrayList<>());
        }

        public Collection<Node> leaves(Collection<Node> acc) {
            for (Node child : children) {
                child.leaves(acc);
            }

            if (children.length == 0) {
                acc.add(this);
            }

            return acc;
        }

        public PsiElementPattern[] patternsTill(Node node) {
            return this.patternsTill(node, new ArrayList<>());
        }

        public PsiElementPattern[] patternsTill(Node node, Collection<ElementPattern> acc) {
            acc.add(this.pattern);

            if (this.equals(node)) {
                Collections.reverse((List<?>) acc);
                return acc.toArray(new PsiElementPattern[acc.size()]);
            }

            return this.parent.patternsTill(node, acc);
        }
    }

    private class RootNode extends Node {
        Class statement;

        public RootNode(Class statement, PsiElementPattern.Capture<PsiElement> pattern, Node... children) {
            super(pattern, null, children);
            this.statement = statement;
        }
    }

    private class ConditionalNode extends Node {
        PsiElementPattern.Capture<PsiElement> condition;

        public ConditionalNode(PsiElementPattern.Capture<PsiElement> pattern, CobolCompletionProvider provider, PsiElementPattern.Capture<PsiElement> condition, Node... children) {
            super(pattern, provider, children);
            this.condition = condition;
        }
    }

    private class RepeatingNode extends Node {
        PsiElementPattern.Capture<PsiElement> condition;

        public RepeatingNode(PsiElementPattern.Capture<PsiElement> pattern, PsiElementPattern.Capture<PsiElement> condition, CobolCompletionProvider provider) {
            this(pattern, condition, provider, new Node[]{});
        }

        public RepeatingNode(PsiElementPattern.Capture<PsiElement> pattern, PsiElementPattern.Capture<PsiElement> condition, CobolCompletionProvider provider, Node... children) {
            super(pattern, provider, children);
            this.condition = condition;
        }
    }

    private class RepeatingBranchRootNode extends Node {
        public RepeatingBranchRootNode(PsiElementPattern.Capture<PsiElement> pattern, CobolCompletionProvider provider, Node... children) {
            super(pattern, provider, children);
        }
    }

    protected void extendFor(Node node) {
        for (Node child : node.children) {
            extendFor(child);
        }

        if (node instanceof RootNode) return;

        if (node instanceof RepeatingNode) {
            PsiElementPattern repeatAfterPattern;
            RepeatingNode repeatNode = (RepeatingNode) node;
            if (repeatNode.condition != null) {
                repeatAfterPattern = repeatNode.condition;
            } else {
                PsiElementPattern[] patterns = node.patternsTill(node.root());
                repeatAfterPattern = isAfterPatterns(patterns);
            }

            extend(CompletionType.BASIC, repeatAfterPattern, node.provider);
        }

        if (node instanceof RepeatingBranchRootNode) {
            for (Node leaf : node.leaves()) {
                PsiElementPattern[] patterns = leaf.patternsTill(node);
                extend(CompletionType.BASIC, isAfterPatterns(patterns), node.provider);
            }
        }

        if (node.isInRepeatingBranch()) {
            PsiElementPattern[] patterns = node.parent.patternsTill(node.repeatingBranchRoot());
            patterns[0] = (PsiElementPattern) patterns[0].inside(node.root().statement);
            extend(CompletionType.BASIC, isAfterPatterns(patterns), node.provider);

            return;
        }

        if (node instanceof ConditionalNode) {
            node.parent.pattern = node.parent.pattern.and(((ConditionalNode) node).condition);
        }

        PsiElementPattern[] patterns = node.parent.patternsTill(node.root());
        extend(CompletionType.BASIC, isAfterPatterns(patterns), node.provider);
    }

    private PsiElementPattern<PsiElement, PsiElementPattern.Capture<PsiElement>> isAfterPatterns(PsiElementPattern... patterns) {
        return psiElement().with(new AfterPatterns(patterns));
    }

    public CobolCompletionContributor() {
        extend(CompletionType.BASIC, atStatementBeginning(), new Keywords(
            "accept", "call", "display", "initialize", "move",
            "add", "subtract", "multiply", "divide", "compute",
            "set")
        );

        extend(CompletionType.BASIC, atFileLevel(), new Combine(new ProgramSeed(), new Keywords("copy")));
        extend(CompletionType.BASIC, afterItemUsageThatHasParent(), new Keywords("of"));
        extend(CompletionType.BASIC, afterOfKeyword(), new ItemParent());
        extend(CompletionType.BASIC, psiElement().afterLeaf(psiElement(CobolTypes.COPY)), new CopyPaths());
        extend(CompletionType.BASIC, atIdentificationDivision(), new IdentificationDivisionParagraphs());
        extend(CompletionType.BASIC, atEndProgramName(), new ProgramName());
        extend(CompletionType.BASIC, atProcedureDivisionLevel(), new ProgramSeed());

        Node initializeStatement = new RootNode(CobolInitialize_.class,
            psiElement(CobolTypes.INITIALIZE),
            new RepeatingNode(
                listOfItems(),
                null,
                new NonConditionalProgramItems(),
                new Node(
                    psiElement(CobolTypes.REPLACING), new Keywords("replacing"),
                    new RepeatingBranchRootNode(
                        any(CobolTypes.ALPHABETIC, CobolTypes.ALPHANUMERIC, CobolTypes.NUMERIC), new Keywords("alphabetic", "alphanumeric", "numeric"),
                        new Node(
                            psiElement(CobolTypes.BY), new Keywords("by"),
                            new Node(
                                listOfItemsOrLiterals(),
                                new NonConditionalProgramItems()))))));

        Node acceptStatement = new RootNode(CobolAccept_.class,
            psiElement(CobolTypes.ACCEPT),
            new Node(
                item(), new NonConditionalProgramItems(),
                new Node(
                    psiElement(CobolTypes.FROM), new Keywords("from"),
                    new Node(
                        psiElement(CobolTypes.DATE), new Keywords(true, "date"),
                        new Node(
                            new Keywords(false, "yyyymmdd"))),
                    new Node(
                        psiElement(CobolTypes.DAY), new Keywords(true, "day"),
                        new Node(
                            new Keywords(false, "yyyyddd"))),
                    new Node(
                        new Keywords(false, "day-of-week", "time")))));

        Node callStatement = new RootNode(CobolCall_.class,
            psiElement(CobolTypes.CALL),
            new Node(
                new ProgramNames()));

        Node displayStatement = new RootNode(CobolDisplay_.class,
            psiElement(CobolTypes.DISPLAY),
            new RepeatingNode(
                listOfItemsOrLiterals(),
                null,
                new NonConditionalProgramItems()));

        Node moveStatement = new RootNode(CobolMove_.class,
            psiElement(CobolTypes.MOVE),
            new Node(
                any(CobolTypes.CORR, CobolTypes.CORRESPONDING), new Keywords("corresponding"),
                new Node(
                    itemOrLiteral(), new GroupItems(),
                    new Node(
                        psiElement(CobolTypes.TO), new Keywords("to"),
                        new Node(
                            itemOrLiteral(), new GroupItems())))),
            new Node(
                itemOrLiteral(), new NonConditionalProgramItems(),
                new Node(
                    psiElement(CobolTypes.TO), new Keywords("to"),
                    new RepeatingNode(
                        any(CobolTypes.IDENTIFIER, CobolTypes.OF, CobolTypes.COMMA), null, new NonConditionalProgramItems()))));

        Node addStatement = new RootNode(CobolAdd_.class,
            psiElement(CobolTypes.ADD),
            new Node(
                any(CobolTypes.CORR, CobolTypes.CORRESPONDING), new Keywords("corresponding"),
                new Node(
                    item(), new GroupItems(),
                    new Node(
                        psiElement(CobolTypes.TO), new Keywords("to"),
                        new Node(
                            new GroupItems())))),
            new RepeatingNode(
                listOfItemsOrLiterals(), null, new NumericItems(),
                new Node(
                    psiElement(CobolTypes.GIVING), new Keywords("giving"),
                    new RepeatingNode(
                        listOfItems(), null, new NumericItems())),
                new Node(
                    psiElement(CobolTypes.TO), new Keywords("to"),
                    new RepeatingNode(
                        listOfItems(), null, new NumericItems()),
                    new Node(
                        item(),
                        new NumericItems(),
                        new ConditionalNode(
                            psiElement(CobolTypes.GIVING), new Keywords("giving"),
                            psiElement().andNot(isInListOfItems()),
                            new RepeatingNode(
                                listOfItems(), null, new NumericItems()))))));

        Node subtractStatement = new RootNode(CobolSubtract_.class,
            psiElement(CobolTypes.SUBTRACT),
            new Node(
                any(CobolTypes.CORR, CobolTypes.CORRESPONDING), new Keywords("corresponding"),
                new Node(
                    item(), new GroupItems(),
                    new Node(
                        psiElement(CobolTypes.FROM), new Keywords("from"),
                        new Node(
                            new GroupItems())))),
            new RepeatingNode(
                listOfItemsOrLiterals(), null, new NumericItems(),
                new Node(
                    psiElement(CobolTypes.FROM), new Keywords("from"),
                    new RepeatingNode(
                        listOfItems(), null, new NumericItems()),
                    new Node(
                        item(), new NumericItems(),
                        new ConditionalNode(
                            psiElement(CobolTypes.GIVING), new Keywords("giving"),
                            psiElement().andNot(isInListOfItems()),
                            new RepeatingNode(
                                listOfItems(), null, new NumericItems()))))));

        Node multiplyStatement = new RootNode(CobolMultiply_.class,
            psiElement(CobolTypes.MULTIPLY),
            new Node(
                itemOrLiteral(), new NumericItems(),
                new Node(
                    psiElement(CobolTypes.BY), new Keywords("by"),
                    new RepeatingNode(
                        listOfItems(), null, new NumericItems()),
                    new Node(
                        item(), new NumericItems(),
                        new ConditionalNode(
                            psiElement(CobolTypes.GIVING), new Keywords("giving"),
                            psiElement().andNot(isInListOfItems()),
                            new RepeatingNode(
                                listOfItems(), null, new NumericItems()))))));

        Node divideStatement = new RootNode(CobolDivide_.class,
            psiElement(CobolTypes.DIVIDE),
            new Node(
                itemOrNumber(), new NumericItems(),
                new Node(
                    psiElement(CobolTypes.INTO), new Keywords("into"),
                    new RepeatingNode(
                        listOfItems(), null, new NumericItems()),
                    new Node(
                        item(), new NumericItems(),
                        new ConditionalNode(
                            psiElement(CobolTypes.GIVING), new Keywords("giving"),
                            psiElement().andNot(isInListOfItems()),
                            new RepeatingNode(
                                listOfItems(), null, new NumericItems()),
                            new Node(
                                item(), new NumericItems(),
                                new ConditionalNode(
                                    psiElement(CobolTypes.REMAINDER), new Keywords("remainder"),
                                    psiElement().andNot(isInListOfItems()),
                                    new Node(
                                        item(), new NumericItems())))))),
                new Node(
                    psiElement(CobolTypes.BY), new Keywords("by"),
                    new Node(
                        itemOrNumber(), new NumericItems(),
                        new Node(
                            psiElement(CobolTypes.GIVING), new Keywords("giving"),
                            new RepeatingNode(
                                listOfItems(), null, new NumericItems()),
                            new Node(
                                item(), new NumericItems(),
                                new ConditionalNode(
                                    psiElement(CobolTypes.REMAINDER), new Keywords("remainder"),
                                    psiElement().andNot(isInListOfItems()),
                                    new Node(
                                        item(), new NumericItems()))))))));

        Node computeStatement = new RootNode(CobolCompute_.class,
            psiElement(CobolTypes.COMPUTE),
            new RepeatingNode(
                listOfItems(),
                null,
                new NonConditionalProgramItems(),
                new Node(
                    psiElement(CobolTypes.EQUALS_OP), new Keywords("="),
                    new RepeatingNode(
                        mathExpression(),
                        null,
                        new NonConditionalProgramItems()))));

        Node setStatement = new RootNode(CobolSet_.class,
            psiElement(CobolTypes.SET),
            new RepeatingNode(
                listOfItems(), null, new ConditionalProgramItems(),
                new Node(
                    psiElement(CobolTypes.TO), new Keywords(true, "to"),
                    new Node(
                        psiElement(CobolTypes.TRUE), new Keywords(true, "true")))));

        extendFor(acceptStatement);
        extendFor(displayStatement);
        extendFor(callStatement);
        extendFor(moveStatement);
        extendFor(initializeStatement);
        extendFor(addStatement);
        extendFor(subtractStatement);
        extendFor(multiplyStatement);
        extendFor(divideStatement);
        extendFor(computeStatement);
        extendFor(setStatement);
    }
    
    private PsiElementPattern.Capture<PsiElement> afterItemUsage() {
        // TODO: Dont know why I cant just do psiElement().afterSibling(psiElement(CobolItemUsage_.class));
        return psiElement().afterLeaf(psiElement(CobolTypes.IDENTIFIER).withParent(psiElement(CobolItemUsage_.class)));
    }

    private ElementPattern<PsiElement> afterItemUsageThatHasParent() {
        return psiElement().and(afterItemUsage()).and(previousItemHasParent());
    }

    private ElementPattern<PsiElement> afterOfKeyword() {
        return afterAny(CobolTypes.OF, CobolTypes.IN);
    }

    private ElementPattern<PsiElement> atFileLevel() {
        // I don't know why I cant just us withParent(). PSI viewer doesn't show a intermediate node.
        return psiElement().withElementType(CobolTypes.IDENTIFIER).withSuperParent(2, CobolFile.class);
    }

    private ElementPattern<PsiElement> atIdentificationDivision() {
        return psiElement().withElementType(CobolTypes.IDENTIFIER).inside(CobolIdentificationDivision_.class);
    }

    private ElementPattern<PsiElement> atStatementBeginning() {
        return psiElement()
                   .withElementType(CobolTypes.IDENTIFIER)
                   .inside(CobolProcedureDivision_.class)
                   .afterLeaf(psiElement(CobolTypes.DOT))
                   .and(psiElementIsOnLineBeginning());
    }

    private ElementPattern<PsiElement> atProcedureDivisionLevel() {
        return psiElement().withSuperParent(2, CobolProcedureDivision_.class).and(atStatementBeginning());
    }

    private ElementPattern<PsiElement> atEndProgramName() {
        return psiElement().inside(CobolEndProgram_.class).andOr(
            psiElement(CobolTypes.IDENTIFIER),
            psiElement(CobolTypes.STRING)
        );
    }

    private PsiElementPattern<PsiElement, PsiElementPattern.Capture<PsiElement>> psiElementIsOnLineBeginning() {
        return psiElement().with(new OnLineBeginning(""));
    }

    private PsiElementPattern<PsiElement, PsiElementPattern.Capture<PsiElement>> isInsideIfScope() {
        return psiElement().with(new isInsideIfScope());
    }

    private PsiElementPattern.Capture<PsiElement> afterAny(IElementType... types) {
        Collection<ElementPattern> patterns = Stream.of(types)
                                                  .map(it -> psiElement(it))
                                                  .collect(Collectors.toList());
        // Ahh, java...
        ElementPattern[] array = patterns.toArray(new ElementPattern[patterns.size()]);

        return psiElement().afterLeaf(
            psiElement().andOr(array)
        );
    }

    private PsiElementPattern.Capture<PsiElement> any(IElementType... types) {
        Collection<ElementPattern> patterns = Stream.of(types)
                                                  .map(it -> psiElement(it))
                                                  .collect(Collectors.toList());
        // Ahh, java...
        ElementPattern[] array = patterns.toArray(new ElementPattern[patterns.size()]);

        return psiElement().andOr(array);
    }

    private ElementPattern<PsiElement> previousItemHasParent() {
        return psiElement().with(new PreviousItemHasParent(""));
    }

    private ElementPattern<PsiElement> isInListOfItems() {
        return psiElement().with(new InItemList(""));
    }

    private PsiElementPattern.Capture<PsiElement> listOfItemsOrLiterals() {
        return psiElement().andOr(
            psiElement(CobolTypes.COMMA),
            itemOrLiteral()
        );
    }

    private PsiElementPattern.Capture<PsiElement> listOfItems() {
        return psiElement().andOr(
            psiElement(CobolTypes.COMMA),
            item()
        );
    }

    private PsiElementPattern.Capture<PsiElement> mathExpression() {
        return psiElement().andOr(
            mathOperators(),
            itemOrLiteral(),
            psiElement(CobolTypes.PAREN_OPEN),
            psiElement(CobolTypes.PAREN_CLOSE)
        );
    }

    private PsiElementPattern.Capture<PsiElement> mathOperators() {
        return psiElement().andOr(
            psiElement(CobolTypes.ADD_OP),
            psiElement(CobolTypes.SUBTRACT_OP),
            psiElement(CobolTypes.MULTIPLY_OP),
            psiElement(CobolTypes.DIVIDE_OP),
            psiElement(CobolTypes.POWER_OP)
        );
    }

    private PsiElementPattern.Capture<PsiElement> itemOrLiteral() {
        return psiElement().andOr(
            item(),
            literal()
        );
    }

    private PsiElementPattern.Capture<PsiElement> itemOrNumber() {
        return psiElement().andOr(
            item(),
            number()
        );
    }

    private PsiElementPattern.Capture<PsiElement> item() {
        return any(CobolTypes.IDENTIFIER, CobolTypes.OF);
    }

    private PsiElementPattern.Capture<PsiElement> literal() {
        return psiElement().andOr(
            psiElement(CobolTypes.STRING),
            number()
        );
    }

    private PsiElementPattern.Capture<PsiElement> number() {
        return any(CobolTypes.INTEGER, CobolTypes.FLOAT);
    }
}

class OnLineBeginning extends PatternCondition<PsiElement> {

    public OnLineBeginning(String debugMethodName) {
        super("On line beginning");
    }

    @Override
    public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
        PsiElement prevLeaf = element;

        while ((prevLeaf = PsiTreeUtil.prevLeaf(prevLeaf)) != null) {

            if (prevLeaf instanceof PsiWhiteSpace) {
                if (prevLeaf.textContains('\n')) {
                    return true;
                }

                continue;
            }

            break;
        }

        return false;
    }
}

class StatementAlreadyHas extends PatternCondition<PsiElement> {
    Collection<IElementType> types = new ArrayList<>();

    public StatementAlreadyHas(IElementType type) {
        super("Statement already has Element of that type");
        this.types.add(type);
    }

    public StatementAlreadyHas(IElementType... types) {
        super("Statement already has Element of that type");
        this.types = new ArrayList<>(Arrays.asList(types));
    }

    @Override
    public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
        // There is probably a better way to do that "is it the only of that type in the statement" check...
        CobolStatement_ statementOfTyped = PsiTreeUtil.getParentOfType(element, CobolStatement_.class);

        if (statementOfTyped == null) return false;

        PsiElement prevLeaf = element;
        while ((prevLeaf = PsiTreeUtil.prevLeaf(prevLeaf)) != null) {
            // If out of statement boundaries, exit.
            CobolStatement_ statementOfLeaf = PsiTreeUtil.getParentOfType(prevLeaf, CobolStatement_.class);

            if (!statementOfTyped.equals(statementOfLeaf)) {
                break;
            }

            if (types.contains(prevLeaf.getNode().getElementType())) {
                return true;
            }
        }

        return false;
    }
}

class isInsideIfScope extends PatternCondition<PsiElement> {
    public isInsideIfScope() {
        super("Statement already has Element of that type");
    }

    @Override
    public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
        PsiElement prevLeaf = element;
        while ((prevLeaf = PsiTreeUtil.prevLeaf(prevLeaf)) != null) {

            if (prevLeaf.getNode().getElementType() == CobolTypes.DOT) {
                return false;
            }

            if (prevLeaf.getNode().getElementType() == CobolTypes.IF) {
                return true;
            }
        }

        return false;
    }
}

class PreviousItemHasParent extends PatternCondition<PsiElement> {

    public PreviousItemHasParent(String debugMethodName) {
        super("Previous item has parent");
    }

    @Override
    public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
        CobolItemUsage_ previousItemUsage = CobolUtil.previousItemUsage(element, true);
        CobolItemDecl_ itemDecl = previousItemUsage.declaration();

        if (itemDecl == null) {
            return false;
        }

        if (itemDecl.parent() != null) {
            return true;
        }

        return false;
    }
}

class InItemList extends PatternCondition<PsiElement> {

    public InItemList(String debugMethodName) {
        super("In item list");
    }

    @Override
    public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
        PsiElement prevLeaf = element;

        int itemsInRow = 0;

        while (prevLeaf != null) {
            if (
                prevLeaf.getNode().getElementType() != CobolTypes.IDENTIFIER
                    && prevLeaf.getNode().getElementType() != CobolTypes.OF
                    && prevLeaf.getNode().getElementType() != CobolTypes.COMMA
            ) {
                return false;
            }

            if (prevLeaf.getNode().getElementType() == CobolTypes.IDENTIFIER && prevLeaf.getParent() instanceof CobolItemUsage_) {
                itemsInRow++;
            }

            if (prevLeaf.getNode().getElementType() == CobolTypes.OF) {
                itemsInRow--;
            }

            if (itemsInRow == 2) {
                return true;
            }

            prevLeaf = PsiTreeUtil.prevVisibleLeaf(prevLeaf);
        }

        return false;
    }
}

class AfterPatterns extends PatternCondition<PsiElement> {
    ArrayList<PsiElementPattern> patterns;

    public AfterPatterns(PsiElementPattern... patterns) {
        super("Is after patterns");
        this.patterns = new ArrayList<>(Arrays.asList(patterns));
    }

    @Override
    public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
        int indexOfCurrentPattern = patterns.size() - 1;
        PsiElementPattern currentPattern = patterns.get(indexOfCurrentPattern);

        PsiElement currentElement = PsiTreeUtil.prevVisibleLeaf(element);

        if (!currentPattern.accepts(currentElement)) {
            return false;
        }

        PsiElement statementOfTyped = boundaryForMatching(currentElement);

        while ((currentElement = PsiTreeUtil.prevVisibleLeaf(currentElement)) != null) {
            PsiElement statementOfLeaf = boundaryForMatching(currentElement);
            if (statementOfLeaf == null || !statementOfLeaf.equals(statementOfTyped)) {
                break;
            }

            if (indexOfCurrentPattern == 0) {
                break;
            }

            if (currentPattern.accepts(currentElement)) {
                continue;
            }

            indexOfCurrentPattern--;
            currentPattern = patterns.get(indexOfCurrentPattern);

            if (currentPattern.accepts(currentElement)) {
                continue;
            } else {
                return false;
            }
        }

        return indexOfCurrentPattern == 0;
    }

    public PsiElement boundaryForMatching(PsiElement element) {
        CobolStatement_ statement = PsiTreeUtil.getParentOfType(element, CobolStatement_.class);
        return statement;
    }
}
