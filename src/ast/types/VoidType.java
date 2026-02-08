package ast.types;

import ast.AbstractLocatable;
import ast.Type;

public class VoidType implements Type {
    private static VoidType instance;

    public static VoidType getInstance(){
        if(instance == null){
            instance = new VoidType();
        }
        return instance;
    }
}
