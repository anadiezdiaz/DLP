package ast.types;

import ast.AbstractLocatable;
import ast.Locatable;
import ast.Type;
import semantic.Visitor;

public class NumberType extends AbstractType {
    private static NumberType instance;

    public NumberType() {
        super(0, 0);
    }

    public static NumberType getInstance() {
        if (instance == null) {
            instance = new NumberType();
        }
        return instance;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public Type arithmetic(Locatable locatable, Type other) {
        if(other == IntType.getInstance() || other == CharType.getInstance())
            return IntType.getInstance();
        if(other == NumberType.getInstance())
            return NumberType.getInstance();
        return super.arithmetic(locatable, other);
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return this;
    }

    @Override
    public Type comparison(Locatable locatable, Type other) {
        if(other == IntType.getInstance() || other == CharType.getInstance() || other == NumberType.getInstance())
            return IntType.getInstance();
        return super.comparison(locatable, other);
    }

    @Override
    public Type canBeCastTo(Locatable locatable, Type other) {
        if(other == IntType.getInstance() || other == CharType.getInstance() || other == NumberType.getInstance())
            return other;
        return super.canBeCastTo(locatable, other);
    }

    @Override
    public void mustPromotes(Locatable locatable, Type other) {
        if (other == NumberType.getInstance() ||
                other == IntType.getInstance() ||
                other == CharType.getInstance()) {
            return;
        }
        super.mustPromotes(locatable, other);
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable){}

    @Override
    public String toString(){
        return "NumberType";
    }
}
