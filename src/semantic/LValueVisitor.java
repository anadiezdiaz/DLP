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

//Aquí solo deberían estar los métodos que vayan a hacer un setLvalue(...) o un getLvalue()
public class LValueVisitor<TP, TR> extends AbstractVisitor<Void, Void>{
    @Override
    public Void visit(Arithmetic a, Void p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Void visit(ArrayAccess a, Void p) {
        super.visit(a, p);
        a.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Cast c, Void p) {
        super.visit(c, p);
        c.setLValue(false);
        return null;
    }

    @Override
    public Void visit(CharLiteral c, Void p) {
        super.visit(c, p);
        c.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Comparisson c, Void p) {
        super.visit(c, p);
        c.setLValue(false);
        return null;
    }

    @Override
    public Void visit(FieldAccess f, Void p) {
        super.visit(f, p);
        f.setLValue(true);
        return null;
    }

    @Override
    public Void visit(FunctionInvocation f, Void p) {
        super.visit(f, p);
        //f.getVariable().setLValue(false);
        f.setLValue(false);
        return null;
    }

    @Override
    public Void visit(IntLiteral i, Void p) {
        super.visit(i, p);
        i.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Logic l, Void p) {
        super.visit(l, p);
        l.setLValue(false);
        return null;
    }

    @Override
    public Void visit(NumberLiteral n, Void p) {
        super.visit(n, p);
        n.setLValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryMinus u, Void p) {
        super.visit(u, p);
        u.setLValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryNot u, Void p) {
        super.visit(u, p);
        u.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Variable v, Void p) {
        super.visit(v, p);
        v.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Assignment a, Void p) {
        super.visit(a, p);
        if (!a.getLeft().getLValue()) {
            new ErrorType(a.getLine(), a.getColumn(), "LValue expected at assignment");
        }
        return null;
    }

    @Override
    public Void visit(InputStatement i, Void p) {
        super.visit(i, p);
        for(Expression e : i.getExpressions()){
            if (!e.getLValue()) {
                new ErrorType(e.getLine(), e.getColumn(), "LValue expected at input statement");
            }
        }
        return null;
    }
}

