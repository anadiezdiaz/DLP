grammar TSmm;	

program: (var_definition|func_definition)* main_definiton EOF
    ;
var_definition: 'let' ID (',' ID)* ':' type ';'
    ;
func_definition: 'function' ID '(' arguments ')' ':' (simple_type | 'void') '{' func_body '}'
    ;
main_definiton: 'function' 'main' '(' ')' ':' 'void' '{' func_body '}'
    ;
func_body: var_definition* statement*
    ;
arguments: ID ':' simple_type (',' ID ':' simple_type)*
         | (ID ':' simple_type)?
    ;
expression: ID  '(' expression_list? ')'
           | '(' expression ')'
           | expression '[' expression ']'
           | expression '.' expression
           | '(' expression 'as' type ')'
           | '-' expression
           | '!' expression
           | expression ('*'|'/'|'%') expression
           | expression ('+'|'-') expression
           | expression ('>'|'>='|'<'|'<='|'!='|'==') expression
           | expression ('&&'|'||') expression
           | ID
           | INT_CONSTANT
           | CHAR_CONSTANT
           | REAL_CONSTANT
    ;
type: '[' INT_CONSTANT ']' type
     | '[' var_definition+ ']'
     | simple_type
    ;
simple_type: 'int'
           | 'number'
           | 'char'
    ;
statement: 'log' expression_list ';'
         | 'input' expression_list ';'
         | expression '=' expression ';'
         | 'if' '(' expression ')' block ('else' block)?
         | 'while' '(' expression ')' block
         | 'return' expression ';'
         | ID  '(' expression_list? ')' ';'
    ;
expression_list: expression (',' expression)*
    ;
block: statement
     | '{' statement* '}'
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