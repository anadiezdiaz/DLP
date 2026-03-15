package semantic;

import ast.Definition;
import ast.Expression;
import ast.Program;
import ast.Statement;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;

public class AbstractVisitor<TP, TR> implements Visitor<TP, TR> {
    @Override
    public TR visit(Program pr, TP p) {
        for(Definition d : pr.getDefinitions()){
            d.accept(this, p);
        }
        return null;
    }

    @Override
    public TR visit(FuncDefinition f, TP p) {
        for(Statement s : f.getStatements()){
            s.accept(this, p);
        }
        return null;
    }

    @Override
    public TR visit(VarDefinition v, TP p) {
        v.getType().accept(this, p);
        return null;
    }

    @Override
    public TR visit(Arithmetic a, TP p) {
        a.getRight().accept(this, p);
        a.getLeft().accept(this, p);
        return null;
    }

    @Override
    public TR visit(ArrayAccess a, TP p) {
        a.getRight().accept(this, p);
        a.getLeft().accept(this, p);
        return null;
    }

    @Override
    public TR visit(Cast c, TP p) {
        c.getType().accept(this, p);
        c.getExpression().accept(this, p);
        return null;
    }

    @Override
    public TR visit(CharLiteral c, TP p) {
        return null;
    }

    @Override
    public TR visit(Comparisson c, TP p) {
        c.getRight().accept(this, p);
        c.getLeft().accept(this, p);
        return null;
    }

    @Override
    public TR visit(FieldAccess f, TP p) {
        f.getExpression().accept(this, p);
        return null;
    }

    @Override
    public TR visit(FunctionInvocation f, TP p) {
        f.getVariable().accept(this, p);
        for(Expression e : f.getArguments()){
            e.accept(this, p);
        }
        return null;
    }

    @Override
    public TR visit(IntLiteral i, TP p) {
        return null;
    }

    @Override
    public TR visit(Logic l, TP p) {
        l.getRight().accept(this, p);
        l.getRight().accept(this, p);
        return null;
    }

    @Override
    public TR visit(NumberLiteral n, TP p) {
        return null;
    }

    @Override
    public TR visit(UnaryMinus u, TP p) {
        u.getExpression().accept(this, p);
        return null;
    }

    @Override
    public TR visit(UnaryNot u, TP p) {
        u.getExpression().accept(this, p);
        return null;
    }

    @Override
    public TR visit(Variable v, TP p) {
        return null;
    }

    @Override
    public TR visit(Assignment a, TP p) {
        a.getRight().accept(this, p);
        a.getLeft().accept(this, p);
        return null;
    }

    @Override
    public TR visit(IfElseStatement i, TP p) {
        i.getExpression().accept(this, p);
        for(Statement s : i.getIfBody()){
            s.accept(this, p);
        }
        for(Statement s : i.getElseBody()){
            s.accept(this, p);
        }
        return null;
    }

    @Override
    public TR visit(InputStatement i, TP p) {
        for(Expression e : i.getExpressions()){
            e.accept(this, p);
        }
        return null;
    }

    @Override
    public TR visit(LogStatement l, TP p) {
        for(Expression e : l.getExpressions()){
            e.accept(this, p);
        }
        return null;
    }

    @Override
    public TR visit(ReturnStatement r, TP p) {
        r.getExpression().accept(this, p);
        return null;
    }

    @Override
    public TR visit(WhileStatement w, TP p) {
        w.getExpression().accept(this, p);
        for(Statement s : w.getBody()){
            s.accept(this, p);
        }
        return null;
    }

    @Override
    public TR visit(ArrayType a, TP p) {
        a.getType().accept(this, p);
        return null;
    }

    @Override
    public TR visit(CharType c, TP p) {
        return null;
    }

    @Override
    public TR visit(ErrorType e, TP p) {
        return null;
    }

    @Override
    public TR visit(FunctionType f, TP p) {
        f.getReturnType().accept(this, p);
        for(VarDefinition v : f.getParameters()){
            v.accept(this, p);
        }
        return null;
    }

    @Override
    public TR visit(IntType i, TP p) {
        return null;
    }

    @Override
    public TR visit(NumberType n, TP p) {
        return null;
    }

    @Override
    public TR visit(RecordField r, TP p) {
        return null;
    }

    @Override
    public TR visit(RecordType r, TP p) {
        for(RecordField re : r.getRecordFields()){
            re.accept(this, p);
        }
        return null;
    }

    @Override
    public TR visit(VoidType v, TP p) {
        return null;
    }
}
