package ast;

public interface Definition extends Locatable{
    String getName();
    Type getType();
}
