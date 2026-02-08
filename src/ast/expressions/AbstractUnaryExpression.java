package ast.expressions;

import ast.Expression;

public class AbstractUnaryExpression extends AbstractExpression{
    Expression expression;

    public AbstractUnaryExpression(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }
}
