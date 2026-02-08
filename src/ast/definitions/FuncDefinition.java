package ast.definitions;

import ast.Definition;
import ast.Statement;
import ast.Type;

import java.util.List;

public class FuncDefinition extends AbstractDefinition {
    List<Statement> statements;

    public FuncDefinition(int line, int column, String name, Type type, List<Statement> statements) {
        super(line, column, name, type);
        this.statements = statements;
    }
}
