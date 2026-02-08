package ast.statements;

import ast.Expression;
import ast.Statement;

import java.util.List;

public class InputStatement extends AbstractUnaryExpressionStatement {
    List<Expression> expressions;

    public InputStatement(int line, int column, Expression expression) {
        super(line, column, expression);
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
