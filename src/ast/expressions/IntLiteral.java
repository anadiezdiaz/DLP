package ast.expressions;

import ast.Expression;

public class IntLiteral extends AbstractExpression {
    int value;

    public IntLiteral(int line, int column, int value) {
        super(line, column);
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
