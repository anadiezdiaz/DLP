package ast.types;

import ast.AbstractLocatable;
import ast.Locatable;
import ast.Type;
import semantic.Visitor;

import java.util.List;

public class AbstractType extends AbstractLocatable implements Type {

    public AbstractType(int line, int column) {
        super(line, column);
    }

    @Override
    public Type arithmetic(Locatable locatable, Type other) {
        if(other instanceof ErrorType){
            return other;
        }
        return new ErrorType("Arithmetic operation not allowed for types " + other + " and " + this, locatable);
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return new ErrorType("Arithmetic operation not allowed for type " + this, locatable);
    }

    @Override
    public Type comparison(Locatable locatable, Type other) {
        if(other instanceof ErrorType){
            return other;
        }
        return new ErrorType("Comparison operation not allowed for types " + other + " and " + this, locatable);
    }

    @Override
    public Type logic(Locatable locatable, Type other) {
        if(other instanceof ErrorType){
            return other;
        }
        return new ErrorType("Logic operation not allowed for types " + other + " and " + this, locatable);
    }

    @Override
    public Type logic(Locatable locatable) {
        return new ErrorType("Logic operation not allowed for this type: " + this, locatable);

    }

    @Override
    public void mustBeLogical(Locatable locatable) {
        new ErrorType("Logical type expected", locatable);
    }

    @Override
    public void mustPromotes(Locatable locatable, Type other) {
        if(other instanceof ErrorType){
            return;
        }
        new ErrorType("Type " + other + " cannot promote type " + this, locatable);
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
        new ErrorType("BuiltIn type expected", locatable);
    }

    @Override
    public Type canBeCastTo(Locatable locatable, Type other) {
        if(other instanceof ErrorType){
            return other;
        }
        return new ErrorType("Cast operation not allowed, expected " + other + " and received " + this, locatable);
    }

    @Override
    public Type squareBrackets(Locatable locatable, Type other) {
        if(other instanceof ErrorType){
            return other;
        }
        return new ErrorType("SquareBrackets operation only allowed for ArrayType and actual type is " + this, locatable);
    }

    @Override
    public Type dot(Locatable locatable, String name) {
        return new ErrorType("Record field " + name + " has not been defined in record", locatable);
    }

    @Override
    public Type parenthesis(Locatable locatable, List<Type> others) {
        StringBuilder typesOfParams = new StringBuilder();
        for(Type other : others){
            if(other instanceof ErrorType){
                return other;
            }
            typesOfParams.append(other.toString()).append(" ");
        }
        return new ErrorType("Parenthesis with wrong parameters: " + typesOfParams.toString(), locatable);
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return null;
    }
}
