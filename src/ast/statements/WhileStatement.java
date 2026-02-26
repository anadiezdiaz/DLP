package ast.statements;

import ast.Expression;
import ast.Statement;

import java.util.List;

public class WhileStatement extends AbstractUnaryExpressionStatement{
    List<Statement> body;

    public WhileStatement(int line, int column, Expression condition, List<Statement> body) {
        super(line, column, condition);
        this.body = body;
    }

    public List<Statement> getBody() {
        return this.body;
    }
}
