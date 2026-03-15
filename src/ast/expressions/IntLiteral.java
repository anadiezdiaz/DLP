package ast.expressions;

import ast.Expression;
import semantic.Visitor;

public class IntLiteral extends AbstractExpression {
    int value;

    public IntLiteral(int line, int column, int value) {
        super(line, column);
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
