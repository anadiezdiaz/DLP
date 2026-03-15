package ast.types;

import ast.AbstractLocatable;
import ast.Type;
import semantic.Visitor;

public class NumberType implements Type {
    private static NumberType instance;

    public static NumberType getInstance() {
        if (instance == null) {
            instance = new NumberType();
        }
        return instance;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
