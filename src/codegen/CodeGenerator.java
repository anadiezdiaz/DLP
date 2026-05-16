package codegen;

import ast.Type;
import ast.types.CharType;
import ast.types.IntType;
import ast.types.NumberType;
import java.io.IOException;
import java.io.PrintWriter;

public class CodeGenerator {
    private PrintWriter out;
    private int labels = 1;

    public CodeGenerator(String outputFilename, String sourceFilename){
        try{
            this.out = new PrintWriter(outputFilename);
        } catch (IOException e) {
            System.err.println("Error opening the file " + outputFilename);
            System.exit(-1);
        }
    }

    /*
    Push instructions
        pushb <ASCII_code> -> Pushes the character (1 byte) onto the stack
        push[i] <int_constant> -> Pushes the integer literal (2 bytes) onto the stack
        pushf <real_constant> -> Pushes the real number (4 bytes) onto the stack
     */
    public void push(Type type, Object object){
        out.println("\tpush"+type.suffix() + " " + object);
        out.flush();
    }

    /*
        pusha <int_constant> -> Pushes the integer address (2 bytes) onto the stack
     */
    public void pusha(int address){
        out.println("\tpusha" + " " + address);
        out.flush();
    }

    /*
        push[a] bp -> Pushes the value of the bp register (2 bytes)
     */
    public void pushbp(){
        out.println("\tpushbp");
        out.flush();
    }

    /*
    loadb, load[i], loadf
        Pop a memory address off the stack (2 bytes).
		Then, they push onto the stack the content (1, 2 or 4 bytes) of
		the address popped in the previous point
     */
    public void load(Type type){
        out.println("\tload"+type.suffix());
        out.flush();
    }

    /*
    storeb, store[i], storef
        Pop from the stack 1, 2 or 4 bytes.
		Then, they pop from the stack a memory address (2 bytes).
		The content of the memory address is replaced with the value
		popped in the first step
     */
    public void store(Type type) {
        out.println("\tstore"+type.suffix());
        out.flush();
    }

    /*
        popb, pop[i], popf -> Pop 1, 2 or 4 bytes, respectively, off the stack
     */
    public void pop(Type type){
        out.println("\tpop"+type.suffix());
        out.flush();
    }

    /*
        dupb, dup[i], dupf -> Duplicate the 1, 2 or 4 bytes, respectively, on the top of the stack
     */
    public void dup(Type type){
        out.println("\tdup"+type.suffix());
        out.flush();
    }

    /*
    Arithmetic operations. They pop two operands, perform the operation and push the result.
     */
    public void arithmetic(Type type, String op){
        switch(op){
            case "+": add(type);
            case "-": sub(type);
            case "*": mul(type);
            case "/": div(type);
            case "%": mod(type);
        }
    }

    /*
        add[i], addf -> For addition
     */
    public void add(Type type){
        out.println("\tadd"+type.suffix());
        out.flush();
    }

    /*
        sub[i], subf -> For subtraction
     */
    public void sub(Type type){
        out.println("\tsub"+type.suffix());
        out.flush();
    }

    /*
        mul[i], mulf -> For multiplication
     */
    public void mul(Type type){
        out.println("\tmul"+type.suffix());
        out.flush();
    }

    /*
        div[i], divf -> For division
     */
    public void div(Type type){
        out.println("\tdiv"+type.suffix());
        out.flush();
    }

    /*
        mod[i] -> For modulus
     */
    public void mod(Type type){
        out.println("\tmod"+type.suffix());
        out.flush();
    }

    /*
        Comparison operations. They pop two operands, perform the operation and push the result.
     */
    public void comparison(Type type, String op){
        switch (op){
            case ">": gt(type);
            case "<": lt(type);
            case ">=": ge(type);
            case "<=": le(type);
            case "==": eq(type);
            case "!=": ne(type);
        }
    }

    /*
        gt[i], gtf -> For "greater than" comparison
     */
    public void gt(Type type){
        out.println("\tgt"+type.suffix());
        out.flush();
    }

    /*
        lt[i], ltf -> For "lower than" comparison
     */
    public void lt(Type type){
        out.println("\tlt"+type.suffix());
        out.flush();
    }

    /*
        ge[i], gef -> For "greater or equal" comparison
     */
    public void ge(Type type){
        out.println("\tge"+type.suffix());
        out.flush();
    }

    /*
        le[i], lef -> For "lower or equal than" comparison
     */
    public void le(Type type){
        out.println("\tle"+type.suffix());
        out.flush();
    }

    /*
        eq[i], eqf -> For "equal to" comparison
     */
    public void eq(Type type){
        out.println("\teq"+type.suffix());
        out.flush();
    }

    /*
        ne[i], nef -> For "not equal" comparison
     */
    public void ne(Type type){
        out.println("\tne"+type.suffix());
        out.flush();
    }

    /*
    Logical operations. Pop one or two operands, perform the operation and push the result.
     */
    public void logical(String op){
        switch(op){
            case "&&": and();
            case "||": or();
            case "!": not();
        }
    }

    /*
        and -> For the "and" logical operation
     */
    public void and(){
        out.println("\tand");
        out.flush();
    }

    /*
        or -> For the "or" logical operation
     */
    public void or(){
        out.println("\tor");
        out.flush();
    }

    /*
        not -> For the unary "not" logical operation
     */
    public void not(){
        out.println("\tnot");
        out.flush();
    }

    /*
    Input
        inb, in[i], inf -> Read a value from the keyboard and pushes it onto the stack
     */
    public void input(Type type){
        out.println("\tin"+type.suffix());
        out.flush();
    }

    /*
    Output
        outb, out[i], outf -> Pop one value off the stack and shows it in the console
     */
    public void output(Type type){
        out.println("\tout"+type.suffix());
        out.flush();
    }

    /*
    Conversions
	    b2i -> Pops one character and pushes it as an integer
	    i2f -> Pops one integer and pushes it as a real number
	    f2i -> Pops one real number and pushes it as an integer
	    i2b -> Pops one integer and pushes it as a character
     */
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

    /*
    Jumps
        <id>: -> Defines one label for jumps and invocations (functions).
	    jmp <label> -> Jumps (unconditionally) to the label specified as a parameter.
	    jz <label> -> Pops one integer and jumps to the label if the popped integer is zero.
	    jnz <label> -> Pops one integer and jumps to the label if the popped integer is not zero.
     */
    public void jmp(String label){
        out.println("\tjmp" + " " + label + ":");
        out.flush();
    }

    public void jz(String label){
        out.println("\tjz" + " " + label + ":");
        out.flush();
    }

    public void jnz(String label){
        out.println("\tjnz" + " " + label + ":");
        out.flush();
    }

    public void functions(){
        //TODO
    }

    /*
        call <id> -> Invokes the <id> function
     */
    public void call(String id){
        out.println("\tcall" + " " + id);
        out.flush();
    }

    /*
        enter <int_constant> -> Allocates <int_constant> bytes on the top of the stack
     */
    public void enter(int bytes){
        out.println("\tenter" + " " + bytes);
        out.flush();
    }

    /*
    ret -> Returns from a function invocation.
	    <int_constant> -> The first constant represents the bytes to return;
	    <int_constant> -> the second one, the bytes of all the local variables;
        <int_constant> -> and the last one, the bytes of all the parameters.
     */
    public void ret(int bytesToReturn, int bytesLocalVariables, int bytesParameters){
        out.println("\tret" + " " + bytesToReturn + ", " + bytesLocalVariables + ", " + bytesParameters);
        out.flush();
    }

    /*
        halt -> Terminates the program execution
     */
    public void halt(){
        out.println("\thalt");
        out.flush();
    }

    /*
    Debugging info
	    #source <string_constant> -> Allows the MAPL IDE to associate assembly code
					                 to the high-level source program
	    #line <INT_CONSTANT> -> Allows the MAPL IDE to associate the assembly code
					            corresponding to each high-level statement
     */
    public void debuggingInfo(String s_constant, int i_constant){
        out.println("\t#source" + " " + s_constant);
        out.println("\t#line" + " " + i_constant);
        out.flush();
    }

    /*
        Additional method to call the main function
     */
    public void mainInvocation(){
        //TODO
        out.println();
        out.println("Main invocation");
        out.println("\tcall main");
        out.println("\thalt");
        out.flush();
    }

    /*
        Additional method to print a comment
     */
    public void comment(String comment) {
        out.println("\t"+comment);
        out.flush();
    }

    public String getLabel() {
        return "label" + this.labels++;
    }

    public void label(String label) {
        out.println(label + ":");
        out.flush();
    }
}
