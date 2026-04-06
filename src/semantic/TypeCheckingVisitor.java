package semantic;

import ast.definitions.FuncDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import ast.*;

import java.util.ArrayList;
import java.util.List;

public class TypeCheckingVisitor extends AbstractVisitor<Type, Void> {

    @Override
    public Void visit(FuncDefinition f, Type p) {
        super.visit(f, f.getType());
        return null;
    }

    @Override
    public Void visit(Arithmetic a, Type p) {
        super.visit(a, p);
        a.setType(a.getLeft().getType().arithmetic(a, a.getRight().getType()));
        return null;
    }

    @Override
    public Void visit(ArrayAccess a, Type p) {
        super.visit(a, p);
        a.setType(a.getLeft().getType().squareBrackets(a, a.getRight().getType()));
        return null;
    }

    @Override
    public Void visit(Cast c, Type p) {
        super.visit(c, p);
        c.setType(c.getExpression().getType().canBeCastTo(c, c.getType()));
        return null;
    }

    @Override
    public Void visit(CharLiteral c, Type p) {
        super.visit(c, p);
        c.setType(CharType.getInstance());
        return null;
    }

    @Override
    public Void visit(Comparisson c, Type p) {
        super.visit(c, p);
        c.setType(c.getLeft().getType().comparison(c, c.getRight().getType()));
        return null;
    }

    @Override
    public Void visit(FieldAccess f, Type p) {
        super.visit(f, p);
        f.setType(f.getExpression().getType().dot(f, f.getField()));
        return null;
    }

    @Override
    public Void visit(FunctionInvocation f, Type p) {
        super.visit(f, p);
        List<Type> params = new ArrayList<>();
        for(Expression expression : f.getArguments()){
            params.add(expression.getType());
        }
        f.setType(f.getVariable().getDefinition().getType().parenthesis(f, params));
        return null;
    }

    @Override
    public Void visit(IntLiteral i, Type p) {
        super.visit(i, p);
        i.setType(IntType.getInstance());
        return null;
    }

    @Override
    public Void visit(Logic l, Type p) {
        super.visit(l, p);
        l.setType(l.getLeft().getType().logic(l, l.getRight().getType()));
        return null;
    }

    @Override
    public Void visit(NumberLiteral n, Type p) {
        super.visit(n, p);
        n.setType(NumberType.getInstance());
        return null;
    }

    @Override
    public Void visit(UnaryMinus u, Type p) {
        super.visit(u, p);
        u.setType(u.getExpression().getType().arithmetic(u));
        return null;
    }

    @Override
    public Void visit(UnaryNot u, Type p) {
        super.visit(u, p);
        u.setType(u.getExpression().getType().logic(u));
        return null;
    }

    @Override
    public Void visit(Variable v, Type p) {
        super.visit(v, p);
        v.setType(v.getDefinition().getType());
        return null;
    }

    @Override
    public Void visit(Assignment a, Type p) {
        super.visit(a, p);
        a.getLeft().getType().mustPromotes(a, a.getRight().getType());
        return null;
    }

    @Override
    public Void visit(IfElseStatement i, Type p) {
        super.visit(i, p);
        i.getExpression().getType().mustBeLogical(i);
        return null;
    }

    @Override
    public Void visit(InputStatement i, Type p) {
        super.visit(i, p);
        for(Expression expression : i.getExpressions()){
            expression.getType().mustBeBuiltIn(i);
        }
        return null;
    }

    @Override
    public Void visit(LogStatement l, Type p) {
        super.visit(l, p);
        for(Expression expression : l.getExpressions()){
            expression.getType().mustBeBuiltIn(l);
        }
        return null;
    }

    @Override
    public Void visit(ReturnStatement r, Type p) {
        super.visit(r, p);
        FunctionType f = (FunctionType) p;
        f.getReturnType().mustPromotes(r, r.getExpression().getType());
        return null;
    }

    @Override
    public Void visit(WhileStatement w, Type p) {
        super.visit(w, p);
        w.getExpression().getType().mustBeLogical(w);
        return null;
    }
}
