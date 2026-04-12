package ast.types;

import ast.AbstractLocatable;
import ast.Type;
import semantic.Visitor;

import java.util.Objects;

public class RecordField extends AbstractType {
    String name;
    Type type;
    int offset;

    public RecordField(String name, Type type) {
        super(0,0);
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
    public int getNumberOfBytes(){
        return type.getNumberOfBytes();
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

}
