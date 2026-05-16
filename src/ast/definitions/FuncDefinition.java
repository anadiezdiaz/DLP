package ast.definitions;

import ast.Statement;
import ast.Type;
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
        int numOfBytes = 0;
        for (Statement s : statements) {
            if(s instanceof VarDefinition){
                numOfBytes += ((VarDefinition) s).getType().getNumberOfBytes();
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
