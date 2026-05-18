package ast.statements;

import ast.Expression;
import semantic.Visitor;

public class LogicAssignment extends AbstractBinaryExpressionStatement{
    String operator;

    public LogicAssignment(int line, int column, Expression left, Expression right, String operator){
        super(line, column, left, right);
        this.operator = operator;
    }
    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "LogicAssignment [left=" + getLeft() + ", right=" + getRight() + ", operator=" + getOperator()
                + ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
