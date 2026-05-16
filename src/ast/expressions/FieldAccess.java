package ast.expressions;

import ast.Expression;
import semantic.Visitor;

public class FieldAccess extends AbstractUnaryExpression{
    String field;
    int offset;

    public FieldAccess(int line, int column, Expression left, String field) {
        super(line, column, left);
        this.field = field;
    }

    public String getField(){
        return this.field;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return "FieldAccess [field=" + getField() + ", expression" + getExpression()
                + ", line= " + getLine() + ", column= " + getColumn() + "]";
    }
}
