package ast.types;

import ast.AbstractLocatable;
import ast.Type;
import semantic.Visitor;

public class VoidType extends AbstractType {
    private static VoidType instance;

    public VoidType() {
        super(0, 0);
    }

    public static VoidType getInstance(){
        if(instance == null){
            instance = new VoidType();
        }
        return instance;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString(){
        return "VoidType";
    }
}
