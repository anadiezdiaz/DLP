package ast.statements;

import ast.AbstractLocatable;
import ast.Statement;
import semantic.Visitor;

public class BreakStatement extends AbstractLocatable implements Statement {

    public BreakStatement(int line, int column) {
        super(line, column);
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return "BreakStatement [line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
