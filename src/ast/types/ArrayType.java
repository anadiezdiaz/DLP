package ast.types;

import ast.AbstractLocatable;
import ast.Locatable;
import ast.Type;
import semantic.Visitor;

public class ArrayType extends AbstractType {
    Type type;
    int size;

    public ArrayType(int size, Type type) {
        super(0,0);
        this.size = size;
        this.type = type;
    }

    public int getSize(){
        return this.size;
    }

    public Type getType(){
        return this.type;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public Type squareBrackets(Locatable locatable, Type other) {
        if(other instanceof IntType){
            return this.type;
        }
        return super.squareBrackets(locatable, other);
    }

    @Override
    public String toString(){
        return "ArrayType, type: " + type;
    }
}
