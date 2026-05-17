package codegen;

import ast.Definition;
import ast.Expression;
import ast.Program;
import ast.Statement;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.FunctionInvocation;
import ast.statements.*;
import ast.types.FunctionType;
import ast.types.IntType;
import ast.types.VoidType;

/*
Para la plantilla de Execution hay que incluir:
	Statements
	Program
		VarDefinition
		FunctionDefinition
 */
public class ExecuteCGVisitor extends AbstractCGVisitor<FuncDefinition, Void>{
    private AddressCGVisitor address;
    private ValueCGVisitor value;

    public ExecuteCGVisitor(CodeGenerator codeGenerator){
        this.codeGenerator = codeGenerator;
        this.address = new AddressCGVisitor(codeGenerator);
        this.value = new ValueCGVisitor(codeGenerator);
        this.address.setValueCGVisitor(value);
        this.value.setAddressCGVisitor(address);
    }

    /*
    execute[[Assignment : statement -> exp1 exp2]]() =
                           address[[exp1]]
						   value[[exp2]]
						   cg.convertTo(exp2.type, exp1.type)
						   <store>exp1.type.suffix()
     */
    @Override
    public Void visit(Assignment a, FuncDefinition f){
        codeGenerator.line(a.getLine());
        codeGenerator.comment("' * Assignment");

        a.getLeft().accept(address, null);
        a.getRight().accept(value, null);
        codeGenerator.convertTo(a.getRight().getType(), a.getLeft().getType());
        codeGenerator.store(a.getLeft().getType());
        return null;
    }

    /*
    execute[[InputStatement: stmnt -> exp*]]() =
        for(Expression e : exp*){
            address[[e]]()
            <in>e.type.suffix()
            <store>e.type.suffix()
        }
     */
    @Override
    public Void visit(InputStatement i, FuncDefinition f){
        int line = i.getLine();
        for(Expression e : i.getExpressions()){
            codeGenerator.line(line);
            codeGenerator.comment("' * Read");

            e.accept(address, null);
            codeGenerator.input(e.getType());
            codeGenerator.store(e.getType());
        }
        return null;
    }

    /*
    execute[[LogStatement: stmnt -> exp*]]() =
        for(Expression e : exp*){
            value[[e]]()
            <out>e.type.suffix()
        }
     */
    @Override
    public Void visit(LogStatement l, FuncDefinition f){
        int line = l.getLine();
        for(Expression e : l.getExpressions()){
            codeGenerator.line(line);
            codeGenerator.comment("' * Write");

            e.accept(value, null);
            codeGenerator.output(e.getType());
        }
        return null;
    }

    /*
    execute[[Program : program -> definition*]]()
        for(Definition d : definition*){
            if(d instanceOf VarDefinition)
                execute[[d]]()
        }
        <call main>
        <halt>
        for(Definition d : definition*){
            if(d instanceOf FuncDefinition)
                execute[[d]]()
        }
     */
    @Override
    public Void visit(Program p, FuncDefinition f){
        for(Definition d : p.getDefinitions()){
            if(d instanceof VarDefinition){
                d.accept(this, null);
            }
        }

        codeGenerator.mainInvocation();
        for(Definition d : p.getDefinitions()){
            if(d instanceof FuncDefinition){
                d.accept(this, null);
            }
        }
        return null;
    }

    /*
    execute[[VarDefinition: def -> type ID]]() =
        ' * type.toString() ID (offset def.offset)
     */
    @Override
    public Void visit(VarDefinition v, FuncDefinition f){
        String varDefComment = "' * " + v.getType().toString() + " " + v.getName() + " "
                                + "(" + "offset " + v.getOffset() + ")";
        codeGenerator.comment(varDefComment);
        return null;
    }

    /*
    execute[[FuncDefinition: def -> ID type stmnt*]]() =
        <label> ID
        '* Parameters
        for(VarDefinition par : ((FunctionType) def.type).parameters)
            execute[[par]]()
        '* Local variables
        for(Statement s : def.statements)
            if(s instanceof VarDefinition)
                execute[[s]]()
        <enter> def.localBytes
        for(Statement s : def.statements)
            if(!(s instanceof VarDefinition))
                execute[[s]](def)
        if(((FunctionType) def.type).returnType == VoidType.getInstance())
            <ret> 0, def.localBytes, ((FunctionType) def.type).paramBytes
    */
    @Override
    public Void visit(FuncDefinition funcDef, FuncDefinition p) {
        codeGenerator.line(funcDef.getLine());
        codeGenerator.label(funcDef.getName());

        FunctionType type = (FunctionType) funcDef.getType();

        int paramBytes = type.getParameters().stream()
                .mapToInt(param -> param.getType().getNumberOfBytes())
                .sum();

        int localBytes = funcDef.getLocalBytes();
        int returnBytes = type.getReturnType().getNumberOfBytes();

        type.setParamBytes(paramBytes);
        type.setReturnBytes(returnBytes);
        funcDef.setLocalBytes(localBytes);

        codeGenerator.comment("' * Parameters");
        for (VarDefinition param : type.getParameters()) {
            param.accept(this, funcDef);
        }

        codeGenerator.comment("' * Local variables");
        for (Statement s : funcDef.getStatements()) {
            if (s instanceof VarDefinition) {
                s.accept(this, funcDef);
            }
        }

        codeGenerator.enter(localBytes);

        for (Statement s : funcDef.getStatements()) {
            if (!(s instanceof VarDefinition)) {
                s.accept(this, funcDef);
            }
        }

        if (type.getReturnType() == VoidType.getInstance()) {
            codeGenerator.ret(0, localBytes, paramBytes);
        }
        return null;
    }

    /*
    execute[[IfElse: stmnt1 -> expr stmnt2* stmnt3*]]()=
        String elseLabel = codeGenerator.getLabel()
        String endLabel = codeGenerator.getLabel()
        value[[expr]]()
        codeGenerator.convertTo(expr.type, IntType)
        <jz> elseLabel
        stmnt2*.forEach(s->execute[[s]]())
        <jmp> endLabel
        elseLabel <:>
        stmnt3*.forEach(s->execute[[s]]())
        endLabel <:>
     */
    @Override
    public Void visit(IfElseStatement i, FuncDefinition f){
        String elseLabel = codeGenerator.getLabel();
        String endLabel = codeGenerator.getLabel();

        codeGenerator.line(i.getLine());
        codeGenerator.comment("' * If");

        codeGenerator.line(i.getLine());
        i.getExpression().accept(value, null);
        codeGenerator.convertTo(i.getExpression().getType(), IntType.getInstance());
        codeGenerator.jz(elseLabel);

        codeGenerator.comment("' * if body");
        for(Statement s : i.getIfBody()){
            s.accept(this, f);
        }
        codeGenerator.jmp(endLabel);
        codeGenerator.label(elseLabel);

        codeGenerator.comment("' * else body");

        for(Statement s : i.getElseBody()){
            s.accept(this, f);
        }
        codeGenerator.label(endLabel);
        return null;
    }

    /*
    execute[[While: stmnt1 -> expr stmnt2*]]()=
        String condLabel = codeGenerator.getLabel()
        String endLabel = codeGenerator.getLabel()
        condLabel <:>
        value[[expr]]()
        codeGenerator.convertTo(expr.type, IntType)
        <jz> endLabel
        stmnt2*.forEach(s->execute[[s]]())
        <jmp> condLabel
        endLabel <:>
     */
    @Override
    public Void visit(WhileStatement w , FuncDefinition f){
        String condLabel = codeGenerator.getLabel();
        String endLabel = codeGenerator.getLabel();

        codeGenerator.line(w.getLine());
        codeGenerator.comment("' * While");

        codeGenerator.line(w.getLine());
        codeGenerator.label(condLabel);

        w.getExpression().accept(value, null);
        codeGenerator.convertTo(w.getExpression().getType(), IntType.getInstance());
        codeGenerator.jz(endLabel);

        codeGenerator.comment("' * While body");

        for(Statement s : w.getBody()){
            s.accept(this, f);
        }

        codeGenerator.jmp(condLabel);
        codeGenerator.label(endLabel);

        return null;
    }

    /*
    execute[[ReturnStatement: stmnt -> exp]](FunctionDefinition funcdef)
        value[[expr]]()
        codeGenerator.convertTo(exp.type, funcdef.type.returnType)
        <ret> funcdef.type.returnType.numberOfBytes, funcdef.localBytes, funcdef.paramBytes
     */
    @Override
    public Void visit(ReturnStatement r, FuncDefinition f){
        codeGenerator.line(r.getLine());
        codeGenerator.comment("' * Return");

        r.getExpression().accept(value, null);
        codeGenerator.convertTo(r.getExpression().getType(), ((FunctionType) f.getType()).getReturnType());
        codeGenerator.ret(((FunctionType) f.getType()).getReturnType().getNumberOfBytes(),
                f.getLocalBytes(),
                ((FunctionType) f.getType()).getParamBytes());
        return null;
    }

    /*
    execute[[FunctionInvocation: stmnt -> exp1 exp2*]]()=
        value[[(Expression) stmnt]]()
        if(exp1.type.returnType != VoidType.getInstance()))
            <pop> exp1.type.returnType.suffix()
     */
    @Override
    public Void visit(FunctionInvocation fi, FuncDefinition f){
        codeGenerator.line(fi.getLine());
        fi.accept(value, null);
        if(((FunctionType) fi.getVariable().getType()).getReturnType() != VoidType.getInstance()){
            codeGenerator.pop(((FunctionType) fi.getVariable().getType()).getReturnType());
        }
        return null;
    }

    /*
    execute[[DoWhile: stmnt1 -> stmnt2* expr]]()=
        String condLabel = codeGenerator.getLabel()
        String endLabel = codeGenerator.getLabel()
        condLabel <:>
        stmnt2*.forEach(s->execute[[s]]())
        value[[expr]]()
        codeGenerator.convertTo(expr.type, IntType)
        <jnz> condLabel
     */
    @Override
    public Void visit(DoWhileStatement w , FuncDefinition f){
        String condLabel = codeGenerator.getLabel();

        codeGenerator.line(w.getLine());
        codeGenerator.comment("' * DoWhile");

        codeGenerator.line(w.getLine());
        codeGenerator.label(condLabel);

        codeGenerator.comment("' * DoWhile body");

        for(Statement s : w.getBody()){
            s.accept(this, f);
        }

        w.getExpression().accept(value, null);
        codeGenerator.convertTo(w.getExpression().getType(), IntType.getInstance());
        codeGenerator.jnz(condLabel);

        return null;
    }
}
