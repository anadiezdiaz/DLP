package ast.expressions;

import ast.Expression;
import ast.statements.AbstractBinaryExpressionStatement;

public class AbstractBinaryOperatorExpression extends AbstractBinaryExpression {
    String operator;

    public AbstractBinaryOperatorExpression(int line, int column, Expression left, Expression right, String operator) {
        super(line, column, left, right);
        this.operator = operator;
    }

    public String getOperator() {
        return this.operator;
    }
}
