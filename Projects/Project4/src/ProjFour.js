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
	dot = '.';

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

Ignored Tokens
  whitespace;

Productions
    prog = begin classmethodstmts end;
	
	classmethodstmts = {first} classmethodstmts classmethodstmt |
		{empty};
		
	classmethodstmt = {first} class id lcurly methodstmtseqs rcurly |
		{second} type id lparen varlist rparen lcurly stmtseq rcurly |
<<<<<<< HEAD
<<<<<<< HEAD
		{third} [first]:id commaids colon type semicolon ;
		
	commaids = {first} commaid commaids |
		{empty} ;
		
	commaid = comma id;
=======
		{third} id commaid* colon type semicolon ;
>>>>>>> f12a0a20d9af024e497188b8a6e534e555b56235
=======
		{third} id commaid* colon type semicolon ;
>>>>>>> f12a0a20d9af024e497188b8a6e534e555b56235
		
	methodstmtseqs = {first} methodstmtseqs methodstmtseq |
		{empty} ;
		
	methodstmtseq = {first} type id lparen varlist rparen lcurly stmtseq rcurly |
<<<<<<< HEAD
<<<<<<< HEAD
		{second} [first]:id commaids colon type semicolon ;
=======
		{second} id commaid* colon type semicolon ;
>>>>>>> f12a0a20d9af024e497188b8a6e534e555b56235
=======
		{second} id commaid* colon type semicolon ;
>>>>>>> f12a0a20d9af024e497188b8a6e534e555b56235
		
	stmtseq = {first} stmt stmtseq |
		{empty} ;
		
<<<<<<< HEAD
<<<<<<< HEAD
	stmt = {third} [first]:id commaids colon type optbracknum semicolon |
		{fourth} if lparen boolean rparen then [first]:lcurly [first]:stmtseq [first]:rcurly optelse |
=======
=======
>>>>>>> f12a0a20d9af024e497188b8a6e534e555b56235
	stmt = {third} id commaid* colon type (lbrack int rbrack)? semicolon |
		{fourth} if lparen boolean rparen then [first]:lcurly [first]:stmtseq [first]:rcurly (else [second]:lcurly [second]:stmtseq [second]:rcurly)? |
>>>>>>> f12a0a20d9af024e497188b8a6e534e555b56235
		{fifth} while lparen boolean rparen lcurly stmtseq rcurly |
		{sixth} for lparen opttype id [first]:colon [first]:equal [first]:expr [first]:semicolon boolean [second]:semicolon bigora rparen lcurly stmtseq rcurly |
		{seventh} id optbracknum colon equal get lparen rparen semicolon |
		{eigth} put lparen id optbracknum rparen semicolon |
		{ninth} [first]:id optbracknum bigorb |
		{tenth} id lparen varlisttwo rparen semicolon |
		{eleventh} [first]:id optbracknum [first]:dot [second]:id [first]:lparen [first]:varlisttwo [first]:rparen dotidthings semicolon |
		{twelfth} return expr semicolon |
		{thirteenth} switch [first]:lparen expr [first]:rparen lcurly [first]:case [second]:lparen [first]:int [second]:rparen [first]:colon [first]:stmtseq breakornah cases default [third]:colon [third]:stmtseq rcurly ;
	
	cases = {first} cases singcase |
		{empty} ;
		
	singcase = [second]:case [third]:lparen [second]:int [third]:rparen [second]:colon [second]:stmtseq breakornah ;
	
	breakornah = {first} [first]:break [first]:semicolon |
		{empty} ;
	
	dotidthings = {first} dotidthings dotidthing |
		{empty} ;
		
	dotidthing = [second]:dot [third]:id [second]:lparen [second]:varlisttwo [second]:rparen;
	
	bigora = {first} [first]:id [first]:plus [second]:plus |
		{second} [second]:id [first]:minus [second]:minus |
		{third} [third]:id [second]:colon [second]:equal [second]:expr ;
		
	bigorb = {first} [first]:plus [second]:plus [first]:semicolon |
		{second} [first]:minus [second]:minus [second]:semicolon |
		{third} [first]:colon [first]:equal new [second]:id lparen rparen [third]:semicolon |
		{fourth} bigorc ;
	
	bigorc = {first} [first]:colon [first]:equal new [second]:id lparen rparen [third]:semicolon |
		{second} [second]:colon [second]:equal bigord ;
	
	bigord = {first} expr [fourth]:semicolon |
		{second} [first]:quote anychars [second]:quote [fifth]:semicolon |
		{third} boolean [sixth]:semicolon ;
	
	opttype = {first} type |
		{empty} ;
		
	optbracknum = {first} lbrack int rbrack |
		{empty} ;
		
	optelse = {first} else [second]:lcurly [second]:stmtseq [second]:rcurly |
		{empty} ;
	
	varlist = varlistornah ;
	
	varlistornah = {first} [first]:id [first]:colon [first]:type optbracknum bigcommaids |
		{empty} ;
		
	bigcommaids = {first} bigcommaid bigcommaids |
		{empty} ;
		
	bigcommaid = comma [second]:id [second]:colon [second]:type optbracknum
	
	varlisttwo = varlisttwoornah ;
	
	varlisttwoornah = {first} [first]:expr commaexprs |
		{empty} ;
		
	commaexprs = {first} commaexpr commaexprs |
		{empty} ;
		
	commaexpr = comma [second]:expr;
	
	expr = {first} expr addop term |
		{second} term ;
		
	factor = {first} lparen expr rparen |
		{second} minus factor |
		{third} int |
		{fourth} real |
		{fifth} boolean |
		{sixth} [first]:id bigore ;
		
	bigore = {first} optbracknum optdotidthing |
		{second} [second]:lparen [second]:varlisttwo [second]:rparen ;
	
	optdotidthing = {first} dotidthing |
		{empty} ;
	
	string = stringtok;
	
	boolean = {first} true | {fourth}false |
		{second} [first]:expr cond [second]:expr | 
		{third} id ;

	cond = {first} equivalent |
		{second} notequivalent |
		{third} geq | {fourth} leq |
		{fifth} greater | {sixth} less ;

	addop = {first} add | {second} subtract;
	multop = {first} times | {second} divide ;

	type = {first} int | 
		{second} real |
		{third} string |
		{fourth} boolean |
		{fifth} void | 
		{sixth} id ; 
	

	
	id = idtok ;
	int = digittok ;
	real = real ;
	anychars = anychars ;
	letter = letter ;
	digit = digsing ;
	commaid = comma id ;
