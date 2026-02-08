package ast;

public class AbstractLocatable implements Locatable{
    int line;
    int column;

    public AbstractLocatable(int line, int column){
        this.line = line;
        this.column = column;
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public int getColumn() {
        return this.column;
    }
}
