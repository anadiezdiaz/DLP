package ast.statements;

import ast.Expression;
import ast.Statement;
import semantic.Visitor;

import java.util.List;

public class SwitchStatement extends AbstractUnaryExpressionStatement{
    private List<SwitchCase> cases;
    private List<Statement> defaultBody;

    public SwitchStatement(int line, int column, Expression expression, List<SwitchCase> cases, List<Statement> defaultBody) {
        super(line, column, expression);
        this.cases = cases;
        this.defaultBody = defaultBody;
    }

    public List<SwitchCase> getCases() {
        return cases;
    }

    public List<Statement> getDefaultBody() {
        return defaultBody;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return "SwitchStatement [cases=" + getCases() + ", defaultBody=" + getDefaultBody() + ", expression" + getExpression()
                + ", line" + getLine() + ", column" + getColumn() + "]";
    }
}
