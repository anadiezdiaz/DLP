package ast;

import semantic.Visitor;

import java.util.List;

public class Program {
    List<Definition> definitions;

    public Program(List<Definition> definitions){
        this.definitions = definitions;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }
}
