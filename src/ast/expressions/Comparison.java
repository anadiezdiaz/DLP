package ast.expressions;

import ast.Expression;
import semantic.Visitor;

public class Comparison extends AbstractBinaryOperatorExpression{
    public Comparison(int line, int column, Expression left, Expression right, String operator) {
        super(line, column, left, right, operator);
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return "Comparison [left=" + getLeft() + ", operator=" + getOperator() + ", right=" + getRight()
                + ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
