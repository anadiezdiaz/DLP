package ast.expressions;

import ast.Expression;

public class AbstractBinaryExpression extends AbstractExpression{
    Expression left, right;

    public AbstractBinaryExpression(int line, int column, Expression left, Expression right) {
        super(line, column);
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return this.left;
    }

    public Expression getRight() {
        return this.right;
    }
}
