package ast.expressions;

import ast.Expression;

public class Comparisson extends AbstractBinaryOperatorExpression{
    public Comparisson(int line, int column, Expression left, Expression right, String operator) {
        super(line, column, left, right, operator);
    }
}
