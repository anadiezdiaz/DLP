package ast.statements;

import ast.Expression;
import ast.Type;
import semantic.Visitor;

public class CompoundAssignment extends AbstractBinaryExpressionStatement {
    private String operator;
    private Type operationType;

    public CompoundAssignment(int line, int column, Expression left, Expression right, String operator) {
        super(line, column, left, right);
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public Type getOperationType() {
        return operationType;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
