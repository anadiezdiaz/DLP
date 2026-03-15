package ast.expressions;

import ast.Expression;
import ast.Statement;
import semantic.Visitor;

import java.util.List;

public class FunctionInvocation extends AbstractExpression implements Statement{
    List<Expression> arguments;
    Variable variable;

    public FunctionInvocation(int line, int column, List<Expression> arguments, Variable variable) {
        super(line, column);
        this.arguments = arguments;
        this.variable = variable;
    }

    public List<Expression> getArguments() {
        return this.arguments;
    }

    public Variable getVariable() {
        return this.variable;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

}
