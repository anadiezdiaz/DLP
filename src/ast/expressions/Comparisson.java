package ast.expressions;

import ast.Expression;
import semantic.Visitor;

public class Comparisson extends AbstractBinaryOperatorExpression{
    public Comparisson(int line, int column, Expression left, Expression right, String operator) {
        super(line, column, left, right, operator);
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
