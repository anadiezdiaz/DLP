package ast.statements;

import ast.Expression;
import ast.Statement;

import java.util.List;

public class InputStatement extends AbstractExpressionStatement {
    List<Expression> expressions;

    public InputStatement(int line, int column, List<Expression> expressions) {
        super(line, column);
        this.expressions = expressions;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
