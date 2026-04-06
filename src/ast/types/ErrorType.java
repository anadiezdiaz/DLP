package ast.types;

import ast.AbstractLocatable;
import ast.Locatable;
import ast.Type;
import errorhandler.ErrorHandler;
import semantic.Visitor;

import java.util.List;

public class ErrorType extends AbstractType {
    private final String messageString;

    public ErrorType(String messageString, Locatable locatable) {
        super(locatable.getLine(), locatable.getColumn());
        this.messageString = messageString;
        ErrorHandler.getInstance().addError(this);
    }

    public ErrorType(int line, int column, String messageString) {
        super(line, column);
        this.messageString = messageString;
        ErrorHandler.getInstance().addError(this);
    }

    public String getMessageString() {
        return messageString;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return "(" + getLine() + ", " + getColumn() + ") por: " + messageString;
    }

    @Override
    public Type arithmetic(Locatable locatable, Type other) {
        return this;
    }

    @Override
    public Type squareBrackets(Locatable locatable, Type other) {
        return this;
    }

    @Override
    public Type dot(Locatable locatable, String name) {
        return this;
    }

    @Override
    public Type comparison(Locatable locatable, Type other) {
        return this;
    }

    @Override
    public Type logic(Locatable locatable, Type other) {
        return this;
    }

    @Override
    public Type canBeCastTo(Locatable locatable, Type other) {
        return this;
    }

    @Override
    public Type parenthesis(Locatable locatable, List<Type> others) {
        return this;
    }

    @Override
    public void mustPromotes(Locatable locatable, Type other) {}

    @Override
    public void mustBeBuiltIn(Locatable locatable) {}

    @Override
    public void mustBeLogical(Locatable locatable){}
}
