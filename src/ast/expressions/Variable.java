package ast.expressions;

import semantic.Visitor;

public class Variable extends AbstractExpression {
    String name;

    public Variable(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
