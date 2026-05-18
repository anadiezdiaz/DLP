package codegen;

import ast.Type;
import ast.types.CharType;
import ast.types.IntType;
import ast.types.NumberType;
import java.io.IOException;
import java.io.PrintWriter;

public class CodeGenerator {
    private PrintWriter out;
    private int labels = 0;

    public CodeGenerator(String outputFilename, String sourceFilename){
        try{
            this.out = new PrintWriter(outputFilename);
            source(sourceFilename);
        } catch (IOException e) {
            System.err.println("Error opening the file " + outputFilename);
            System.exit(-1);
        }
    }

    public void push(Type type, Object object){
        out.println("\tpush" + type.suffix() + "\t" + object);
        out.flush();
    }

    public void pusha(int address){
        out.println("\tpusha\t" + address);
        out.flush();
    }

    public void pushbp(){
        out.println("\tpush\tbp");
        out.flush();
    }

    public void load(Type type){
        out.println("\tload" + type.suffix());
        out.flush();
    }

    public void store(Type type) {
        out.println("\tstore" + type.suffix());
        out.flush();
    }

    public void pop(Type type){
        out.println("\tpop" + type.suffix());
        out.flush();
    }

    public void dup(Type type){
        out.println("\tdup" + type.suffix());
        out.flush();
    }

    public void arithmetic(Type type, String op){
        switch(op){
            case "+": add(type); break;
            case "-": sub(type); break;
            case "*": mul(type); break;
            case "/": div(type); break;
            case "%": mod(type); break;
        }
    }

    public void add(Type type){
        out.println("\tadd" + type.suffix());
        out.flush();
    }

    public void sub(Type type){
        out.println("\tsub" + type.suffix());
        out.flush();
    }

    public void mul(Type type){
        out.println("\tmul" + type.suffix());
        out.flush();
    }

    public void div(Type type){
        out.println("\tdiv" + type.suffix());
        out.flush();
    }

    public void mod(Type type){
        out.println("\tmod" + type.suffix());
        out.flush();
    }

    public void comparison(Type type, String op){
        switch (op){
            case ">": gt(type); break;
            case "<": lt(type); break;
            case ">=": ge(type); break;
            case "<=": le(type); break;
            case "==": eq(type); break;
            case "!=": ne(type); break;
        }
    }

    public void gt(Type type){
        out.println("\tgt" + type.suffix());
        out.flush();
    }

    public void lt(Type type){
        out.println("\tlt" + type.suffix());
        out.flush();
    }

    public void ge(Type type){
        out.println("\tge" + type.suffix());
        out.flush();
    }

    public void le(Type type){
        out.println("\tle" + type.suffix());
        out.flush();
    }

    public void eq(Type type){
        out.println("\teq" + type.suffix());
        out.flush();
    }

    public void ne(Type type){
        out.println("\tne" + type.suffix());
        out.flush();
    }

    public void logical(String op){
        switch(op){
            case "&&": and(); break;
            case "||": or(); break;
            case "!": not(); break;
        }
    }

    public void logicalAssignment(String op){
        switch(op){
            case "&&=": and(); break;
            case "||=": or(); break;
        }
    }

    public void and(){
        out.println("\tand");
        out.flush();
    }

    public void or(){
        out.println("\tor");
        out.flush();
    }

    public void not(){
        out.println("\tnot");
        out.flush();
    }

    public void input(Type type){
        out.println("\tin" + type.suffix());
        out.flush();
    }

    public void output(Type type){
        out.println("\tout" + type.suffix());
        out.flush();
    }

    public void convertTo(Type from, Type to){
        if(from.suffix() == to.suffix()){
            return;
        }
        if(from == CharType.getInstance()){
            out.println("\tb2i");
            if(to == NumberType.getInstance()){
                out.println("\ti2f");
            }
        }else if(from == IntType.getInstance()){
            if(to == CharType.getInstance()){
                out.println("\ti2b");
            }else if(to == NumberType.getInstance()){
                out.println("\ti2f");
            }
        }else if(from == NumberType.getInstance()){
            out.println("\tf2i");
            if(to == CharType.getInstance()){
                out.println("\ti2b");
            }
        }
        out.flush();
    }

    public void jmp(String label){
        out.println("\tjmp\t" + label);
        out.flush();
    }

    public void jz(String label){
        out.println("\tjz\t" + label);
        out.flush();
    }

    public void jnz(String label){
        out.println("\tjnz\t" + label);
        out.flush();
    }

    public void call(String id){
        out.println("\tcall " + id);
        out.flush();
    }

    public void callIdent(String id){
        out.println("\tcall\t" + id);
        out.flush();
    }

    public void enter(int bytes){
        out.println("\tenter\t" + bytes);
        out.flush();
    }

    public void ret(int bytesToReturn, int bytesLocalVariables, int bytesParameters){
        out.println("\tret\t" + bytesToReturn + ", " + bytesLocalVariables + ", " + bytesParameters);
        out.flush();
    }

    public void halt(){
        out.println("halt");
        out.flush();
    }

    public void source(String sourceFilename){
        out.println();
        out.println("#source\t\"" + sourceFilename + "\"");
        out.println();
        out.flush();
    }

    public void line(int line){
        out.println();
        out.println("#line\t" + line);
        out.flush();
    }

    public void mainInvocation(){
        out.println();
        out.println("' Invocation to the main function");
        out.println("call main");
        halt();
        out.println();
        out.flush();
    }

    public void comment(String comment) {
        out.println("\t" + comment);
        out.flush();
    }

    public String getLabel() {
        return "label" + this.labels++;
    }

    public void label(String label) {
        out.println();
        out.println(" " + label + ":");
        out.flush();
    }
}