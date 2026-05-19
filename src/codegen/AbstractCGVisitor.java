package codegen;

import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import semantic.Visitor;

public abstract class AbstractCGVisitor<TP, TR> implements Visitor<TP, TR> {
    public CodeGenerator codeGenerator;

    @Override
    public TR visit(Program pr, TP p) {
        throw new UnsupportedOperationException("CG Error in Program");
    }

    @Override
    public TR visit(FuncDefinition f, TP p) {
        throw new UnsupportedOperationException("CG Error in FuncDefinition");
    }

    @Override
    public TR visit(VarDefinition v, TP p) {
        throw new UnsupportedOperationException("CG Error in VarDefinition");
    }

    @Override
    public TR visit(Arithmetic a, TP p) {
        throw new UnsupportedOperationException("CG Error in Arithmetic");
    }

    @Override
    public TR visit(ArrayAccess a, TP p) {
        throw new UnsupportedOperationException("CG Error in ArrayAccess");
    }

    @Override
    public TR visit(Cast c, TP p) {
        throw new UnsupportedOperationException("CG Error in Cast");
    }

    @Override
    public TR visit(CharLiteral c, TP p) {
        throw new UnsupportedOperationException("CG Error in CharLiteral");
    }

    @Override
    public TR visit(Comparison c, TP p) {
        throw new UnsupportedOperationException("CG Error in Comparison");
    }

    @Override
    public TR visit(FieldAccess f, TP p) {
        throw new UnsupportedOperationException("CG Error in FieldAccess");
    }

    @Override
    public TR visit(FunctionInvocation f, TP p) {
        throw new UnsupportedOperationException("CG Error in FunctionInvocation");
    }

    @Override
    public TR visit(IntLiteral i, TP p) {
        throw new UnsupportedOperationException("CG Error in IntLiteral");
    }

    @Override
    public TR visit(Logic l, TP p) {
        throw new UnsupportedOperationException("CG Error in Logic");
    }

    @Override
    public TR visit(NumberLiteral n, TP p) {
        throw new UnsupportedOperationException("CG Error in NumberLiteral");
    }

    @Override
    public TR visit(UnaryMinus u, TP p) {
        throw new UnsupportedOperationException("CG Error in UnaryMinus");
    }

    @Override
    public TR visit(UnaryNot u, TP p) {
        throw new UnsupportedOperationException("CG Error in UnaryNot");
    }

    @Override
    public TR visit(Variable v, TP p) {
        throw new UnsupportedOperationException("CG Error in Variable");
    }

    @Override
    public TR visit(Assignment a, TP p) {
        throw new UnsupportedOperationException("CG Error in Assignment");
    }

    @Override
    public TR visit(IfElseStatement i, TP p) {
        throw new UnsupportedOperationException("CG Error in IfElseStatement");
    }

    @Override
    public TR visit(Len l, TP p) {
        throw new UnsupportedOperationException("CG Error in Len");
    }

    @Override
    public TR visit(InputStatement i, TP p) {
        throw new UnsupportedOperationException("CG Error in InputStatement");
    }

    @Override
    public TR visit(LogStatement l, TP p) {
        throw new UnsupportedOperationException("CG Error in LogStatement");
    }

    @Override
    public TR visit(ReturnStatement r, TP p) {
        throw new UnsupportedOperationException("CG Error in ReturnStatement");
    }

    @Override
    public TR visit(WhileStatement w, TP p) {
        throw new UnsupportedOperationException("CG Error in WhileStatement");
    }

    @Override
    public TR visit(ArrayType a, TP p) {
        throw new UnsupportedOperationException("CG Error in ArrayType");
    }

    @Override
    public TR visit(CharType c, TP p) {
        throw new UnsupportedOperationException("CG Error in CharType");
    }

    @Override
    public TR visit(ErrorType e, TP p) {
        throw new UnsupportedOperationException("CG Error in ErrorType");
    }

    @Override
    public TR visit(FunctionType f, TP p) {
        throw new UnsupportedOperationException("CG Error in FunctionType");
    }

    @Override
    public TR visit(IntType i, TP p) {
        throw new UnsupportedOperationException("CG Error in IntType");
    }

    @Override
    public TR visit(NumberType n, TP p) {
        throw new UnsupportedOperationException("CG Error in NumberType");
    }

    @Override
    public TR visit(RecordField r, TP p) {
        throw new UnsupportedOperationException("CG Error in RecordField");
    }

    @Override
    public TR visit(RecordType r, TP p) {
        throw new UnsupportedOperationException("CG Error in RecordType");
    }

    @Override
    public TR visit(VoidType v, TP p) {
        throw new UnsupportedOperationException("CG Error in VoidType");
    }
}
