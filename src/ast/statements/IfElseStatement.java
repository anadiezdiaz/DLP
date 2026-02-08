package ast.statements;

import ast.Expression;

import ast.Statement;

import java.util.List;

public class IfElseStatement extends AbstractUnaryExpressionStatement{
    List<Statement> ifBody;
    List<Statement> elseBody;

    public IfElseStatement(int line, int column, Expression condition) {
        super(line, column, condition);
    }

    public List<Statement> getIfBody() {
        return this.ifBody;
    }

    public List<Statement> getElseBody() {
        return this.elseBody;
    }
}
