package ast.statements;

import ast.AbstractLocatable;
import ast.Expression;
import ast.Statement;

public class AbstractExpressionStatement extends AbstractLocatable implements Statement {

    public AbstractExpressionStatement(int line, int column) {
        super(line, column);
    }
}
