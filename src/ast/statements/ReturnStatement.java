package ast.statements;

import ast.Expression;
import ast.Statement;
import semantic.Visitor;

public class ReturnStatement extends AbstractUnaryExpressionStatement {

    public ReturnStatement(int line, int column, Expression expression) {
        super(line, column, expression);
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

}
