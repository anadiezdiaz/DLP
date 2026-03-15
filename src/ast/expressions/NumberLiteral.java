package ast.expressions;

import ast.Expression;
import semantic.Visitor;

public class NumberLiteral extends AbstractExpression {
    double value;

    public NumberLiteral(int line, int column, double value) {
        super(line, column);
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
