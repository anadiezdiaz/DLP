package ast.definitions;

import ast.Definition;
import ast.Statement;
import ast.Type;
import ast.types.RecordField;
import semantic.Visitor;

import java.util.Objects;

public class VarDefinition extends AbstractDefinition implements Statement{
    int offset;
    boolean isParam;

    public VarDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VarDefinition that = (VarDefinition) o;
        return Objects.equals(name, that.name);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isParam() {
        return isParam;
    }

    public void setParam(boolean param) {
        isParam = param;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
