package ast.expressions;

import semantic.Visitor;

public class BooleanLiteral extends AbstractExpression {
    private boolean value;

    public BooleanLiteral(int line, int column, boolean value) {
        super(line, column);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public int getIntValue() {
        return value ? 1 : 0;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
