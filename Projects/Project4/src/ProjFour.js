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
	string = quote (number | letter)* quote;
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
		{third} id (comma id)* colon type semicolon ;
		
	methodstmtseqs = {first} methodstmtseqs methodstmtseq |
		{empty} ;
		
	methodstmtseq = {first} type id lparen varlist rparen lcurly stmtseq rcurly |
		{second} id (comma id)* colon type semicolon ;
		
	stmtseq = {first} stmt stmtseq |
		{empty} ;
		
	stmt = {third} id (comma id)* colon type (lbrack int rbrack)? semicolon |
		{fourth} if lparen boolean rparen then lcurly stmtseq rcurly (else lcurly stmtseq rcurly)? |
		{fifth} while lparen boolean rparen lcurly stmtseq rcurly |
		{sixth} for lparen (type)? id colon equal expr semicolon boolean semicolon (id plus plus | id minus minus | id colon equal expr) rparen lcurly stmtseq rcurly |
		{seventh} id ( lbrack int rbrack )? colon equal get lparen rparen semicolon |
		{eigth} put lparen id (lbrack int rbrack)? rparen semicolon |
		{ninth} id (lbrack int rbrack)? (plus plus semicolon | minus minus semicolon | colon equal new id lparen rparen semicolon | colon equal (expr semicolon | quote anychars quote semicolon | boolean semicolon)) |
		{tenth} id lparen varlisttwo rparen semicolon |
		{eleventh} id (lbrack int rbrack)? dot id lparen varlisttwo rparen (dot id lparen varlisttwo rparen )* semicolon |
		{twelfth} return expr semicolon |
		{thirteenth} 
		
	string = string;
	
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
