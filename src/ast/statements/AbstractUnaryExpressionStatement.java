package ast.statements;

import ast.AbstractLocatable;
import ast.Expression;
import ast.Statement;
import ast.statements.AbstractExpressionStatement;

public class AbstractUnaryExpressionStatement extends AbstractExpressionStatement {
    Expression expression;

    public AbstractUnaryExpressionStatement(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
