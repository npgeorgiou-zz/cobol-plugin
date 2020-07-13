package com.nikos.gnucobol_3_1;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.nikos.gnucobol_3_1.psi.CobolTypes;
import com.intellij.psi.TokenType;

%%
%class CobolLexer
%implements FlexLexer

%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

//https://jflex.de/manual.html#unipropsyntax
EOL_WS           = \R
LINE_WS          = [\ \t]
WHITE_SPACE_CHAR = {EOL_WS} | {LINE_WS}
WHITE_SPACE      = {WHITE_SPACE_CHAR}+

INTEGER =[\+\-]?[0-9]+
FLOAT =[\+\-]?[0-9]+\.[0-9]+

STR_CHAR=[^\"\n\r\\]
STRING= \" {STR_CHAR}* \" | '{STR_CHAR}*'

COMMENT ="*>".*
DOT=\.


IDENTIFIER = ([:jletterdigit:])+ (- | [:jletterdigit:])* ([:jletterdigit:])+

%state ITEM_TYPE

%%

({WHITE_SPACE})+          {return TokenType.WHITE_SPACE;}
"("                       {return CobolTypes.PAREN_OPEN;}
")"                       {return CobolTypes.PAREN_CLOSE;}
"is"                      {return CobolTypes.IS;}

<YYINITIAL>
{
    ({WHITE_SPACE})+          {return TokenType.WHITE_SPACE; }
    {COMMENT}                 {return CobolParserDefinition.COMMENT; }
    {INTEGER}                 {return CobolTypes.INTEGER; }
    {FLOAT}                   {return CobolTypes.FLOAT; }
    {STRING}                  {return CobolTypes.STRING; }
    {DOT}                     {return CobolTypes.DOT;}
    ","                       {return CobolTypes.COMMA;}
    "="                       {return CobolTypes.EQUALS_OP;}
    "**"                      {return CobolTypes.POWER_OP ;}
    "+"                       {return CobolTypes.ADD_OP;}
    "-"                       {return CobolTypes.SUBTRACT_OP;}
    "*"                       {return CobolTypes.MULTIPLY_OP;}
    "/"                       {return CobolTypes.DIVIDE_OP;}

    "identification division" {return CobolTypes.IDENTIFICATION_DIVISION;}
    "program-id"              {return CobolTypes.PROGRAM_ID;}
    "author"                  {return CobolTypes.AUTHOR;}
    "installation"            {return CobolTypes.INSTALLATION;}
    "date-written"            {return CobolTypes.DATE_WRITTEN;}
    "date-compiled"           {return CobolTypes.DATE_COMPILED;}
    "security"                {return CobolTypes.SECURITY;}
    "environment division"    {return CobolTypes.ENVIRONMENT_DIVISION;}
    "data division"           {return CobolTypes.DATA_DIVISION;}
    "working-storage section" {return CobolTypes.WORKING_STORAGE_SECTION;}
    "local-storage section"   {return CobolTypes.LOCAL_STORAGE_SECTION;}
    "linkage section"         {return CobolTypes.LINKAGE_SECTION;}
    "procedure division"      {return CobolTypes.PROCEDURE_DIVISION;}

    "zero"                    {return CobolTypes.ZERO;}
    "zeros"                   {return CobolTypes.ZERO;}
    "zeroes"                  {return CobolTypes.ZERO;}
    "space"                   {return CobolTypes.SPACE;}
    "spaces"                  {return CobolTypes.SPACE;}
    "high-value"              {return CobolTypes.HIGH_VALUE;}
    "high-values"             {return CobolTypes.HIGH_VALUE;}
    "low-value"               {return CobolTypes.LOW_VALUE;}
    "low-values"              {return CobolTypes.LOW_VALUE;}
    "quote"                   {return CobolTypes.QUOTE;}
    "quotes"                  {return CobolTypes.QUOTE;}
    "null"                    {return CobolTypes.NULL;}
    "nulls"                   {return CobolTypes.NULL;}
    "all"                     {return CobolTypes.ALL;}

    "accept"                  {return CobolTypes.ACCEPT;}
    "from"                    {return CobolTypes.FROM;}
    "date"                    {return CobolTypes.DATE;}
    "yyyymmdd"                {return CobolTypes.YYYYMMDD;}
    "day"                     {return CobolTypes.DAY;}
    "yyyyddd"                 {return CobolTypes.YYYYDDD;}
    "day-of-week"             {return CobolTypes.DAY_OF_WEEK;}
    "time"                    {return CobolTypes.TIME;}
    "display"                 {return CobolTypes.DISPLAY;}
    "of"                      {return CobolTypes.OF;}
    "in"                      {return CobolTypes.IN;}
    "add"                     {return CobolTypes.ADD;}
    "giving"                  {return CobolTypes.GIVING;}
    "subtract"                {return CobolTypes.SUBTRACT;}
    "multiply"                {return CobolTypes.MULTIPLY;}
    "divide"                  {return CobolTypes.DIVIDE;}
    "into"                    {return CobolTypes.INTO;}
    "remainder"               {return CobolTypes.REMAINDER;}
    "compute"                 {return CobolTypes.COMPUTE;}

    "initialize"              {return CobolTypes.INITIALIZE;}
    "replacing"               {return CobolTypes.REPLACING;}
    "alphabetic"              {return CobolTypes.ALPHABETIC;}
    "alphanumeric"            {return CobolTypes.ALPHANUMERIC;}
    "numeric"                 {return CobolTypes.NUMERIC;}
    "data"                    {return CobolTypes.DATA;}
    "by"                      {return CobolTypes.BY;}
    "set"                     {return CobolTypes.SET;}
    "true"                    {return CobolTypes.TRUE;}

    "using"                   {return CobolTypes.USING;}
    "move"                    {return CobolTypes.MOVE;}
    "corr"                    {return CobolTypes.CORR;}
    "corresponding"           {return CobolTypes.CORRESPONDING;}
    "to"                      {return CobolTypes.TO;}
    "end"                     {return CobolTypes.END;}
    "call"                    {return CobolTypes.CALL;}
    "program"                 {return CobolTypes.PROGRAM;}
    "copy"                    {return CobolTypes.COPY;}

    "pic"                     {yybegin(ITEM_TYPE); return CobolTypes.PIC;}
    "picture"                 {yybegin(ITEM_TYPE); return CobolTypes.PIC;}
    "value"                   {return CobolTypes.VALUE;}
    "values"                  {return CobolTypes.VALUE;}
    "redefines"               {return CobolTypes.REDEFINES;}
    "renames"                 {return CobolTypes.RENAMES;}
    "through"                 {return CobolTypes.THROUGH;}
    "thru"                    {return CobolTypes.THROUGH;}

    {IDENTIFIER}              {return CobolTypes.IDENTIFIER;}
}

<ITEM_TYPE>
{
    "9"                       {return CobolTypes.PIC_TYPE_NUMERIC;}
    "a"                       {return CobolTypes.PIC_TYPE_ALPHA;}
    "x"                       {return CobolTypes.PIC_TYPE_ALPHANUMERIC;}
    "s9"                      {return CobolTypes.PIC_TYPE_SIGNED_NUMERIC;}
    "v9"                      {return CobolTypes.PIC_TYPE_DECIMAL_NUMERIC;}
    {INTEGER}                 {return CobolTypes.INTEGER;}

    {DOT}                     {yybegin(YYINITIAL); return CobolTypes.DOT;}
    "value"                   {yybegin(YYINITIAL); return CobolTypes.VALUE;}
}

[^]                       {return TokenType.BAD_CHARACTER;}
