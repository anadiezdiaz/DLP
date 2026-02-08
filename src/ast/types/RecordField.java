package ast.types;

import ast.AbstractLocatable;
import ast.Type;

public class RecordField implements Type {
    String name;
    Type type;

    public RecordField(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return this.name;
    }

    public Type getType(){
        return this.type;
    }
}
