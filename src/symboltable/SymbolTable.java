package symboltable;

import ast.Definition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTable {
    private int scope=  0;
    private List<Map<String,Definition>> table;

    public SymbolTable(){
        table = new ArrayList<>();
        table.add(new HashMap<>());
    }

    //metodo para meter un ambito nuevo
    public void set(){
        scope++;
        table.add(new HashMap<>());
    }

    //metodo para quitar un ambito (el local)
    public void reset(){
        table.remove(scope);
        scope--;
    }

    //si se puede insertar la definicion devuelve true
    public boolean insert(Definition definition) {
        if (findInCurrentScope(definition.getName())) {
            return false;
        }
        definition.setScope(scope);
        table.get(scope).put(definition.getName(), definition);
        return true;
    }

    //buscar la definicion, si no la encuentra devuelve null
    public Definition find(String id) {
        for (int i = scope; i >= 0; i--) {
            if (table.get(i).containsKey(id)) {
                return table.get(i).get(id);
            }
        }
        return null;
    }

    //package-protected for testing pourposes
    //saber si esta en el ambito actual
    boolean findInCurrentScope(String id) {
        return table.get(scope).get(id) != null;
    }
}