package ast.statements;

import ast.Expression;
import ast.Statement;

import java.util.List;

public class WhileStatement extends AbstractUnaryExpressionStatement{
    List<Statement> body;

    public WhileStatement(int line, int column, Expression condition) {
        super(line, column, condition);
    }

    public List<Statement> getBody() {
        return this.body;
    }
}
