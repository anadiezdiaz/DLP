package ast.types;

import ast.AbstractLocatable;
import ast.Type;
import semantic.Visitor;

public class ArrayType implements Type {
    Type type;
    int size;

    public ArrayType(int size, Type type) {
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
}
