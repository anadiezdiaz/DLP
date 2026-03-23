package semantic;

import ast.Definition;
import ast.Statement;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Variable;
import ast.types.ErrorType;
import ast.types.FunctionType;
import symboltable.SymbolTable;

public class IdentificationVisitor extends AbstractVisitor<Void, Void>{
    SymbolTable symbolTable = new SymbolTable();

    @Override
    public Void visit(FuncDefinition f, Void p) {
        if(!symbolTable.insert(f)){
            new ErrorType(f.getLine(), f.getColumn(), "FuncDefinition '" + f.getName() + "' already defined");
        }
        symbolTable.set();
        super.visit(f, p);
        symbolTable.reset();
        return null;
    }

    @Override
    public Void visit(VarDefinition v, Void p) {
        if(!symbolTable.insert(v)){
            new ErrorType(v.getLine(), v.getColumn(), "VarDefinition '" + v.getName() + "' already defined");
        }
        super.visit(v, p);
        return null;
    }

    @Override
    public Void visit(Variable v, Void p) {
        Definition def = symbolTable.find(v.getName());
        if(def != null){
            v.setDefinition(def);
        }else{
            def = new VarDefinition(0,0, v.getName(),
                new ErrorType(v.getLine(), v.getColumn(), "No VarDefinition associated to variable '" + v.getName() + "'"));
        }
        super.visit(v, p);
        return null;
    }

    @Override
    public Void visit(FunctionType f, Void p){
        for(VarDefinition v : f.getParameters()){
            if(!symbolTable.insert(v)){
                new ErrorType(v.getLine(), v.getColumn(), "FunctionType '" + v.getName() + "' already defined");
            }
        }
        //super.visit(f, p);
        f.getReturnType().accept(this, p);
        return null;
    }

}
