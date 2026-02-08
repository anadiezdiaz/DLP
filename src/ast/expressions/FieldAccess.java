package ast.expressions;

import ast.Expression;

public class FieldAccess extends AbstractUnaryExpression{
    String field;

    public FieldAccess(int line, int column, Expression left, String field) {
        super(line, column, left);
        this.field = field;
    }

    public String getField(){
        return this.field;
    }
}
