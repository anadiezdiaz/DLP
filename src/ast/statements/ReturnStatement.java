package ast.statements;

import ast.Expression;
import ast.Statement;

public class ReturnStatement extends AbstractUnaryExpressionStatement {

    public ReturnStatement(int line, int column, Expression expression) {
        super(line, column, expression);
    }
}
