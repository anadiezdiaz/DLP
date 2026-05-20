package ast.expressions;

import ast.Expression;
import semantic.Visitor;

public class Power extends AbstractUnaryExpression {
    private int exponent;

    public Power(int line, int column, Expression expression, int exponent) {
        super(line, column, expression);
        this.exponent = exponent;
    }

    public int getExponent() {
        return exponent;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
