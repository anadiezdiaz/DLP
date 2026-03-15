package ast.expressions;

import ast.Expression;
import semantic.Visitor;

public class ArrayAccess extends AbstractBinaryExpression{
    public ArrayAccess(int line, int column, Expression left, Expression right) {
        super(line, column, left, right);
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
