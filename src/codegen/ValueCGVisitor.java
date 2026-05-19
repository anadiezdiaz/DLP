package codegen;

import ast.expressions.*;
import ast.types.*;

/*
Para la plantilla de Value hay que incluir
    Expressions
 */
public class ValueCGVisitor extends AbstractCGVisitor<Void, Void>{

    private AddressCGVisitor address;

    public ValueCGVisitor(CodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
    }

    public ValueCGVisitor(){
        this.address = new AddressCGVisitor(codeGenerator);
    }

    public void setAddressCGVisitor(AddressCGVisitor address) {
        this.address = address;
    }

    /*
    value[[Arithmetic: exp1 -> exp2 (+|-|*|/|%) exp3]]()=
        value[[exp2]]
        codeGenerator.convertTo(exp2.type, exp1.type)
        value[[exp3]]
        codeGenerator.convertTo(exp3.type, exp1.type)
        codeGenerator.arithmetic(exp1.type, exp1.op);
     */
    @Override
    public Void visit(Arithmetic a, Void p) {
        a.getLeft().accept(this, p);
        codeGenerator.convertTo(a.getLeft().getType(), a.getType());
        a.getRight().accept(this, p);
        codeGenerator.convertTo(a.getRight().getType(), a.getType());
        codeGenerator.arithmetic(a.getType(), a.getOperator());
        return null;
    }

    /*
    value[[Len: exp1 -> exp2]]()=
        value[[exp1]]()
        <pushi> exp1.type.size
     */
    @Override
    public Void visit(Len l, Void p) {
        ArrayType arrayType = (ArrayType) l.getExpression().getType();
        codeGenerator.push(IntType.getInstance(), arrayType.getSize());
        return null;
    }

    /*
    value[[ArrayAccess: exp1 -> exp2 exp3]]()=
        address[[exp1]]()
        <load> exp1.type.suffix
     */
    @Override
    public Void visit(ArrayAccess a, Void p) {
        a.accept(address, p);
        codeGenerator.load(a.getType());
        return null;
    }

    /*
    value[[Cast: exp1 -> type exp2]]()=
        value[[exp2]]()
        codeGenerator.convertTo(exp2.type, type)
     */
    @Override
    public Void visit(Cast c, Void p) {
        c.getExpression().accept(this, p);
        codeGenerator.convertTo(c.getExpression().getType(), c.getType());
        return null;
    }

    /*
    value[[CharLiteral: exp -> CHAR_CONSTANT]]()=
        <pushb> exp.value
     */
    @Override
    public Void visit(CharLiteral c, Void p) {
        codeGenerator.push(CharType.getInstance(), (int) c.getValue());
        return null;
    }

    /*
    value[[Comparison: exp1 -> exp2 (>|<|>=|<=|==|!=) exp3]]()=
        value[[exp2]]()
        codeGenerator.convertTo(exp2.type, exp1.type)
        value[[exp3]]()
        codeGenerator.convertTo(exp3.type, exp1.type)
        codeGenerator.comparison(exp1.type, exp1.op)
     */
    @Override
    public Void visit(Comparison c, Void p) {
        c.getLeft().accept(this, p);
        codeGenerator.convertTo(c.getLeft().getType(), c.getType());
        c.getRight().accept(this, p);
        codeGenerator.convertTo(c.getRight().getType(), c.getType());
        codeGenerator.comparison(c.getType(), c.getOperator());
        return null;
    }

    /*
    value[[FieldAccess: exp1 -> exp2 ID]]()=
        address[[exp1]]()
        <load> exp1.type.suffix
     */
    @Override
    public Void visit(FieldAccess f, Void p) {
        f.accept(address, p);
        codeGenerator.load(f.getType());
        return null;
    }

    /*
    value[[FunctionInvocation: exp1 -> exp2 exp3*]]() =
        for(int i = 0; i < exp3*.size(); i++){
            value[[exp3*.get(i)]]()
            codeGenerator.convertTo(exp3*.get(i).type, exp2.type.parameters.get(i).type))
        }
        <call> exp2.name
     */
    @Override
    public Void visit(FunctionInvocation f, Void p) {
        for(int i = 0; i < f.getArguments().size(); i++){
            f.getArguments().get(i).accept(this, p);
            codeGenerator.convertTo(f.getArguments().get(i).getType(),
                    ((FunctionType) f.getVariable().getType()).getParameters().get(i).getType());
        }
        codeGenerator.callIdent(f.getVariable().getName());
        return null;
    }

    /*
    value[[IntLiteral: exp -> INT_CONSTANT]]()=
        <pushi> exp.value
     */
    @Override
    public Void visit(IntLiteral i, Void p) {
        codeGenerator.push(IntType.getInstance(), i);
        return null;
    }

    /*
    value[[Logic: exp1 -> exp2 (&&||||!) exp3]]()=
        value[[exp2]]()
        value[[exp3]]()
        codeGenerator.logical(exp1.op)
     */
    @Override
    public Void visit(Logic l, Void p) {
        l.getLeft().accept(this, p);
        l.getRight().accept(this, p);
        codeGenerator.logical(l.getOperator());
        return null;
    }

    /*
    value[[NumberLiteral: exp -> REAL_CONSTANT]]()=
        <pushf> exp.value
     */
    @Override
    public Void visit(NumberLiteral n, Void p) {
        codeGenerator.push(NumberType.getInstance(), n);
        return null;
    }

    /*
    value[[UnaryMinus: exp1 -> exp2]]()=
        value[[exp2]]()
        codeGenerator.convertTo(exp2.type, exp1.type)
        <pushi> -1
        codeGenerator.convertTo(IntType, exp1.type)
        <mul> exp1.type.suffix
     */
    @Override
    public Void visit(UnaryMinus u, Void p) {
        u.getExpression().accept(this, p);
        codeGenerator.convertTo(u.getExpression().getType(), u.getType());
        codeGenerator.push(IntType.getInstance(), -1);
        codeGenerator.convertTo(IntType.getInstance(), u.getType());
        codeGenerator.mul(u.getType());
        return null;
    }

    /*
    value[[UnaryNot: exp1 -> exp2]]()=
        value[[exp2]]()
        codeGenerator.logical(exp1.operator)
    */
    @Override
    public Void visit(UnaryNot u, Void p) {
        u.getExpression().accept(this, p);
        codeGenerator.logical("!");
        return null;
    }

    /*
    value[[Variable: exp -> ID]]()=
        address[[exp]]()
        <load> exp.type.suffix
    */
    @Override
    public Void visit(Variable v, Void p){
        v.accept(address, null);
        codeGenerator.load(v.getType());
        return null;
    }



}
