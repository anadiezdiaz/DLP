package ast.expressions;

import ast.Expression;

public class Variable extends AbstractExpression {
    String name;

    public Variable(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
