package ast.types;

import ast.Locatable;
import ast.Type;
import semantic.Visitor;

public class BooleanType extends AbstractType {
    private static BooleanType instance;

    private BooleanType() {
        super(0, 0);
    }

    public static BooleanType getInstance() {
        if (instance == null) {
            instance = new BooleanType();
        }
        return instance;
    }

    @Override
    public Type comparison(Locatable locatable, Type other) {
        if (other == BooleanType.getInstance()) {
            return BooleanType.getInstance();
        }
        return super.comparison(locatable, other);
    }

    @Override
    public Type logic(Locatable locatable, Type other) {
        if (other == BooleanType.getInstance()) {
            return BooleanType.getInstance();
        }
        return super.logic(locatable, other);
    }

    @Override
    public Type logic(Locatable locatable) {
        return BooleanType.getInstance();
    }

    @Override
    public void mustBeLogical(Locatable locatable) {}

    @Override
    public void mustPromotes(Locatable locatable, Type other) {
        if (other == BooleanType.getInstance()) {
            return;
        }
        super.mustPromotes(locatable, other);
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {}

    @Override
    public int getNumberOfBytes() {
        return 2;
    }

    @Override
    public char suffix() {
        return 'i';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public String toString() {
        return "BooleanType";
    }
}
