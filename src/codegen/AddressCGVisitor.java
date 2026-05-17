package codegen;

import ast.definitions.VarDefinition;
import ast.expressions.ArrayAccess;
import ast.expressions.FieldAccess;
import ast.expressions.Variable;
import ast.types.IntType;
import ast.types.RecordType;

/*
Para la plantilla de Address hay que incluir:
    Variable
	FieldAccess
	ArrayAccess
 */
public class AddressCGVisitor extends AbstractCGVisitor<Void, Void>{
    private ValueCGVisitor value;

    public AddressCGVisitor(CodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
    }

    public void setValueCGVisitor(ValueCGVisitor value) {
        this.value = value;
    }

    /*
    address[[Variable : expr -> ID]]()
        if(expr.definition.scope == 0){
            <pusha> expr.definition.offset
        }else{
            <push bp>
            <pushi> expr.definition.offset
            <addi>
        }
     */
    @Override
    public Void visit(Variable v, Void p){
        if(v.getDefinition().getScope() == 0){
            codeGenerator.pusha(((VarDefinition) v.getDefinition()).getOffset());
        }else{
            codeGenerator.pushbp();
            codeGenerator.push(IntType.getInstance(), ((VarDefinition) v.getDefinition()).getOffset());
            codeGenerator.add(IntType.getInstance());
        }
        return null;
    }

    /*
    address[[FieldAccess: exp1 -> exp2 ID]]()=
        address[[exp2]]
        <pushi> exp2.type.getField(ID).offset
        <addi>
     */
    @Override
    public Void visit(FieldAccess f, Void p){
        f.getExpression().accept(this, p);
        codeGenerator.push(IntType.getInstance(), ((RecordType) f.getExpression().getType()).getField(f.getField()).getOffset());
        codeGenerator.add(IntType.getInstance());
        return null;
    }

    /*
    address[[ArrayAccess: exp1 -> exp2 exp3]]()=
        address[[exp2]]
        value[[exp3]]
        <b2i>
        <pushi> exp1.type.numberOfBytes
        <muli>
        <addi>
     */
    @Override
    public Void visit(ArrayAccess a, Void p){
        a.getLeft().accept(this, p);
        a.getRight().accept(value, p);
        codeGenerator.convertTo(a.getRight().getType(), IntType.getInstance());
        codeGenerator.push(IntType.getInstance(), a.getType().getNumberOfBytes());
        codeGenerator.mul(IntType.getInstance());
        codeGenerator.add(IntType.getInstance());
        return null;
    }
}
