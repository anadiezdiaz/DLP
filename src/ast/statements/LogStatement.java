package ast.statements;

import ast.Expression;
import ast.Statement;

import java.util.List;

public class LogStatement extends AbstractExpressionStatement{
    List<Expression> expressions;

    public LogStatement(int line, int column, List<Expression> expressions) {
        super(line, column);
        this.expressions = expressions;
    }

    public List<Expression> getExpressions() {
        return this.expressions;
    }
}
