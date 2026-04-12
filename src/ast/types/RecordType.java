package ast.types;

import ast.AbstractLocatable;
import ast.Locatable;
import ast.Type;
import semantic.Visitor;

import java.sql.Ref;
import java.util.List;

public class RecordType extends AbstractType {
    List<RecordField> recordFields;

    public RecordType(List<RecordField> recordFields) {
        super(0,0);
        this.recordFields = recordFields;
    }

    public List<RecordField> getRecordFields() {
        return this.recordFields;
    }

    public<TP, TR> TR accept(Visitor<TP, TR> v, TP tp) {
        return v.visit(this, tp);
    }

    @Override
    public Type dot(Locatable locatable, String name) {
        for (RecordField recordField : recordFields){
            if(recordField.getName().equals(name)){
                return recordField.getType();
            }
        }
        return super.dot(locatable, name);
    }

    @Override
    public int getNumberOfBytes(){
        int total = 0;
        for(RecordField r : recordFields){
            total += r.getNumberOfBytes();
        }
        return total;
    }

    @Override
    public String toString(){
        return "RecordType";
    }
}
