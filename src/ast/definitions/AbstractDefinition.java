package ast.definitions;

import ast.AbstractLocatable;
import ast.Definition;
import ast.Type;

public class AbstractDefinition extends AbstractLocatable implements Definition {
    String name;
    Type type;

    public AbstractDefinition(int line, int column, String name, Type type) {
        super(line, column);
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Type getType() {
        return this.type;
    }
}
