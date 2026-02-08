package ast.definitions;

import ast.Definition;
import ast.Statement;
import ast.Type;

public class VarDefinition extends AbstractDefinition {
    Statement statement;

    public VarDefinition(int line, int column, String name, Type type, Statement statement) {
        super(line, column, name, type);
        this.statement = statement;
    }

    public Statement getStatement(){
        return this.statement;
    }
}
