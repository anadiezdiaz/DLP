package ast.types;

import ast.AbstractLocatable;
import ast.Locatable;
import ast.Type;
import ast.definitions.VarDefinition;
import semantic.Visitor;

import java.util.List;

public class FunctionType extends AbstractType {
    Type returnType;
    List<VarDefinition> parameters;

    private int returnBytes;
    private int paramBytes;

    public FunctionType(Type returnType, List<VarDefinition> parameters) {
        super(0,0);
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public Type getReturnType(){
        return this.returnType;
    }

    public List<VarDefinition> getParameters(){
        return this.parameters;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public Type parenthesis(Locatable locatable, List<Type> others) {
        if (others.size() != parameters.size()) {
            return super.parenthesis(locatable, others);
        }
        for (int i = 0; i < parameters.size(); i++) {
            parameters.get(i).getType().mustPromotes(locatable, others.get(i));
        }
        return returnType;
    }

    public int getParamBytes() {
        return paramBytes;
    }

    public void setParamBytes(int paramBytes) {
        this.paramBytes = paramBytes;
    }

    public int getBytesReturn() {
        return returnBytes;
    }

    public void setReturnBytes(int returnBytes) {
        this.returnBytes = returnBytes;
    }

    @Override
    public String toString(){
        return "FunctionType";
    }

}
