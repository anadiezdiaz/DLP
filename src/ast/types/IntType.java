package ast.types;

import ast.AbstractLocatable;
import ast.Type;
import semantic.Visitor;

public class IntType implements Type {
    private static IntType instance;

    public static IntType getInstance(){
        if(instance == null){
            instance = new IntType();
        }
        return instance;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

}
