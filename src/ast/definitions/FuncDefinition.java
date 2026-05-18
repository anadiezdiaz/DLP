package ast.definitions;

import ast.Statement;
import ast.Type;
import ast.statements.BlockStatement;
import ast.statements.IfElseStatement;
import ast.statements.WhileStatement;
import semantic.Visitor;

import java.util.List;

public class FuncDefinition extends AbstractDefinition {
    List<Statement> statements;

    private int localBytes;

    public FuncDefinition(int line, int column, String name, Type type, List<Statement> statements) {
        super(line, column, name, type);
        this.statements = statements;
    }

    public void setLocalBytes(int localBytes){
        this.localBytes = localBytes;
    }

    public int getLocalBytes() {
        return getLocalBytes(statements);
    }

    private int getLocalBytes(List<Statement> statements) {
        int numOfBytes = 0;

        for (Statement s : statements) {
            if (s instanceof VarDefinition) {
                numOfBytes += ((VarDefinition) s).getType().getNumberOfBytes();
            } else if (s instanceof BlockStatement) {
                numOfBytes += getLocalBytes(((BlockStatement) s).getStatements());
            } else if (s instanceof IfElseStatement) {
                numOfBytes += getLocalBytes(((IfElseStatement) s).getIfBody());
                numOfBytes += getLocalBytes(((IfElseStatement) s).getElseBody());
            } else if (s instanceof WhileStatement) {
                numOfBytes += getLocalBytes(((WhileStatement) s).getBody());
            }
        }

        return numOfBytes;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return "FuncDefinition [name=" + name + ", type=" + type + ", statements=" + statements + ", localBytes=" + localBytes + "]";
    }
}
