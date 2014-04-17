/* Simple expressions, allowing JISON to control precedence */
/* lexical grammar */


%lex
%%

\s+                   /* skip whitespace */
[0-9]+("."[0-9]+)?\b  return 'NUMBER'
"*"                   return '*'
"/"                   return '/'
"-"                   return '-'
"+"                   return '+'
"^"                   return '^'
"%"		              return '%'
"="		              return '='
"("                   return '('
")"                   return ')'
[a-zA-Z][a-zA-Z0-9]*  return 'ID'
<<EOF>>               return 'EOF'
.                     return 'INVALID'

/lex

%start expressions

%% /* language grammar */

expressions
    : variable EOF
        { return $1; }
    ;

variable
    : exp
        {$$ = $1;}
    | factor '=' variable
        {$$ = ['=', $1, $3];}
    ;

exp
    : term
        {$$ = $1;}
    | exp '+' term
        {$$ = ['+', $1, $3];}
    | exp '-' term
        {$$ = ['-', $1, $3];}
    ;

term
    : exponent
        {$$ = $1;}
    | term '*' exponent
        {$$ = ['*', $1, $3];}
    | term '/' exponent
        {$$ = ['/', $1, $3];}
    | term '%' exponent
        {$$ = ['%', $1, $3];}
    ;

exponent
    : factor
        {$$ = $1;}
    | factor '^' exponent
        {$$ = ['^', $1, $3];}
    ;

factor
    : ID
        {$$ = yytext;}
    | NUMBER
        {$$ = Number(yytext);}
    | '(' exp ')'
        {$$ = $2;}
    ;
