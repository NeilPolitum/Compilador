package main;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

{espacio} {/*Ignore*/}
( "//"(.)* ) {/*Ignore*/}
( "\"" ) {return new Symbol(sym.Comillas, yychar, yyline, yytext());}
( byte ) {return new Symbol(sym.Byte, yychar, yyline, yytext());}
( int ) {return new Symbol(sym.Int, yychar, yyline, yytext());}
( char ) {return new Symbol(sym.Char, yychar, yyline, yytext());}
( long ) {return new Symbol(sym.Long, yychar, yyline, yytext());}
( float ) {return new Symbol(sym.Float, yychar, yyline, yytext());}
( double ) {return new Symbol(sym.Double, yychar, yyline, yytext());}
( String ) {return new Symbol(sym.Cadena, yychar, yyline, yytext());}
( if ) {return new Symbol(sym.If, yychar, yyline, yytext());}
( else ) {return new Symbol(sym.Else, yychar, yyline, yytext());}
( do ) {return new Symbol(sym.Do, yychar, yyline, yytext());}
( while ) {return new Symbol(sym.While, yychar, yyline, yytext());}
( for ) {return new Symbol(sym.For, yychar, yyline, yytext());}
( "=" ) {return new Symbol(sym.Igual, yychar, yyline, yytext());}
( "+" ) {return new Symbol(sym.Suma, yychar, yyline, yytext());}
( "-" ) {return new Symbol(sym.Resta, yychar, yyline, yytext());}
( "*" ) {return new Symbol(sym.Multiplicacion, yychar, yyline, yytext());}
( "/" ) {return new Symbol(sym.Division, yychar, yyline, yytext());}
( ":" ) {return new Symbol(sym.Dos_Puntos, yychar, yyline, yytext());}
( ";" ) {return new Symbol(sym.Punto_Coma, yychar, yyline, yytext());}
( "." ) {return new Symbol(sym.Punto, yychar, yyline, yytext());}
( "&&" | "||" | "!" | "&" | "|" ) {return new Symbol(sym.Operador_logico, yychar, yyline, yytext());}
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" ) {return new Symbol(sym.Operador_relacional, yychar, yyline, yytext());}
( "+=" | "-="  | "*=" | "/=" | "%=" ) {return new Symbol(sym.Operador_atribucion, yychar, yyline, yytext());}
( "++" | "--" ) {return new Symbol(sym.Operador_incremento, yychar, yyline, yytext());}
( true | false ) {return new Symbol(sym.Operador_booleano, yychar, yyline, yytext());}
( "(" ) {return new Symbol(sym.Parentesis_abierto, yychar, yyline, yytext());}
( ")" ) {return new Symbol(sym.Parentesis_cerrado, yychar, yyline, yytext());}
( "{" ) {return new Symbol(sym.Llave_abierta, yychar, yyline, yytext());}
( "}" ) {return new Symbol(sym.Llave_cerrada, yychar, yyline, yytext());}
( "[" ) {return new Symbol(sym.Corchete_abierto, yychar, yyline, yytext());}
( "]" ) {return new Symbol(sym.Corchete_cerrado, yychar, yyline, yytext());}
( "main" ) {return new Symbol(sym.Main, yychar, yyline, yytext());}
{L}({L}|{D})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}
("(-"{D}+")")|{D}+ {return new Symbol(sym.Numero, yychar, yyline, yytext());}
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}