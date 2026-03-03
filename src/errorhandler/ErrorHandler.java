package errorhandler;

import ast.types.ErrorType;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {
    List<ErrorType> errors;
    private static ErrorHandler instance;

    private ErrorHandler(){
        this.errors = new ArrayList<ErrorType>();
    }

    public static ErrorHandler getInstance() {
        if (instance == null) {
            instance = new ErrorHandler();
        }
        return instance;
    }

    public void addError(ErrorType e) {
        this.errors.add(e);
    }

    public void showErrors(PrintStream p) {
        for (ErrorType e : errors) {
            p.println(e.toString());
        }
    }

    public boolean anyError() {
        return !this.errors.isEmpty();
    }
}
