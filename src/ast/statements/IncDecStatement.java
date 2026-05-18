package ast.statements;

import ast.Expression;
import semantic.Visitor;

public class IncDecStatement extends AbstractUnaryExpressionStatement{
    String operator;

    public IncDecStatement(int line, int column, Expression expression, String operator){
        super(line, column, expression);
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return "IncDecStatement [expression=" + getExpression() + ", operator=" + getOperator()
                + ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
