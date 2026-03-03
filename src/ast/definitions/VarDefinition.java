package ast.definitions;

import ast.Definition;
import ast.Statement;
import ast.Type;
import ast.types.RecordField;

import java.util.Objects;

public class VarDefinition extends AbstractDefinition implements Statement{

    public VarDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VarDefinition that = (VarDefinition) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
