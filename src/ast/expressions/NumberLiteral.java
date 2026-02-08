package ast.expressions;

import ast.Expression;

public class NumberLiteral extends AbstractExpression {
    double value;

    public NumberLiteral(int line, int column, double value) {
        super(line, column);
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }
}
