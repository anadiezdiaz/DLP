package ast.statements;

import ast.Expression;
import ast.Statement;

import java.util.List;

public class SwitchCase {
    private Expression expression;
    private List<Statement> body;

    public SwitchCase(Expression expression, List<Statement> body) {
        this.expression = expression;
        this.body = body;
    }

    public Expression getExpression() {
        return expression;
    }

    public List<Statement> getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "SwitchCase [expression=" + getExpression() + ", body=" + getBody() + "]";
    }
}
