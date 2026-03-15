package ast.expressions;

import ast.Expression;
import semantic.Visitor;

public class UnaryMinus extends AbstractUnaryExpression{
    public UnaryMinus(int line, int column, Expression expression) {
        super(line, column, expression);
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
