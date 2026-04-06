package ast.expressions;

import ast.Expression;
import ast.Type;
import semantic.Visitor;

public class Cast extends AbstractUnaryExpression{
    Type castType;

    public Cast(int line, int column, Expression expression, Type castType) {
        super(line, column, expression);
        this.castType = castType;
    }

    public Type getCastType() {
        return this.castType;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
