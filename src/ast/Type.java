package ast;

import java.util.List;

public interface Type extends ASTNode {
    //el tipo no tiene ni línea ni columna, lo debería tener la definición de tipo
    //por ello voy a poner que extiende de ASTNode que no tiene ni tipo ni columna (es locatable quien lo tiene)

    Type arithmetic(Locatable locatable, Type other);
    Type arithmetic(Locatable locatable);
    Type comparison(Locatable locatable, Type other);
    Type logic(Locatable locatable, Type other);
    Type logic(Locatable locatable);
    void mustBeLogical(Locatable locatable);
    void mustPromotes(Locatable locatable, Type other);
    void mustBeBuiltIn(Locatable locatable);
    Type canBeCastTo(Locatable locatable, Type other);
    Type squareBrackets(Locatable locatable, Type other);
    Type dot(Locatable locatable, String name);
    Type parenthesis(Locatable locatable, List<Type> others);
    int getNumberOfBytes();
    char suffix();
    Type ternary(Type type1, Type type2, Locatable locatable);
}
