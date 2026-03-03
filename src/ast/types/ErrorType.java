package ast.types;

import ast.AbstractLocatable;
import ast.Locatable;
import ast.Type;
import errorhandler.ErrorHandler;

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

    @Override
    public String toString() {
        return "(" + getLine() + ", " + getColumn() + ") por: " + messageString;
    }
}
