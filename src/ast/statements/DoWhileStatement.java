package ast.statements;

import ast.Expression;
import ast.Statement;
import semantic.Visitor;
import java.util.List;

public class DoWhileStatement extends AbstractUnaryExpressionStatement{
    List<Statement> body;

    public DoWhileStatement(int line, int column, Expression condition, List<Statement> body) {
        super(line, column, condition);
        this.body = body;
    }

    public List<Statement> getBody() {
        return this.body;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return "DoWhileStatement [body=" + getBody() + ", condition" + getExpression()
                + ", line" + getLine() + ", column" + getColumn() + "]";
    }
}
