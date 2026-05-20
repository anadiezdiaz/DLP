package ast.expressions;

import ast.Expression;
import semantic.Visitor;

public class Square extends AbstractUnaryExpression {

    public Square(int line, int column, Expression expression) {
        super(line, column, expression);
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return "Square [expression=" + getExpression()
                + ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
