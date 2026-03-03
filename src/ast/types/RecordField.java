package ast.types;

import ast.AbstractLocatable;
import ast.Type;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RecordField that = (RecordField) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
