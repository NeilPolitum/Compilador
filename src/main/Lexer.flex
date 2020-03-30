package main;
import static main.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    public String lexeme;
%}
%%

{espacio} {/*Ignore*/}
( "//"(.)* ) {/*Ignore*/}
( "\"" ) {lexeme=yytext(); return Comillas;}
( byte ) {lexeme=yytext(); return Byte;}
( int ) {lexeme=yytext(); return Int;}
( char ) {lexeme=yytext(); return Char;}
( long ) {lexeme=yytext(); return Long;}
( float ) {lexeme=yytext(); return Float;}
( double ) {lexeme=yytext(); return Double;}
( String ) {lexeme=yytext(); return Cadena;}
( if ) {lexeme=yytext(); return If;}
( else ) {lexeme=yytext(); return Else;}
( do ) {lexeme=yytext(); return Do;}
( while ) {lexeme=yytext(); return While;}
( for ) {lexeme=yytext(); return For;}
( "=" ) {lexeme=yytext(); return Igual;}
( "+" ) {lexeme=yytext(); return Suma;}
( "-" ) {lexeme=yytext(); return Resta;}
( "*" ) {lexeme=yytext(); return Multiplicacion;}
( "/" ) {lexeme=yytext(); return Division;}
( ":" ) {lexeme=yytext(); return Dos_Puntos;}
( ";" ) {lexeme=yytext(); return Punto_Coma;}
( "." ) {lexeme=yytext(); return Punto;}
( "&&" | "||" | "!" | "&" | "|" ) {lexeme=yytext(); return Operador_logico;}
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" ) {lexeme = yytext(); return Operador_relacional;}
( "+=" | "-="  | "*=" | "/=" | "%=" ) {lexeme = yytext(); return Operador_atribucion;}
( "++" | "--" ) {lexeme = yytext(); return Operador_incremento;}
(true | false) {lexeme = yytext(); return Operador_booleano;}
( "(" ) {lexeme=yytext(); return Parentesis_abierto;}
( ")" ) {lexeme=yytext(); return Parentesis_cerrado;}
( "{" ) {lexeme=yytext(); return Llave_abierta;}
( "}" ) {lexeme=yytext(); return Llave_cerrada;}
( "[" ) {lexeme = yytext(); return Corchete_abierto;}
( "]" ) {lexeme = yytext(); return Corchete_cerrado;}
( "main" ) {lexeme=yytext(); return Main;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 . {return ERROR;}
