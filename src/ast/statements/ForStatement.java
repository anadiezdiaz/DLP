package ast.statements;

import ast.Expression;
import ast.Statement;
import semantic.Visitor;

import java.util.List;

public class ForStatement extends AbstractUnaryExpressionStatement{
    List<Statement> body;
    Statement pre;
    Statement post;

    public ForStatement(int line, int column, Statement pre, Expression condition, Statement post, List<Statement> body){
        super(line, column, condition);
        this.pre = pre;
        this.post = post;
        this.body = body;
    }

    public Statement getPost() {
        return post;
    }

    public Statement getPre() {
        return pre;
    }

    public List<Statement> getBody() {
        return body;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return "ForStatement [body=" + getBody() + ", pre=" + getPre() + ", condition=" + getExpression() + ", post=" + getPost()
                + ", line" + getLine() + ", column" + getColumn() + "]";
    }
}
