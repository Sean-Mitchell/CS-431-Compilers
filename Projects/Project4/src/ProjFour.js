Package ProjFour;

Helpers
    sp  = ' ';
    number = ['0'..'9'];
    letter = ['a'..'z'] | ['A'..'Z'];
    anychar = [35..255];
	

	
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
	real = number+ '.' number+;
	stringtok = '"' (number | letter)* '"';
    whitespace = sp+;
	anychars = anychar+;
	begin = 'BEGIN';
	end = 'END';
	true = 'TRUE';
	false = 'FALSE';
	classs = 'CLASS';
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
	void = 'VOID';
	
	equivalent = '==';
	notequivalent = '!=';
	geq = '>=';
	leq = '<=';
	less = '<';
	greater = '>';

	add = '+';
	subtract = '-';
	times = '*';
	divide = '/';

Ignored Tokens
  whitespace;

Productions
    prog = begin classmethodstmts end;
	
	classmethodstmts = {first} classmethodstmts classmethodstmt |
		{empty} ;
		
	classmethodstmt = {first} classs id lcurly methodstmtseqs rcurly |
		{second} type id lparen varlist rparen lcurly stmtseq rcurly |
		{third} [first]:id commaids colon type semicolon ;
		
	commaids = {first} commaid commaids |
		{empty} ;
		
	commaid = comma id;
		
	methodstmtseqs = {first} methodstmtseqs methodstmtseq |
		{empty} ;
		
	methodstmtseq = {first} type id lparen varlist rparen lcurly stmtseq rcurly |
		{second} [first]:id commaids colon type semicolon ;
		
	stmtseq = {first} stmt stmtseq |
		{empty} ;
		
	stmt = {third} [first]:id commaids colon type optarray semicolon |
		{fourth} if lparen boolid rparen then [first]:lcurly [second]:stmtseq [third]:rcurly optelse |
		{fifth} while lparen boolid rparen lcurly stmtseq rcurly |
		{sixth} for lparen opttype id [first]:colon [second]:equal [third]:expr [fourth]:semicolon boolean [fifth]:semicolon bigora rparen lcurly stmtseq rcurly |
		{seventh} id optarray colon equal get lparen rparen semicolon |
		{eigth} put lparen id optarray rparen semicolon |
		{ninth} [first]:id optarray bigorb |
		{tenth} id lparen varlisttwo rparen semicolon |
		{eleventh} [first]:id optarray dot [second]:id lparen varlisttwo rparen methodchains semicolon |
		{twelfth} return expr semicolon |
		{thirteenth} switch [lparen1]:lparen expr [rparen1]:rparen lcurly case [lparen2]:lparen [first]:int [rparen2]:rparen [colon1]:colon [stmtseq1]:stmtseq breakornah cases default [colon2]:colon [stmtseq2]:stmtseq rcurly ;
	
	cases = {first} cases singcase |
		{empty} ;
		
	singcase = case lparen int rparen colon stmtseq breakornah ;
	
	breakornah = {first} break semicolon |
		{empty} ;
	
	methodchains = {first} methodchains methodchain |
		{empty} ;
		
	methodchain = dot id lparen varlisttwo rparen methodchain |
		{empty} ;
	
	bigora = {first} id [first]:plus [second]:plus |
		{second} id [first]:minus [second]:minus |
		{third} id colon equal expr ;
		
	bigorb = {first} [first]:plus [second]:plus semicolon |
		{second} [first]:minus [second]:minus semicolon |
		{third} colon equal new id lparen rparen semicolon |
		{fourth} bigorc ;
	
	bigorc = {first} colon equal new id lparen rparen semicolon |
		{second} colon equal bigord ;
	
	bigord = {first} expr semicolon |
		{second} [first]:quote anycharsprod [second]:quote semicolon |
		{third} boolean semicolon ;
	
	opttype = {first} type |
		{empty} ;
		
	optarray = {array} lbrack int rbrack |
		{empty} ;
		
	optelse = {first} else lcurly stmtseq rcurly |
		{empty} ;
	
	varlist = varlistornah ;
	
	varlistornah = {first} id colon type optarray bigcommaids |
		{empty} ;
		
	bigcommaids = {first} bigcommaid bigcommaids |
		{empty} ;
		
	bigcommaid = comma id colon type optarray ;
	
	varlisttwo = varlisttwoornah ;
	
	varlisttwoornah = {first} expr commaexprs |
		{empty} ;
		
	commaexprs = {first} commaexpr commaexprs |
		{empty} ;
		
	commaexpr = comma expr;
	
	expr = {first} expr addop term |
		{second} term ;
		
	term = {mult} term multop factor |
		{factor} factor ;
		
	factor = {exp} lparen expr rparen |
		{minus} minus factor |
		{int} int |
		{real} real |
		{boolean} boolean |
		{sixth} [first]:id bigore ;
		
	bigore = {first} optarray optmethodchain |
		{second} lparen varlisttwo rparen ;
	
	optmethodchain = {first} methodchain |
		{empty} ;
	
	string = stringtok;
	
	boolean = {true} true | {false}false |
		{expCondExp} [first]:expr cond [second]:expr ;

	cond = {first} equivalent |
		{second} notequivalent |
		{third} geq | {fourth} leq |
		{fifth} greater | {sixth} less ;

	addop = {add} add | {subtract} subtract ;
	multop = {times} times | {divide} divide ;

	type = {int} int | 
		{realp} realp |
		{string} string |
		{boolean} boolean |
		{void} void | 
		{id} id ;
	
	boolid = {boolean} boolean |
		{id} id ; 
	
	id = {idtok} idtok ;
	int = {digittok} digittok ; 
	realp = {real} real ;
	anycharsprod = {anychars}anychars ;
	letter ={letter} letter ;
	digit = {digsing}digsing ;
	voidprod = {void}void ;