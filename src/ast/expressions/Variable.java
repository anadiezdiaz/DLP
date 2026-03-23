package ast.expressions;

import ast.Definition;
import semantic.Visitor;

public class Variable extends AbstractExpression {
    String name;
    Definition definition;

    public Variable(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDefinition(Definition definition){
        this.definition = definition;
    }

    public Definition getDefinition(){
        return this.definition;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
