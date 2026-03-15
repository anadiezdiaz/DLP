package ast.types;

import ast.AbstractLocatable;
import ast.Locatable;
import ast.Type;
import errorhandler.ErrorHandler;
import semantic.Visitor;

public class ErrorType extends AbstractLocatable implements Type {
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
}
