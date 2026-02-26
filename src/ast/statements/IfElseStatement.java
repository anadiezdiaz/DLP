package ast.statements;

import ast.Expression;

import ast.Statement;

import java.util.List;

public class IfElseStatement extends AbstractUnaryExpressionStatement{
    List<Statement> ifBody;
    List<Statement> elseBody;

    public IfElseStatement(int line, int column, Expression condition, List<Statement> ifBody, List<Statement> elseBody) {
        super(line, column, condition);
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }

    public List<Statement> getIfBody() {
        return this.ifBody;
    }

    public List<Statement> getElseBody() {
        return this.elseBody;
    }
}
