package ast.types;

import ast.AbstractLocatable;
import ast.Type;

public class NumberType implements Type {
    private static NumberType instance;

    public static NumberType getInstance() {
        if (instance == null) {
            instance = new NumberType();
        }
        return instance;
    }
}
