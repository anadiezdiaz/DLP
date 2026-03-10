package semantic;

import ast.Definition;
import ast.Program;
import ast.expressions.*;
import ast.statements.IfElseStatement;
import ast.statements.WhileStatement;

public class LValueVisitor<TP, TR> extends AbstractVisitor<Void, Void>{

    @Override
    public Void visit(Variable v, Void p) {
        return null;
    }

    @Override
    public Void visit(Arithmetic a, Void p) {
        a.getLeft().accept(this, p);
        a.getRight().accept(this, p);
        return null;
    }

    @Override
    public Void visit(Program pr, Void p) {
        for (Definition d : pr.getDefinitions()){
            d.accept(this, p);
        }
        return null;
    }
}

