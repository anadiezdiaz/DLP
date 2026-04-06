package ast.expressions;

import ast.AbstractLocatable;
import ast.Expression;
import ast.Type;

public abstract class AbstractExpression extends AbstractLocatable implements Expression {
    boolean lvalue;
    Type type;

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

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

}
