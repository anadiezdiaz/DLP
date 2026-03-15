package ast.statements;

import ast.Expression;
import ast.Statement;
import semantic.Visitor;

public class Assignment extends AbstractBinaryExpressionStatement {

    public Assignment(int line, int column, Expression left, Expression right) {
        super(line, column, left, right);
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
