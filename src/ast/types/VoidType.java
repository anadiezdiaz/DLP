package ast.types;

import ast.AbstractLocatable;
import ast.Type;
import semantic.Visitor;

public class VoidType implements Type {
    private static VoidType instance;

    public static VoidType getInstance(){
        if(instance == null){
            instance = new VoidType();
        }
        return instance;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
