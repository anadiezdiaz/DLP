grammar TSmm;

@header{
import ast.definitions.*;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import ast.*;
}

program returns [Program ast] locals [List<Definition> defs = new ArrayList<Definition>()]:
    (definition {$defs.addAll($definition.ast);})* EOF {$ast = new Program($defs);}
    ;
definition returns [List<Definition> ast = new ArrayList<Definition>()]:
    (d1=var_definition{$ast.addAll($d1.ast);}|d2=func_definition{$ast.add($d2.ast);})* d3=main_definition {$ast.add($d3.ast);}
    ;
var_definition returns [List<VarDefinition> ast = new ArrayList<VarDefinition>()] locals [List<Variable> vars = new ArrayList<Variable>()]:
    'let' ID1=ID{$vars.add(new Variable($ID1.getLine(), $ID1.getCharPositionInLine()+1, $ID1.text));} (',' ID2=ID{$vars.add(new Variable($ID2.getLine(), $ID2.getCharPositionInLine()+1, $ID2.text));})* ':' t1=type ';'
    {
        for(Variable v : $vars)
        {
            $ast.add(new VarDefinition(v.getLine(), v.getColumn(), v.getName(), $t1.ast));
        }
    }
    ;
func_definition returns [FuncDefinition ast] locals [Type returnType = VoidType.getInstance()]:
    'function' ID '(' arguments ')' ':' (type{$returnType = $type.ast;})? 'void'? '{' func_body '}'
    {$ast = new FuncDefinition($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text, new FunctionType($returnType, $arguments.ast), $func_body.ast);}
    ;
arguments returns [List<VarDefinition> ast = new ArrayList<VarDefinition>()]:
    ID1=ID ':' s1=type {$ast.add(new VarDefinition($ID1.getLine(), $ID1.getCharPositionInLine()+1, $ID1.text, $s1.ast));}(',' ID2=ID ':' s2=type{$ast.add(new VarDefinition($ID2.getLine(), $ID2.getCharPositionInLine()+1, $ID2.text, $s2.ast));})*
    | (ID1=ID ':' s1=type{$ast.add(new VarDefinition($ID1.getLine(), $ID1.getCharPositionInLine()+1, $ID1.text, $s1.ast));})?
    ;
main_definition returns [FuncDefinition ast]:
    f='function' 'main' '(' ')' ':' 'void' '{' func_body '}'
    {$ast = new FuncDefinition($f.getLine(), $f.getCharPositionInLine()+1, "main", new FunctionType(VoidType.getInstance(), new ArrayList<>()), $func_body.ast);}
    ;
func_body returns [List<Statement> ast = new ArrayList<Statement>()]:
    (var_definition{$ast.addAll($var_definition.ast);})* (statement{$ast.addAll($statement.ast);})*
    ;
expression returns [Expression ast]:
    '(' e1=expression ')' {$ast = $e1.ast;}
    | e1=expression '[' e2=expression ']' {$ast = new ArrayAccess($e1.ast.getLine(), $e2.ast.getColumn(), $e1.ast, $e2.ast);}
    | e1=expression '.' ID {$ast = new FieldAccess($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $ID.text);}
    | '(' e1=expression 'as' t1=type ')' {$ast = new Cast($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $t1.ast);}
    | '-' e1=expression {$ast = new UnaryMinus($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast);}
    | '!' e1=expression {$ast = new UnaryNot($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast);}
    | e1=expression OP=('*'|'/'|'%') e2=expression {$ast = new Arithmetic($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $e2.ast, $OP.text);}
    | e1=expression OP=('+'|'-') e2=expression {$ast = new Arithmetic($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $e2.ast, $OP.text);}
    | e1=expression OP=('>'|'>='|'<'|'<='|'!='|'==') e2=expression {$ast = new Comparisson($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $e2.ast, $OP.text);}
    | e1=expression OP=('&&'|'||') e2=expression {$ast = new Logic($e1.ast.getLine(), $e2.ast.getColumn(), $e1.ast, $e2.ast, $OP.text);}
    | ID {$ast = new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text);}
    | INT_CONSTANT {$ast = new IntLiteral($INT_CONSTANT.getLine(), $INT_CONSTANT.getCharPositionInLine()+1, LexerHelper.lexemeToInt($INT_CONSTANT.text));}
    | CHAR_CONSTANT {$ast = new CharLiteral($CHAR_CONSTANT.getLine(), $CHAR_CONSTANT.getCharPositionInLine()+1, LexerHelper.lexemeToChar($CHAR_CONSTANT.text));}
    | REAL_CONSTANT {$ast = new NumberLiteral($REAL_CONSTANT.getLine(), $REAL_CONSTANT.getCharPositionInLine()+1, LexerHelper.lexemeToReal($REAL_CONSTANT.text));}
    | f=func_invocation {$ast = $f.ast;}
    ;
type returns [Type ast]:
    '[' INT_CONSTANT ']' type {$ast = new ArrayType(LexerHelper.lexemeToInt($INT_CONSTANT.text), $type.ast);}
    | '[' (recordFields{$ast = new RecordType($recordFields.ast);})+ ']'
    | 'int' {$ast = IntType.getInstance();}
    | 'number' {$ast = NumberType.getInstance();}
    | 'char' {$ast = CharType.getInstance();}
    ;
recordFields returns [List<RecordField> ast = new ArrayList<RecordField>()] locals [List<VarDefinition> vars = new ArrayList<VarDefinition>()]:
    (var_definition{$vars.addAll($var_definition.ast);})+
    {
        for(VarDefinition v : $vars)
        {
            $ast.add(new RecordField(v.getName(), v.getType()));
        }
    }
    ;
statement returns [List<Statement> ast = new ArrayList<Statement>();] locals [List<Statement> elseList = new ArrayList<Statement>()]:
    log='log' expression_list ';' {$ast.add(new LogStatement($log.getLine(), $log.getCharPositionInLine()+1,$expression_list.ast));}
    | input='input' expression_list ';' {$ast.add(new InputStatement($input.getLine(), $input.getCharPositionInLine()+1,$expression_list.ast));}
    | e1=expression '=' e2=expression ';' {$ast.add(new Assignment($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $e2.ast));}
    | 'if' '(' e1=expression ')' b1=block ('else' b2=block {$elseList = $b2.ast;})? {$ast.add(new IfElseStatement($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $b1.ast, $elseList));}
    | 'while' '(' e1=expression ')' b1=block {$ast.add(new WhileStatement($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $b1.ast));}
    | 'return' e1=expression ';' {$ast.add(new ReturnStatement($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast));}
    | f=func_invocation ';' {$ast.add($f.ast);}
    ;
func_invocation returns [FunctionInvocation ast] locals [List<Expression> params = new ArrayList<Expression>()]:
    ID  '(' (expression_list{$params=$expression_list.ast;})? ')'
    {
        $ast = new FunctionInvocation($ID.getLine(), $ID.getCharPositionInLine()+1, $params,
            new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text));
    }
    ;
expression_list returns [List<Expression> ast = new ArrayList<Expression>();]:
    e1=expression {$ast.add($e1.ast);}(',' e2=expression {$ast.add($e2.ast);})*
    ;
block returns [List<Statement> ast = new ArrayList<Statement>()]:
    statement {$ast.addAll($statement.ast);}
    | '{' (statement{$ast.addAll($statement.ast);})* '}'
    ;

INT_CONSTANT: [1-9] [0-9]*
            | '0'
    ;
SIMPLE_COMMENT: '//' .*? ('\n'|EOF) -> skip
    ;
MULTIPLE_COMMENT: '/*' .*? '*/' -> skip
    ;
CHAR_CONSTANT: '\'' ( ~['\\] | '\\' [nt] | '\\' [0-9]+) '\''
    ;
WHITESPACES: [ \n\t\r]+ -> skip
    ;
EXP: [eE] [+-]? INT_CONSTANT
    ;
REAL_CONSTANT: INT_CONSTANT '.' [0-9]* EXP?
    | '.' [0-9]+ EXP?
    | INT_CONSTANT+ EXP
    ;
ID: [a-zA-Z_][a-zA-Z0-9_]*
    ;