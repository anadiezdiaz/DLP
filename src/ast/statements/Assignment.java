package ast.statements;

import ast.Expression;
import ast.Statement;

public class Assignment extends AbstractBinaryExpressionStatement {

    public Assignment(int line, int column, Expression left, Expression right) {
        super(line, column, left, right);
    }
}
