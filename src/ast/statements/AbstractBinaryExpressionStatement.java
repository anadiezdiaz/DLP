package ast.statements;

import ast.Expression;

public class AbstractBinaryExpressionStatement extends AbstractExpressionStatement{
    Expression left, right;

    public AbstractBinaryExpressionStatement(int line, int column, Expression left, Expression right) {
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
