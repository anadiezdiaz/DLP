package ast.statements;

import ast.Expression;
import ast.Statement;

import java.util.List;

public class LogStatement extends AbstractUnaryExpressionStatement{
    List<Expression> expressions;

    public LogStatement(int line, int column, Expression expression) {
        super(line, column, expression);
    }

    public List<Expression> getExpressions() {
        return this.expressions;
    }
}
