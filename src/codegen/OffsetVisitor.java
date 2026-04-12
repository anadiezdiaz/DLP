package codegen;

import ast.Statement;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.types.FunctionType;
import ast.types.RecordField;
import ast.types.RecordType;
import semantic.AbstractVisitor;

public class OffsetVisitor extends AbstractVisitor<Void, Void> {
    int byteSum;

    @Override
    public Void visit(FuncDefinition f, Void p) {
        f.getType().accept(this, p);
        byteSum = 0;
        for (Statement s : f.getStatements()) {
            s.accept(this, p);
        }
        return null;
    }

    @Override
    public Void visit(VarDefinition v, Void p) {
        if (v.getScope() == 0) {
            v.setOffset(byteSum);
            byteSum += v.getType().getNumberOfBytes();
        } else if (!v.isParam()) {
            byteSum += v.getType().getNumberOfBytes();
            v.setOffset(-byteSum);
        }
        v.getType().accept(this, p);
        return null;
    }

    @Override
    public Void visit(FunctionType f, Void p) {
        int paramOffset = 4;
        for (int i = f.getParameters().size() - 1; i >= 0; i--) {
            VarDefinition param = f.getParameters().get(i);
            param.setParam(true);
            param.setOffset(paramOffset);
            paramOffset += param.getType().getNumberOfBytes();
        }
        f.getReturnType().accept(this, p);
        return null;
    }

    @Override
    public Void visit(RecordType r, Void p) {
        int recordOffset = 0;
        for (RecordField rf : r.getRecordFields()) {
            rf.setOffset(recordOffset);
            recordOffset += rf.getType().getNumberOfBytes();
            rf.getType().accept(this, p);
        }
        return null;
    }
}
