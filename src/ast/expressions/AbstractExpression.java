package ast.expressions;

import ast.AbstractLocatable;
import ast.Expression;

public abstract class AbstractExpression extends AbstractLocatable implements Expression {
    boolean lvalue;

    public AbstractExpression(int line, int column) {
        super(line, column);
    }

    @Override
    public boolean getLValue() {
        return lvalue;
    }

    @Override
    public void setLValue(boolean lvalue){
        this.lvalue = lvalue;
    }

}
