package ast.types;

import ast.AbstractLocatable;
import ast.Type;

import java.util.List;

public class RecordType implements Type {
    List<RecordField> recordFields;

    public RecordType(List<RecordField> recordFields) {
        this.recordFields = recordFields;
    }

    public List<RecordField> getRecordFields() {
        return this.recordFields;
    }
}
