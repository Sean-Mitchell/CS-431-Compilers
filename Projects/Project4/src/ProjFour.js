Package ProjFour;

Helpers
    sp  = ' ';
    number = ['0'..'9'];
    letter = ['a'..'z'] | ['A'..'Z'];
    anychar = [35..255];
	

	add = '+';
	subtract = '-';
	times = '*';
	divide = '/';

	
	lparenh = '(';
	rparenh = ')';	
	lbracket = '[';
	rbracket = ']';


    alphanumeric = letter | number;
    
Tokens
	lparen = lparenh;
	rparen = rparenh;
    idtok = letter (letter | number)*;
    digittok = number+;
	digsing = number;
	real = number+ dot number+;
	stringtok = quote (number | letter)* quote;
    whitespace = sp+;
	anychars = anychar+;
	begin = 'BEGIN';
	end = 'END';
	true = 'TRUE';
	false = 'FALSE';
	class = 'CLASS';
	lcurly = '{';
	rcurly = '}';
	comma = ',';
	colon = ':';
	lbrack = '[';
	rbrack = ']';
	equal = '=';
	semicolon = ';';
	quote = '"';
	if = 'IF';
	then = 'THEN';
	else = 'ELSE';
	while = 'WHILE';
	for = 'FOR';
	plus = '+';
	minus = '-';
	put = 'PUT';
	get = 'GET';
	new = 'NEW';
	return = 'RETURN';
	dot = '.';
	switch = 'SWITCH';
	case = 'CASE';
	break = 'BREAK';
	default = 'DEFAULT';
	
	equivalent = '==';
	notequivalent = '!=';
	geq = '>=';
	leq = '<=';
	less = '<';
	greater = '>';

Ignored Tokens
  whitespace;

Productions
    prog = begin classmethodstmts end;
	
	classmethodstmts = {first} classmethodstmts classmethodstmt |
		{empty} ;
		
	classmethodstmt = {first} class id lcurly methodstmtseqs rcurly |
		{second} type id lparen varlist rparen lcurly stmtseq rcurly |
		{third} [first]:id (comma [second]:id)* colon type semicolon ;
		
	methodstmtseqs = {first} methodstmtseqs methodstmtseq |
		{empty} ;
		
	methodstmtseq = {first} type id lparen varlist rparen lcurly stmtseq rcurly |
		{second} [first]:id (comma [second]:id)* colon type semicolon ;
		
	stmtseq = {first} stmt stmtseq |
		{empty} ;
		
	stmt = {third} [first]:id (comma [second]:id)* colon type (lbrack int rbrack)? semicolon |
		{fourth} if lparen boolean rparen then [first]:lcurly [first]:stmtseq [first]:rcurly (else [second]:lcurly [second]:stmtseq [second]:rcurly)? |
		{fifth} while lparen boolean rparen lcurly stmtseq rcurly |
		{sixth} for lparen (type)? id [first]:colon [first]:equal [first]:expr [first]:semicolon boolean [second]:semicolon ([first]:id [first]:plus [second]:plus | [second]:id [first]:minus [second]:minus | [third]:id [second]:colon [second]:equal [second]:expr) rparen lcurly stmtseq rcurly |
		{seventh} id ( lbrack int rbrack )? colon equal get lparen rparen semicolon |
		{eigth} put lparen id (lbrack int rbrack)? rparen semicolon |
		{ninth} [first]:id (lbrack int rbrack)? ([first]:plus [second]:plus [first]:semicolon | [first]:minus [second]:minus [second]:semicolon | [first]:colon [first]:equal new [second]:id lparen rparen [third]:semicolon | [second]:colon [second]:equal (expr [fourth]:semicolon | [first]:quote anychars [second]:quote [fifth]:semicolon | boolean [sixth]:semicolon)) |
		{tenth} id lparen varlisttwo rparen semicolon |
		{eleventh} [first]:id (lbrack int rbrack)? [first]:dot [second]:id [first]:lparen [first]:varlisttwo [first]:rparen ([second]:dot [third]:id [second]:lparen [second]:varlisttwo [second]:rparen )* semicolon |
		{twelfth} return expr semicolon |
		{thirteenth} switch [first]:lparen expr [first]:rparen lcurly [first]:case [second]:lparen [first]:int [second]:rparen [first]:colon [first]:stmtseq ([first]:break [first]:semicolon)? ([second]:case [third]:lparen [second]:int [third]:rparen [second]:colon [second]:stmtseq ([second]:break [second]:semicolon)?)* default [third]:colon [third]:stmtseq rcurly ;
		
	varlist = ([first]:id [first]:colon [first]:type ([first]:lbrack [first]:int [first]:rbrack)? (comma [second]:id [second]:colon [second]:type ([second]:lbrack [second]:int [second]:rbrack)?)*)? ;
	
	varlisttwo = ([first]:expr (comma [second]:expr)*)? ;
	
	expr = {first} expr addop term |
		{second} term ;
		
	factor = {first} lparen expr rparen |
		{second} minus factor |
		{third} int |
		{fourth} real |
		{fifth} boolean |
		{sixth} [first]:id (((lbrack int rbrack)? (dot [second]:id [first]:lparen [first]:varlisttwo [first]:rparen)?) | ([second]:lparen [second]:varlisttwo [second]:rparen)) ;
		
	string = stringtok;
	
	boolean = {first} true | false |
		{second} [first]:expr cond [second]:expr | 
			{third} id;

	cond = {first} equivalent |
		{second} notequivalent |
		{third} geq | {fourth} leq |
		{fifth} greater | {sixth} less;

	addop = {first} add | {second} subtract;
	multop = {first} times | {second} divide;

	type = {first} int | 
		{second} real |
		{third} string |
		{fourth} boolean |
		{fifth} void | 
		{sixth} id;
			
	id = idtok;
	int = digittok;
	real = real;
	anychars = anychars;
	letter = letter;
	digit = digsing;
