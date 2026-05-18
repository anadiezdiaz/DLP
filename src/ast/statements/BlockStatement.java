package ast.statements;

import ast.AbstractLocatable;
import ast.Statement;
import semantic.Visitor;

import java.util.List;

public class BlockStatement extends AbstractLocatable implements Statement {
    private List<Statement> statements;

    public BlockStatement(int line, int column, List<Statement> statements) {
        super(line, column);
        this.statements = statements;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return "BlockStatement [statements=" + statements
                + ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
