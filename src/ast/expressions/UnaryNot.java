package ast.expressions;

import ast.Expression;

public class UnaryNot extends AbstractUnaryExpression{

    public UnaryNot(int line, int column, Expression expression) {
        super(line, column, expression);
    }
}
