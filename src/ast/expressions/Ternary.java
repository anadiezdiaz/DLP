package ast.expressions;

import ast.Expression;
import semantic.Visitor;

public class Ternary extends AbstractExpression{
    Expression e1;
    Expression e2;
    Expression e3;

    public Ternary(int line, int column, Expression e1, Expression e2, Expression e3){
        super(line, column);
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;

    }

    public Expression getE1() {
        return e1;
    }

    public Expression getE2() {
        return e2;
    }

    public Expression getE3() {
        return e3;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return "Ternary [e1=" + getE1() + ", e2=" + getE2() + ", e3=" + getE3()
                + ", line" + getLine() + ", column" + getColumn() + "]";
    }
}
