package ast.expressions;

import ast.AbstractLocatable;
import ast.Expression;

public class AbstractExpression extends AbstractLocatable implements Expression {
    public AbstractExpression(int line, int column) {
        super(line, column);
    }
}
