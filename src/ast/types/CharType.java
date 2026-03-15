package ast.types;

import ast.AbstractLocatable;
import ast.Type;
import semantic.Visitor;

public class CharType implements Type {
    private static CharType instance;

    public static CharType getInstance(){
        if(instance == null){
            instance = new CharType();
        }
        return instance;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
