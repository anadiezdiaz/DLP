package ast.types;

import ast.AbstractLocatable;
import ast.Type;
import ast.definitions.VarDefinition;

import java.util.List;

public class FunctionType implements Type {
    Type returnType;
    List<VarDefinition> parameters;

    public FunctionType(Type returnType, List<VarDefinition> parameters) {
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public Type getReturnType(){
        return this.returnType;
    }

    public List<VarDefinition> getParameters(){
        return this.parameters;
    }
}
