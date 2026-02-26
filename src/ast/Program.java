package ast;

import java.util.List;

public class Program {
    List<Definition> definitions;

    public Program(List<Definition> definitions){
        this.definitions = definitions;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }
}
